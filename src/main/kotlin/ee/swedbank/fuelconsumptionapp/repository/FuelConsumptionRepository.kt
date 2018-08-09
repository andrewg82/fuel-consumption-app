package ee.swedbank.fuelconsumptionapp.repository

import ee.swedbank.fuelconsumptionapp.domain.entity.FuelConsumption
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDate

interface FuelConsumptionRepository : JpaRepository<FuelConsumption, Long> {

    fun findAllByDriverId(driverId: Int): List<FuelConsumption>

    @Query("SELECT fc FROM FuelConsumption fc WHERE YEAR(fc.date) = YEAR(:date) AND MONTH(fc.date) = MONTH(:date)")
    fun getAllByMonth(@Param("date") date: LocalDate): List<FuelConsumption>

    @Query("SELECT fc FROM FuelConsumption fc WHERE YEAR(fc.date) = YEAR(:date) AND MONTH(fc.date) = MONTH(:date) AND fc.driverId = :driverId")
    fun getAllByMonthAndDriverId(@Param("date") date: LocalDate, @Param("driverId") driverId: Int): List<FuelConsumption>
}