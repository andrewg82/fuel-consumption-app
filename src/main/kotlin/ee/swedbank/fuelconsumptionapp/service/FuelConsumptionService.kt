package ee.swedbank.fuelconsumptionapp.service

import ee.swedbank.fuelconsumptionapp.domain.dto.FuelConsumptionUpdateDto
import ee.swedbank.fuelconsumptionapp.domain.entity.FuelConsumption
import java.time.LocalDate

interface FuelConsumptionService {

    fun createFuelConsumption(fuelConsumption: FuelConsumption): FuelConsumption
    fun createFuelConsumptions(fuelConsumptionList: List<FuelConsumption>): List<FuelConsumption>
    fun getFuelConsumption(id: Long): FuelConsumption
    fun getAllFuelConsumptions(): List<FuelConsumption>
    fun updateFuelConsumption(fuelConsumptionUpdateDto: FuelConsumptionUpdateDto, id: Long): FuelConsumption
    fun deleteFuelConsumption(id: Long)

    fun getAllByDriverId(driverId: Int): List<FuelConsumption>
    fun getAllByMonth(date: LocalDate): List<FuelConsumption>
    fun getAllByMonthAndDriverId(date: LocalDate, driverId: Int): List<FuelConsumption>
}