package ee.swedbank.fuelconsumptionapp.domain.dto

import com.fasterxml.jackson.annotation.JsonFormat
import ee.swedbank.fuelconsumptionapp.common.annotation.ValidFuelType
import ee.swedbank.fuelconsumptionapp.domain.entity.FuelConsumption
import ee.swedbank.fuelconsumptionapp.domain.entity.FuelType
import java.math.BigDecimal
import java.time.LocalDate
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class FuelConsumptionDto(

        @field:NotNull
        @ValidFuelType
        var fuelType: String,

        @field:NotNull
        @field:Min(value = 0)
        @field:Max(value = 50)
        val pricePerLitter: BigDecimal,

        @field:NotNull
        @field:Min(value = 1)
        val volume: BigDecimal,

        @JsonFormat(pattern = "yyyy-MM-dd")
        @field:NotNull
        val date: LocalDate,

        @field:NotNull
        @field:Min(value = 1)
        val driverId: Int
)

data class FuelConsumptionUpdateDto(

        @ValidFuelType
        var fuelType: String?,

        @field:Min(value = 0)
        @field:Max(value = 50)
        val pricePerLitter: BigDecimal?,

        @field:Min(value = 1)
        val volume: BigDecimal?,

        val date: LocalDate?,

        @field:Min(value = 1)
        val driverId: Int?
)

data class FuelConsumptionStatisticDto(

        var fuelType: String,
        val pricePerLitter: BigDecimal,
        val volume: BigDecimal,

        @JsonFormat(pattern = "yyyy-MM-dd")
        val date: LocalDate,

        val driverId: Int,
        val totalPrice: BigDecimal?
)

data class FuelPrices(

        var fuelType: FuelType,
        var totalPrice: BigDecimal,
        var averagePrice: BigDecimal
)


fun FuelConsumption.toDto(): FuelConsumptionDto =
        FuelConsumptionDto(
                fuelType = this.fuelType.toString(),
                pricePerLitter = this.pricePerLitter,
                volume = this.volume,
                date = this.date,
                driverId = this.driverId
        )

fun FuelConsumption.toStatisticDto(fuelPrices: List<FuelPrices>): FuelConsumptionStatisticDto =
        FuelConsumptionStatisticDto(
                fuelType = this.fuelType.toString(),
                pricePerLitter = this.pricePerLitter,
                volume = this.volume,
                date = this.date,
                driverId = this.driverId,
                totalPrice = fuelPrices.findLast { it.fuelType == this.fuelType }!!.totalPrice
        )

fun FuelConsumptionDto.toEntity(): FuelConsumption =
        FuelConsumption(
                fuelType = FuelType.valueOf(this.fuelType),
                pricePerLitter = this.pricePerLitter,
                volume = this.volume,
                date = this.date,
                driverId = this.driverId
        )