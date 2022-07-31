package io.almishev.matcherexample.mapper

import io.almishev.matcherexample.controller.dto.ImpressionDto
import io.almishev.matcherexample.domain.ImpressionEntity

fun ImpressionEntity.toDto() = ImpressionDto(
    senderId = senderId,
    recipientId = recipientId,
    interested = interested
)

fun ImpressionDto.toEntity(id: Long = 0) = ImpressionEntity(
    id = id,
    senderId = senderId,
    recipientId = recipientId,
    interested = interested
)
