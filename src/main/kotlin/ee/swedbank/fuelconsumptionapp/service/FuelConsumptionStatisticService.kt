package ee.swedbank.fuelconsumptionapp.service

import ee.swedbank.fuelconsumptionapp.domain.dto.FuelConsumptionStatisticDto
import ee.swedbank.fuelconsumptionapp.domain.dto.FuelPrices
import ee.swedbank.fuelconsumptionapp.domain.entity.FuelConsumption
import java.time.LocalDate

interface FuelConsumptionStatisticService {

    fun getTotalSpentAmountOfMoney(fuelConsumptions: List<FuelConsumption>): Map<LocalDate, Double>
    fun getMonthStatistic(fuelConsumptions: List<FuelConsumption>): List<FuelConsumptionStatisticDto>
    fun getAggregatedStatisticByFuelType(fuelConsumptions: List<FuelConsumption>): Map<LocalDate, List<FuelPrices>>

    fun getAllByDriverIdOrAll(driverId: Int?): List<FuelConsumption>
    fun getAllByMonthAndDriverIdOrAllByMonth(date: LocalDate, driverId: Int?): List<FuelConsumption>
}