package ee.swedbank.fuelconsumptionapp.service

import ee.swedbank.fuelconsumptionapp.domain.dto.FuelConsumptionStatisticsDto
import ee.swedbank.fuelconsumptionapp.domain.dto.FuelPrices
import ee.swedbank.fuelconsumptionapp.domain.entity.FuelConsumption
import java.time.LocalDate

interface FuelConsumptionStatisticsService {

    fun getTotalSpentAmountOfMoney(fuelConsumptions: List<FuelConsumption>): Map<LocalDate, Double>
    fun getMonthStatistics(fuelConsumptions: List<FuelConsumption>): List<FuelConsumptionStatisticsDto>
    fun getAggregatedStatisticsByFuelType(fuelConsumptions: List<FuelConsumption>): Map<LocalDate, List<FuelPrices>>

    fun getAllByDriverIdOrAll(driverId: Int?): List<FuelConsumption>
    fun getAllByMonthAndDriverIdOrAllByMonth(date: LocalDate, driverId: Int?): List<FuelConsumption>
}