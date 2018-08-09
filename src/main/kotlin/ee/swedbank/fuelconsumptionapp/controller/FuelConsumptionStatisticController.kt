package ee.swedbank.fuelconsumptionapp.controller

import ee.swedbank.fuelconsumptionapp.common.Paths
import ee.swedbank.fuelconsumptionapp.domain.dto.FuelConsumptionStatisticDto
import ee.swedbank.fuelconsumptionapp.domain.dto.FuelPrices
import ee.swedbank.fuelconsumptionapp.endpoint.FuelConsumptionStatisticEndpoint
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping(value = [(Paths.STATISTIC)])
class FuelConsumptionStatisticController(
        private val fuelConsumptionStatisticEndpoint: FuelConsumptionStatisticEndpoint
) {

    @GetMapping(Paths.TOTAL_SPENT_AMOUNT)
    fun getTotalSpentAmountOfMoney(@RequestParam(value = "driverId", required = false) driverId: Int?): Map<LocalDate, Double> =
            fuelConsumptionStatisticEndpoint.getTotalSpentAmountOfMoney(driverId)

    @GetMapping(Paths.MONTH_STATISTIC)
    fun getMonthStatistic(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") date: LocalDate,
            @RequestParam(value = "driverId", required = false) driverId: Int?
    ): List<FuelConsumptionStatisticDto> =
            fuelConsumptionStatisticEndpoint.getMonthStatistic(date, driverId)

    @GetMapping(Paths.AGGREGATED_STATISTIC)
    fun getAggregatedStatistic(@RequestParam(value = "driverId", required = false) driverId: Int?): Map<LocalDate, List<FuelPrices>> =
            fuelConsumptionStatisticEndpoint.getAggregatedStatistic(driverId)
}