package ee.swedbank.fuelconsumptionapp.domain.entity

import ee.swedbank.fuelconsumptionapp.common.annotation.NoArg
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

enum class FuelType {
    PETROL_95,
    PETROL_98,
    DIESEL,
    AUTO_GAS
}

@NoArg
@Entity
@Table(name = "fuel_consumptions")
class FuelConsumption(

        @field:NotNull
        @Enumerated(EnumType.STRING)
        @Column(name = "fuel_type")
        var fuelType: FuelType,

        @field:NotNull
        @field:Min(value = 0)
        @field:Max(value = 50)
        @Column(name = "price_per_litter")
        var pricePerLitter: BigDecimal,

        @field:NotNull
        @field:Min(value = 1)
        @Column(name = "volume")
        var volume: BigDecimal,

        @field:NotNull
        @Column(name = "date")
        var date: LocalDate,

        @field:NotNull
        @field:Min(value = 1)
        @Column(name = "driver_id")
        var driverId: Int

) : AuditEntity() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as FuelConsumption

        if (fuelType != other.fuelType) return false
        if (pricePerLitter != other.pricePerLitter) return false
        if (volume != other.volume) return false
        if (date != other.date) return false
        if (driverId != other.driverId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + fuelType.hashCode()
        result = 31 * result + pricePerLitter.hashCode()
        result = 31 * result + volume.hashCode()
        result = 31 * result + date.hashCode()
        result = 31 * result + driverId.hashCode()
        return result
    }

    override fun toString(): String {
        return "FuelConsumption(fuelType=$fuelType, pricePerLitter=$pricePerLitter, volume=$volume, date=$date, driverId=$driverId)"
    }
}