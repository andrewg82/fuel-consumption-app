package ee.swedbank.fuelconsumptionapp.endpoint

import ee.swedbank.fuelconsumptionapp.domain.dto.FuelConsumptionDto
import ee.swedbank.fuelconsumptionapp.domain.dto.FuelConsumptionUpdateDto

interface FuelConsumptionEndpoint {

    fun createFuelConsumption(fuelConsumptionDto: FuelConsumptionDto): FuelConsumptionDto
    fun createFuelConsumptions(payload: String): List<FuelConsumptionDto>
    fun getFuelConsumption(id: Long): FuelConsumptionDto
    fun getAllFuelConsumptions(): List<FuelConsumptionDto>
    fun updateFuelConsumption(fuelConsumptionUpdateDto: FuelConsumptionUpdateDto, id: Long): FuelConsumptionDto
    fun deleteFuelConsumption(id: Long)
}