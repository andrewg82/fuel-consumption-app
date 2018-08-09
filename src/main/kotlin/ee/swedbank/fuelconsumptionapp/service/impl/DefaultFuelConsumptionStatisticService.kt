package ee.swedbank.fuelconsumptionapp.service.impl

import ee.swedbank.fuelconsumptionapp.domain.dto.FuelConsumptionStatisticDto
import ee.swedbank.fuelconsumptionapp.domain.dto.FuelPrices
import ee.swedbank.fuelconsumptionapp.domain.dto.toStatisticDto
import ee.swedbank.fuelconsumptionapp.domain.entity.FuelConsumption
import ee.swedbank.fuelconsumptionapp.domain.entity.FuelType
import ee.swedbank.fuelconsumptionapp.service.FuelConsumptionService
import ee.swedbank.fuelconsumptionapp.service.FuelConsumptionStatisticService
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.EntityNotFoundException

@Service
class DefaultFuelConsumptionStatisticService(
        private val fuelConsumptionService: FuelConsumptionService
) : FuelConsumptionStatisticService {

    override fun getTotalSpentAmountOfMoney(fuelConsumptions: List<FuelConsumption>): Map<LocalDate, Double> =
            if (fuelConsumptions.isNotEmpty()) {
                fuelConsumptions.groupBy { it.date.withDayOfMonth(1) }
                        .mapValues { entry -> entry.value.map { (it.pricePerLitter.multiply(it.volume)).toDouble() }.sum() }.toSortedMap()
            } else {
                throw EntityNotFoundException("There are no records matched your query")
            }


    override fun getMonthStatistic(fuelConsumptions: List<FuelConsumption>): List<FuelConsumptionStatisticDto> {
        if (fuelConsumptions.isNotEmpty()) {
            val totalPrices = calculatePrices(fuelConsumptions)

            return fuelConsumptions.map {
                it.toStatisticDto(totalPrices)
            }
        } else {
            throw EntityNotFoundException("There are no records matched your query")
        }
    }

    override fun getAggregatedStatisticByFuelType(fuelConsumptions: List<FuelConsumption>): Map<LocalDate, List<FuelPrices>> =
            if (fuelConsumptions.isNotEmpty()) {
                fuelConsumptions.groupBy { it.date.withDayOfMonth(1) }.mapValues { calculatePrices(it.value) }.toSortedMap()
            } else {
                throw EntityNotFoundException("There are no records matched your query")
            }

    override fun getAllByDriverIdOrAll(driverId: Int?): List<FuelConsumption> =
            driverId?.let {
                fuelConsumptionService.getAllByDriverId(driverId)
            } ?: fuelConsumptionService.getAllFuelConsumptions()

    override fun getAllByMonthAndDriverIdOrAllByMonth(date: LocalDate, driverId: Int?): List<FuelConsumption> =
            driverId?.let {
                fuelConsumptionService.getAllByMonthAndDriverId(date, driverId)
            } ?: fuelConsumptionService.getAllByMonth(date)

    private fun calculatePrices(fuelConsumptions: List<FuelConsumption>): List<FuelPrices> {
        val countingData = mutableListOf<CountingDataForPrices>()

        fuelConsumptions.forEach { fuelConsumption ->
            val countingDataEntry = countingData.findLast { fuelConsumption.fuelType == it.fuelType }

            if (null != countingDataEntry) {
                countingDataEntry.fuelVolume = countingDataEntry.fuelVolume.add(fuelConsumption.volume)
                countingDataEntry.moneySpent = countingDataEntry.moneySpent.add(fuelConsumption.volume.multiply(fuelConsumption.pricePerLitter))
                countingDataEntry.recordsCount++
                countingDataEntry.pricesSum = countingDataEntry.pricesSum.add(fuelConsumption.pricePerLitter)
            } else {
                val newCountingDataEntry = CountingDataForPrices(
                        fuelType = fuelConsumption.fuelType,
                        fuelVolume = fuelConsumption.volume,
                        moneySpent = (fuelConsumption.volume.multiply(fuelConsumption.pricePerLitter)),
                        recordsCount = 1,
                        pricesSum = fuelConsumption.pricePerLitter
                )
                countingData.add(newCountingDataEntry)
            }
        }

        return countingData.map {
            FuelPrices(
                    fuelType = it.fuelType,
                    totalPrice = it.moneySpent.divide(it.fuelVolume),
                    averagePrice = it.pricesSum.divide(it.recordsCount.toBigDecimal())
            )
        }
    }

    data class CountingDataForPrices(
            val fuelType: FuelType,
            var fuelVolume: BigDecimal = BigDecimal.valueOf(0),
            var moneySpent: BigDecimal = BigDecimal.valueOf(0),
            var recordsCount: Int = 0,
            var pricesSum: BigDecimal = BigDecimal.valueOf(0)
    )
}

