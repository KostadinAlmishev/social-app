package io.almishev.matcherexample.repository

import io.almishev.matcherexample.domain.ImpressionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ImpressionRepository : JpaRepository<ImpressionEntity, Long> {
    fun findAllBySenderIdAndInterested(senderId: String, interested: Boolean): List<ImpressionEntity>
    fun findAllByRecipientIdAndInterested(recipientId: String, interested: Boolean): List<ImpressionEntity>
    fun findFirstBySenderIdAndRecipientId(senderId: String, recipientId: String): ImpressionEntity?
}
