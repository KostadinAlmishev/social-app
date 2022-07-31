package io.almishev.matcherexample.domain

import java.time.LocalTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class ImpressionEntity(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val senderId: String,
    val recipientId: String,
    val interested: Boolean,
    val createdAt: LocalTime = LocalTime.now()
)