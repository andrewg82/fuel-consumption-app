package ee.swedbank.fuelconsumptionapp.service.impl

import ee.swedbank.fuelconsumptionapp.domain.dto.FuelConsumptionUpdateDto
import ee.swedbank.fuelconsumptionapp.domain.entity.FuelConsumption
import ee.swedbank.fuelconsumptionapp.domain.entity.FuelType
import ee.swedbank.fuelconsumptionapp.repository.FuelConsumptionRepository
import ee.swedbank.fuelconsumptionapp.service.FuelConsumptionService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import javax.persistence.EntityNotFoundException

@Service
class DefaultFuelConsumptionService(
        private val fuelConsumptionRepository: FuelConsumptionRepository
) : FuelConsumptionService {

    override fun createFuelConsumption(fuelConsumption: FuelConsumption): FuelConsumption =
            fuelConsumptionRepository.save(fuelConsumption)

    @Transactional
    override fun createFuelConsumptions(fuelConsumptions: List<FuelConsumption>): List<FuelConsumption> =
            fuelConsumptionRepository.saveAll(fuelConsumptions)

    override fun getFuelConsumption(id: Long): FuelConsumption =
            fuelConsumptionRepository.findById(id).orElseThrow { EntityNotFoundException("No FuelConsumption found with id: $id") }

    override fun getAllFuelConsumptions(): List<FuelConsumption> =
            fuelConsumptionRepository.findAll()

    override fun updateFuelConsumption(fuelConsumptionUpdateDto: FuelConsumptionUpdateDto, id: Long): FuelConsumption {
        val fuelConsumptionToUpdate = fuelConsumptionRepository.findById(id)
        if (!fuelConsumptionToUpdate.isPresent) {
            throw EntityNotFoundException("No FuelConsumption found with id: $id")
        }

        with(fuelConsumptionToUpdate.get()) {
            fuelConsumptionUpdateDto.fuelType?.let { fuelType = FuelType.valueOf(it) }
            fuelConsumptionUpdateDto.pricePerLitter?.let { pricePerLitter = it }
            fuelConsumptionUpdateDto.volume?.let { volume = it }
            fuelConsumptionUpdateDto.date?.let { date = it }
            fuelConsumptionUpdateDto.driverId?.let { driverId = it }
        }
        return fuelConsumptionRepository.save(fuelConsumptionToUpdate.get())
    }

    override fun deleteFuelConsumption(id: Long) {
        val fuelConsumptionToDelete = fuelConsumptionRepository.findById(id)
        fuelConsumptionRepository.delete(fuelConsumptionToDelete.orElseThrow { EntityNotFoundException("No FuelConsumption found with id: $id") })
    }

    override fun getAllByDriverId(driverId: Int): List<FuelConsumption> =
            fuelConsumptionRepository.findAllByDriverId(driverId)

    override fun getAllByMonth(date: LocalDate): List<FuelConsumption> =
            fuelConsumptionRepository.getAllByMonth(date)


    override fun getAllByMonthAndDriverId(date: LocalDate, driverId: Int): List<FuelConsumption> =
            fuelConsumptionRepository.getAllByMonthAndDriverId(date, driverId)
}