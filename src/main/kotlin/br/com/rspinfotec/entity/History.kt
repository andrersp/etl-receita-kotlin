package br.com.rspinfotec.entity

import io.micronaut.serde.annotation.Serdeable
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Serdeable
@Entity
@Table(name = "history")
data class History(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    var status: Int,
    @CreationTimestamp
    val startIn: LocalDateTime? = null,
    var finishedIn: LocalDateTime? = null
)
