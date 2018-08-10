package ee.swedbank.fuelconsumptionapp.controller

import ee.swedbank.fuelconsumptionapp.common.Paths
import ee.swedbank.fuelconsumptionapp.domain.dto.FuelConsumptionDto
import ee.swedbank.fuelconsumptionapp.domain.dto.FuelConsumptionUpdateDto
import ee.swedbank.fuelconsumptionapp.endpoint.FuelConsumptionEndpoint
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = [(Paths.FUEL_CONSUMPTION)])
class FuelConsumptionController(
        private val fuelConsumptionEndpoint: FuelConsumptionEndpoint
) {

    @PostMapping
    fun createFuelConsumption(@Validated @RequestBody fuelConsumptionDto: FuelConsumptionDto): FuelConsumptionDto =
            fuelConsumptionEndpoint.createFuelConsumption(fuelConsumptionDto)

    @PostMapping("/bulk")
    fun createFuelConsumptions(@RequestParam payload: String): List<FuelConsumptionDto> =
            fuelConsumptionEndpoint.createFuelConsumptions(payload)

    @GetMapping("/{id}")
    fun getFuelConsumption(@PathVariable id: Long): FuelConsumptionDto =
            fuelConsumptionEndpoint.getFuelConsumption(id)

    @GetMapping
    fun getAllFuelConsumptions(): List<FuelConsumptionDto> =
            fuelConsumptionEndpoint.getAllFuelConsumptions()

    @PutMapping("/{id}")
    fun updateFuelConsumption(@Validated @RequestBody fuelConsumptionUpdateDto: FuelConsumptionUpdateDto, @PathVariable id: Long): FuelConsumptionDto =
            fuelConsumptionEndpoint.updateFuelConsumption(fuelConsumptionUpdateDto, id)

    @DeleteMapping("/{id}")
    fun deleteFuelConsumption(@PathVariable id: Long) =
            fuelConsumptionEndpoint.deleteFuelConsumption(id)
}