package io.almishev.matcherexample.service

import io.almishev.matcherexample.controller.dto.ImpressionDto
import io.almishev.matcherexample.controller.dto.request.UnmatchRq
import io.almishev.matcherexample.controller.dto.response.ImpressRs
import io.almishev.matcherexample.controller.dto.response.MatchesRs
import io.almishev.matcherexample.feign.UserClient
import io.almishev.matcherexample.feign.dto.GetRelevantUserIdsByFilterRequest
import io.almishev.matcherexample.mapper.toEntity
import io.almishev.matcherexample.repository.ImpressionRepository
import org.springframework.stereotype.Service

@Service
class ImpressionService(
    private val userClient: UserClient,
    private val impressionRepository: ImpressionRepository
) {
    fun impress(impression: ImpressionDto): ImpressRs {
        updateImpression(impression)

        if (!impression.interested) {
            return ImpressRs(isMutual = false)
        }

        val isMutual = impressionRepository.findFirstBySenderIdAndRecipientId(
            senderId = impression.recipientId,
            recipientId = impression.senderId
        )?.interested ?: false

        return ImpressRs(isMutual = isMutual)
    }

    fun unmatch(unmatchRq: UnmatchRq) {
        updateImpression(ImpressionDto(
            senderId = unmatchRq.senderId,
            recipientId = unmatchRq.recipientId,
            interested = false
        ))
    }

    fun findRelevantPeople(userId: String): List<String> {
        val dislikedPeople = impressionRepository.findAllBySenderIdAndInterested(senderId = userId, interested = false)
            .map { it.recipientId }

        return userClient.getRelevantUserIdsByFilterURL(
            GetRelevantUserIdsByFilterRequest(
                userId = userId,
                excludeIds = dislikedPeople
            )
        )
    }

    fun findMatches(userId: String): MatchesRs {
        val usersImpressed = impressionRepository.findAllBySenderIdAndInterested(senderId = userId, interested = true)
            .mapTo(mutableSetOf()) { it.recipientId }
        val usersImpressedBy =
            impressionRepository.findAllByRecipientIdAndInterested(recipientId = userId, interested = true)
                .mapTo(mutableSetOf()) { it.senderId }

        val matches = usersImpressed.intersect(usersImpressedBy)

        return MatchesRs(matchIds = matches)
    }

    private fun updateImpression(impression: ImpressionDto) {
        val oldImpression = impressionRepository.findFirstBySenderIdAndRecipientId(senderId = impression.senderId, recipientId = impression.recipientId)
        impressionRepository.save(impression.toEntity(id = oldImpression?.id ?: 0))
    }
}