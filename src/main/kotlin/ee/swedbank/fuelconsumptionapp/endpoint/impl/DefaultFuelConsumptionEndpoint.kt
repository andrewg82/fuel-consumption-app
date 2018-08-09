package ee.swedbank.fuelconsumptionapp.endpoint.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import ee.swedbank.fuelconsumptionapp.domain.dto.FuelConsumptionDto
import ee.swedbank.fuelconsumptionapp.domain.dto.FuelConsumptionUpdateDto
import ee.swedbank.fuelconsumptionapp.domain.dto.toDto
import ee.swedbank.fuelconsumptionapp.domain.dto.toEntity
import ee.swedbank.fuelconsumptionapp.endpoint.FuelConsumptionEndpoint
import ee.swedbank.fuelconsumptionapp.service.FuelConsumptionService
import org.springframework.stereotype.Service

@Service
class DefaultFuelConsumptionEndpoint(
        private val fuelConsumptionService: FuelConsumptionService,
        private val objectMapper: ObjectMapper
) : FuelConsumptionEndpoint {

    override fun createFuelConsumption(fuelConsumptionDto: FuelConsumptionDto): FuelConsumptionDto =
            fuelConsumptionService.createFuelConsumption(fuelConsumptionDto.toEntity()).toDto()

    override fun createFuelConsumptions(payload: String): List<FuelConsumptionDto> {
        val fuelConsumptionDtoList: List<FuelConsumptionDto> = objectMapper.readValue(payload)
        return fuelConsumptionService.createFuelConsumptions(fuelConsumptionDtoList.map { it.toEntity() }).map { it.toDto() }
    }

    override fun getFuelConsumption(id: Long): FuelConsumptionDto =
            fuelConsumptionService.getFuelConsumption(id).toDto()

    override fun getAllFuelConsumptions(): List<FuelConsumptionDto> =
            fuelConsumptionService.getAllFuelConsumptions().map { it.toDto() }

    override fun updateFuelConsumption(fuelConsumptionUpdateDto: FuelConsumptionUpdateDto, id: Long): FuelConsumptionDto =
            fuelConsumptionService.updateFuelConsumption(fuelConsumptionUpdateDto, id).toDto()

    override fun deleteFuelConsumption(id: Long) {
        fuelConsumptionService.deleteFuelConsumption(id)
    }
}