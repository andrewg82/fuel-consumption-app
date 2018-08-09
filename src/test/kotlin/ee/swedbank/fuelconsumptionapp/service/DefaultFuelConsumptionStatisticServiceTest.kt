package ee.swedbank.fuelconsumptionapp.service

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import ee.swedbank.fuelconsumptionapp.domain.dto.FuelConsumptionStatisticDto
import ee.swedbank.fuelconsumptionapp.domain.dto.FuelPrices
import ee.swedbank.fuelconsumptionapp.domain.entity.FuelConsumption
import ee.swedbank.fuelconsumptionapp.domain.entity.FuelType
import ee.swedbank.fuelconsumptionapp.service.impl.DefaultFuelConsumptionStatisticService
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.EntityNotFoundException

class DefaultFuelConsumptionStatisticServiceTest {
    private lateinit var fuelConsumptionService: FuelConsumptionService
    private lateinit var defaultFuelConsumptionStatisticService: DefaultFuelConsumptionStatisticService

    private val TEST_DATA_ALL = listOf(
            FuelConsumption(
                    fuelType = FuelType.PETROL_95,
                    pricePerLitter = BigDecimal.valueOf(1),
                    volume = BigDecimal.valueOf(500),
                    date = LocalDate.of(2018, 5, 15),
                    driverId = 1),
            FuelConsumption(
                    fuelType = FuelType.PETROL_98,
                    pricePerLitter = BigDecimal.valueOf(1.3),
                    volume = BigDecimal.valueOf(600),
                    date = LocalDate.of(2018, 5, 18),
                    driverId = 2),
            FuelConsumption(
                    fuelType = FuelType.PETROL_95,
                    pricePerLitter = BigDecimal.valueOf(2),
                    volume = BigDecimal.valueOf(500),
                    date = LocalDate.of(2018, 5, 20),
                    driverId = 1),
            FuelConsumption(
                    fuelType = FuelType.PETROL_98,
                    pricePerLitter = BigDecimal.valueOf(1.8),
                    volume = BigDecimal.valueOf(450),
                    date = LocalDate.of(2018, 6, 15),
                    driverId = 2),
            FuelConsumption(
                    fuelType = FuelType.PETROL_95,
                    pricePerLitter = BigDecimal.valueOf(1.9),
                    volume = BigDecimal.valueOf(450),
                    date = LocalDate.of(2018, 6, 20),
                    driverId = 1)
    )

    private val TEST_DATA_ALL_BY_MONTH = listOf(
            FuelConsumption(
                    fuelType = FuelType.PETROL_95,
                    pricePerLitter = BigDecimal.valueOf(1),
                    volume = BigDecimal.valueOf(500),
                    date = LocalDate.of(2018, 5, 15),
                    driverId = 1),
            FuelConsumption(
                    fuelType = FuelType.PETROL_98,
                    pricePerLitter = BigDecimal.valueOf(1.3),
                    volume = BigDecimal.valueOf(600),
                    date = LocalDate.of(2018, 5, 18),
                    driverId = 2),
            FuelConsumption(
                    fuelType = FuelType.PETROL_95,
                    pricePerLitter = BigDecimal.valueOf(2),
                    volume = BigDecimal.valueOf(500),
                    date = LocalDate.of(2018, 5, 20),
                    driverId = 1)
    )

    private val EXPECTED_DATA_FOR_TOTAL_SPENT_AMOUNT = mapOf<LocalDate, Double>(
            LocalDate.of(2018, 5, 1) to 2280.00,
            LocalDate.of(2018, 6, 1) to 1665.00)

    private val EXPECTED_DATA_FOR_MONTH_STATISTIC = listOf(
            FuelConsumptionStatisticDto(
                    fuelType = "PETROL_95",
                    pricePerLitter = BigDecimal.valueOf(1),
                    volume = BigDecimal.valueOf(500),
                    date = LocalDate.of(2018, 5, 15),
                    driverId = 1,
                    totalPrice = BigDecimal.valueOf(1.5)
            ),
            FuelConsumptionStatisticDto(
                    fuelType = "PETROL_98",
                    pricePerLitter = BigDecimal.valueOf(1.3),
                    volume = BigDecimal.valueOf(600),
                    date = LocalDate.of(2018, 5, 18),
                    driverId = 2,
                    totalPrice = BigDecimal.valueOf(1.3)
            ),
            FuelConsumptionStatisticDto(
                    fuelType = "PETROL_95",
                    pricePerLitter = BigDecimal.valueOf(2),
                    volume = BigDecimal.valueOf(500),
                    date = LocalDate.of(2018, 5, 20),
                    driverId = 1,
                    totalPrice = BigDecimal.valueOf(1.5)
            )
    )

