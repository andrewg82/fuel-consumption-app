package ee.swedbank.fuelconsumptionapp.endpoint.impl

import ee.swedbank.fuelconsumptionapp.domain.dto.FuelConsumptionStatisticsDto
import ee.swedbank.fuelconsumptionapp.domain.dto.FuelPrices
import ee.swedbank.fuelconsumptionapp.endpoint.FuelConsumptionStatisticsEndpoint
import ee.swedbank.fuelconsumptionapp.service.FuelConsumptionStatisticsService
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class DefaultFuelConsumptionStatisticsEndpoint(
        private val fuelConsumptionStatisticsService: FuelConsumptionStatisticsService
) : FuelConsumptionStatisticsEndpoint {

    override fun getTotalSpentAmountOfMoney(driverId: Int?): Map<LocalDate, Double> {
        val fuelConsumptions = fuelConsumptionStatisticsService.getAllByDriverIdOrAll(driverId)
        return fuelConsumptionStatisticsService.getTotalSpentAmountOfMoney(fuelConsumptions)
    }

    override fun getMonthStatistics(date: LocalDate, driverId: Int?): List<FuelConsumptionStatisticsDto> {
        val fuelConsumptions = fuelConsumptionStatisticsService.getAllByMonthAndDriverIdOrAllByMonth(date, driverId)
        return fuelConsumptionStatisticsService.getMonthStatistics(fuelConsumptions)
    }

    override fun getAggregatedStatistics(driverId: Int?): Map<LocalDate, List<FuelPrices>> {
        val fuelConsumptions = fuelConsumptionStatisticsService.getAllByDriverIdOrAll(driverId)
        return fuelConsumptionStatisticsService.getAggregatedStatisticsByFuelType(fuelConsumptions)
    }
}