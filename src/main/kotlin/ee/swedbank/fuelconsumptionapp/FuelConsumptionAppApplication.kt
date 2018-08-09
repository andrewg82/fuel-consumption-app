package ee.swedbank.fuelconsumptionapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FuelConsumptionAppApplication

fun main(args: Array<String>) {
    runApplication<FuelConsumptionAppApplication>(*args)
}