    private val EXPECTED_DATA_FOR_AGGREGATED_STATISTIC = mapOf<LocalDate, List<FuelPrices>>(
            LocalDate.of(2018, 5, 1) to listOf(
                    FuelPrices(
                            fuelType = FuelType.PETROL_95,
                            totalPrice = BigDecimal.valueOf(1.5),
                            averagePrice = BigDecimal.valueOf(1.5)
                    ),
                    FuelPrices(
                            fuelType = FuelType.PETROL_98,
                            totalPrice = BigDecimal.valueOf(1.3),
                            averagePrice = BigDecimal.valueOf(1.3)
                    )
            ),
            LocalDate.of(2018, 6, 1) to listOf(
                    FuelPrices(
                            fuelType = FuelType.PETROL_98,
                            totalPrice = BigDecimal.valueOf(1.8),
                            averagePrice = BigDecimal.valueOf(1.8)
                    ),
                    FuelPrices(
                            fuelType = FuelType.PETROL_95,
                            totalPrice = BigDecimal.valueOf(1.9),
                            averagePrice = BigDecimal.valueOf(1.9)
                    )
            )
    )

    @Before
    fun init() {
        fuelConsumptionService = mock { }
        defaultFuelConsumptionStatisticService = DefaultFuelConsumptionStatisticService(fuelConsumptionService)
    }

    @Test(expected = EntityNotFoundException::class)
    fun getTotalSpentAmountOfMoney_ThrowsException_IfEmptyList() {
        defaultFuelConsumptionStatisticService.getTotalSpentAmountOfMoney(emptyList())
    }

    @Test
    fun getTotalSpentAmountOfMoney_Logic() {
        val expectedData = defaultFuelConsumptionStatisticService.getTotalSpentAmountOfMoney(
                TEST_DATA_ALL)
        assert(expectedData == EXPECTED_DATA_FOR_TOTAL_SPENT_AMOUNT)
    }

    @Test(expected = EntityNotFoundException::class)
    fun getMonthStatistic_ThrowsException_IfEmptyList() {
        defaultFuelConsumptionStatisticService.getMonthStatistic(emptyList())
    }

    @Test
    fun getMonthStatistic_Logic() {
        val expectedData = defaultFuelConsumptionStatisticService.getMonthStatistic(
                TEST_DATA_ALL_BY_MONTH)
        assert(expectedData == EXPECTED_DATA_FOR_MONTH_STATISTIC)
    }

    @Test(expected = EntityNotFoundException::class)
    fun getAggregatedStatisticByFuelType_ThrowsException_IfEmptyList() {
        defaultFuelConsumptionStatisticService.getAggregatedStatisticByFuelType(emptyList())
    }

    @Test
    fun getAggregatedStatisticByFuelType_Logic() {
        val expectedData = defaultFuelConsumptionStatisticService.getAggregatedStatisticByFuelType(
                TEST_DATA_ALL)
        assert(expectedData == EXPECTED_DATA_FOR_AGGREGATED_STATISTIC)
    }

    @Test
    fun getAllByDriverIdOrAll_ServiceCall() {
        defaultFuelConsumptionStatisticService.getAllByDriverIdOrAll(1)
        verify(fuelConsumptionService).getAllByDriverId(1)

        defaultFuelConsumptionStatisticService.getAllByDriverIdOrAll(null)
        verify(fuelConsumptionService).getAllFuelConsumptions()
    }

    @Test
    fun getAllByMonthAndDriverIdOrAllByMonth_ServiceCall() {
        defaultFuelConsumptionStatisticService.getAllByMonthAndDriverIdOrAllByMonth(LocalDate.of(2018, 5, 1), 1)
        verify(fuelConsumptionService).getAllByMonthAndDriverId(LocalDate.of(2018, 5, 1), 1)

        defaultFuelConsumptionStatisticService.getAllByMonthAndDriverIdOrAllByMonth(LocalDate.of(2018, 5, 1), null)
        verify(fuelConsumptionService).getAllByMonth(LocalDate.of(2018, 5, 1))
    }
}