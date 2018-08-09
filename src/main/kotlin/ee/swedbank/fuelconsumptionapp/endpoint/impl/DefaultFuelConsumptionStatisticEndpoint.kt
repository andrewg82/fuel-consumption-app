package ee.swedbank.fuelconsumptionapp.endpoint.impl

import ee.swedbank.fuelconsumptionapp.domain.dto.FuelConsumptionStatisticDto
import ee.swedbank.fuelconsumptionapp.domain.dto.FuelPrices
import ee.swedbank.fuelconsumptionapp.endpoint.FuelConsumptionStatisticEndpoint
import ee.swedbank.fuelconsumptionapp.service.FuelConsumptionStatisticService
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class DefaultFuelConsumptionStatisticEndpoint(
        private val fuelConsumptionStatisticService: FuelConsumptionStatisticService
) : FuelConsumptionStatisticEndpoint {

    override fun getTotalSpentAmountOfMoney(driverId: Int?): Map<LocalDate, Double> {
        val fuelConsumptions = fuelConsumptionStatisticService.getAllByDriverIdOrAll(driverId)
        return fuelConsumptionStatisticService.getTotalSpentAmountOfMoney(fuelConsumptions)
    }

    override fun getMonthStatistic(date: LocalDate, driverId: Int?): List<FuelConsumptionStatisticDto> {
        val fuelConsumptions = fuelConsumptionStatisticService.getAllByMonthAndDriverIdOrAllByMonth(date, driverId)
        return fuelConsumptionStatisticService.getMonthStatistic(fuelConsumptions)
    }

    override fun getAggregatedStatistic(driverId: Int?): Map<LocalDate, List<FuelPrices>> {
        val fuelConsumptions = fuelConsumptionStatisticService.getAllByDriverIdOrAll(driverId)
        return fuelConsumptionStatisticService.getAggregatedStatisticByFuelType(fuelConsumptions)
    }
}