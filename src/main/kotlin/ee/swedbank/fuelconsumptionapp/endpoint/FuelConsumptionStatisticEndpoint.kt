package ee.swedbank.fuelconsumptionapp.endpoint

import ee.swedbank.fuelconsumptionapp.domain.dto.FuelConsumptionStatisticsDto
import ee.swedbank.fuelconsumptionapp.domain.dto.FuelPrices
import java.time.LocalDate

interface FuelConsumptionStatisticsEndpoint {

    fun getTotalSpentAmountOfMoney(driverId: Int?): Map<LocalDate, Double>
    fun getMonthStatistics(date: LocalDate, driverId: Int?): List<FuelConsumptionStatisticsDto>
    fun getAggregatedStatistics(driverId: Int?): Map<LocalDate, List<FuelPrices>>
}