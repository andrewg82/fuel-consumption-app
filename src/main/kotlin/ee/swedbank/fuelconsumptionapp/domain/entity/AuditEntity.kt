package ee.swedbank.fuelconsumptionapp.domain.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.ZonedDateTime
import javax.persistence.*


@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AuditEntity : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null

    @CreatedDate
    @Column(name = "created_at")
    var createdAt: ZonedDateTime? = null

    @LastModifiedDate
    @Column(name = "changed_at")
    var changedAt: ZonedDateTime? = null

    @PrePersist
    fun prePersist() {
        createdAt = ZonedDateTime.now()
    }

    @PreUpdate
    fun preUpdate() {
        changedAt = ZonedDateTime.now()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AuditEntity

        if (id != other.id) return false

        return true
    }

    override fun hashCode() = id?.hashCode() ?: 0

    override fun toString() = "AuditEntity(id=$id, createdAt=$createdAt, changedAt=$changedAt)"
}
