package ee.swedbank.fuelconsumptionapp.common.handler

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import ee.swedbank.fuelconsumptionapp.common.helper.log
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.orm.ObjectOptimisticLockingFailureException
import org.springframework.validation.Errors
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.multipart.MultipartException
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import javax.persistence.EntityNotFoundException
import javax.validation.ValidationException

/**
 * Handler class for catching all errors
 */
@EnableWebMvc
@ControllerAdvice
class RestResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {

    /**
     * Catches Optimistic Locking Exception and responses with specific status code
     */
    @ExceptionHandler(ObjectOptimisticLockingFailureException::class, OptimisticLockingFailureException::class)
    protected fun handleOptimisticLockingException(exception: RuntimeException, request: WebRequest): ResponseEntity<Any> {
        log.warn("Optimistic Locking Exception: ", exception.message)

        val body = "The entity was changed by another request"
        return handleExceptionInternal(exception, body,
                HttpHeaders(), HttpStatus.CONFLICT, request)
    }

    /**
     * Catches EntityNotFoundException and converts it into the 404 error
     */
    @ExceptionHandler(EntityNotFoundException::class)
    protected fun handleEntityNotFoundException(exception: EntityNotFoundException, request: WebRequest): ResponseEntity<Any> {
        val error = notFoundFromException(exception)
        return handleExceptionInternal(exception, error, HttpHeaders(), HttpStatus.NOT_FOUND, request)
    }

    /**
     * Catches the multipart exceptions
     */
    @ExceptionHandler(MultipartException::class)
    protected fun handleMultipartException(exception: MultipartException,
                                           request: WebRequest): ResponseEntity<Any>? {
        log.warn("MultipartException: ", exception.message)

        return handleExceptionInternal(exception, exception.message, HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request)
    }

    /**
     * Catches the Validation Exception
     */
    @ExceptionHandler(ValidationException::class)
    protected fun handleValidationException(exception: ValidationException,
                                            request: WebRequest): ResponseEntity<Any>? {
        val validationError = validationErrorFromException(exception)
        return handleExceptionInternal(exception, validationError, HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request)
    }

    /**
     * Catches Json ObjectMapper Exceptions
     */
    protected fun handleJsonProcessingException(exception: JsonProcessingException,
                                                request: WebRequest): ResponseEntity<Any>? {
        log.warn("JsonProcessingException: ", exception.message)

        return handleExceptionInternal(exception, exception.message, HttpHeaders(), HttpStatus.BAD_REQUEST, request)
    }

    /**
     * Handle all unprocessed exceptions
     */
    @ExceptionHandler(Exception::class)
    protected fun handleCommonException(exception: Exception, request: WebRequest): ResponseEntity<Any>? {
        log.error("Unexpected error: ", exception)

        val message = "Internal server error"
        return handleExceptionInternal(exception, message, HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request)
    }

    /**
     * Handle request wrong format exceptions
     */
    override fun handleHttpMessageNotReadable(ex: HttpMessageNotReadableException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val message = when (ex.cause) {
            is MissingKotlinParameterException -> "${(ex.cause as MissingKotlinParameterException).parameter.name} field is missing!"
            else -> ex.cause?.message
        }

        log.warn("BAD REQUEST: ", ex)

        return handleExceptionInternal(ex, message, headers, HttpStatus.BAD_REQUEST, request)
    }

    /**
     * Catches Validation Exceptions
     */
    override fun handleMethodArgumentNotValid(exception: MethodArgumentNotValidException, headers: HttpHeaders,
                                              status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val validationError = fromBindingErrors(exception.bindingResult)
        return handleExceptionInternal(exception, validationError, headers, HttpStatus.UNPROCESSABLE_ENTITY, request)
    }

    private fun fromBindingErrors(errors: Errors): ValidationError {
        val error = ValidationError("Validation failed. ${errors.errorCount} error(s)")

        for (objectError in errors.allErrors) {
            if (objectError is FieldError) {
                error.addValidationError("${objectError.field}: ${objectError.defaultMessage}")
            } else {
                error.addValidationError(objectError.defaultMessage ?: "Unknown validation error")
            }
        }
        return error
    }

    private fun validationErrorFromException(exception: Exception): ValidationError {
        val error = ValidationError("Validation failed. 1 error(s)")
        error.addValidationError(exception.message ?: "")

        return error
    }

    private fun notFoundFromException(exception: Exception) = NotFoundError(exception.message ?: "")

}

/**
 * Contains human-readable description of the ValidationException
 */
data class ValidationError(
        val errorMessage: String,
        val errors: MutableList<String> = arrayListOf()
) {

    fun addValidationError(error: String) {
        errors.add(error)
    }

}

data class NotFoundError(
        val message: String
)