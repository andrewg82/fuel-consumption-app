package ee.swedbank.fuelconsumptionapp.endpoint

import ee.swedbank.fuelconsumptionapp.domain.dto.FuelConsumptionStatisticDto
import ee.swedbank.fuelconsumptionapp.domain.dto.FuelPrices
import java.time.LocalDate

interface FuelConsumptionStatisticEndpoint {

    fun getTotalSpentAmountOfMoney(driverId: Int?): Map<LocalDate, Double>
    fun getMonthStatistic(date: LocalDate, driverId: Int?): List<FuelConsumptionStatisticDto>
    fun getAggregatedStatistic(driverId: Int?): Map<LocalDate, List<FuelPrices>>
}