package ee.swedbank.fuelconsumptionapp.controller

import ee.swedbank.fuelconsumptionapp.common.Paths
import ee.swedbank.fuelconsumptionapp.domain.dto.FuelConsumptionStatisticsDto
import ee.swedbank.fuelconsumptionapp.domain.dto.FuelPrices
import ee.swedbank.fuelconsumptionapp.endpoint.FuelConsumptionStatisticsEndpoint
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping(value = [(Paths.STATISTICS)])
class FuelConsumptionStatisticsController(
        private val fuelConsumptionStatisticsEndpoint: FuelConsumptionStatisticsEndpoint
) {

    @GetMapping(Paths.TOTAL_SPENT_AMOUNT)
    fun getTotalSpentAmountOfMoney(@RequestParam(value = "driverId", required = false) driverId: Int?): Map<LocalDate, Double> =
            fuelConsumptionStatisticsEndpoint.getTotalSpentAmountOfMoney(driverId)

    @GetMapping(Paths.MONTH_STATISTICS)
    fun getMonthStatistics(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") date: LocalDate,
            @RequestParam(value = "driverId", required = false) driverId: Int?
    ): List<FuelConsumptionStatisticsDto> =
            fuelConsumptionStatisticsEndpoint.getMonthStatistics(date, driverId)

    @GetMapping(Paths.AGGREGATED_STATISTICS)
    fun getAggregatedStatistics(@RequestParam(value = "driverId", required = false) driverId: Int?): Map<LocalDate, List<FuelPrices>> =
            fuelConsumptionStatisticsEndpoint.getAggregatedStatistics(driverId)
}