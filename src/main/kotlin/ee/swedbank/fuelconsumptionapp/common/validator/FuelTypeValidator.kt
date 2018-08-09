package ee.swedbank.fuelconsumptionapp.common.validator

import ee.swedbank.fuelconsumptionapp.common.annotation.ValidFuelType
import ee.swedbank.fuelconsumptionapp.domain.entity.FuelType
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

/**
 * Validator for FuelType enum class
 */
class FuelTypeValidator : ConstraintValidator<ValidFuelType, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        try {
            if (null == value) {
                return true
            }
            FuelType.valueOf(value)
            return true
        } catch (exception: IllegalArgumentException) {
            return false
        }
    }
}