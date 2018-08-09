package ee.swedbank.fuelconsumptionapp.common.annotation

import ee.swedbank.fuelconsumptionapp.common.validator.FuelTypeValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

/**
 * Annotation for FuelType class validation
 */
@Constraint(validatedBy = [(FuelTypeValidator::class)])
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class ValidFuelType(
        val message: String = "Invalid value for FuelType",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = []
)