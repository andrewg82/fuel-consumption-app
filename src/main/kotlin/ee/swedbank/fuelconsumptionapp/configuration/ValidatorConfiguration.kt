package ee.swedbank.fuelconsumptionapp.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.validation.Validation
import javax.validation.Validator

@Configuration
class ValidatorConfiguration {

    @Bean
    fun validator(): Validator = Validation.buildDefaultValidatorFactory().validator
}