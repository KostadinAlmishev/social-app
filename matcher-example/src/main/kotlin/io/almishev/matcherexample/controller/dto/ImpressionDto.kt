package io.almishev.matcherexample.controller.dto

data class ImpressionDto (
    val senderId: String,
    val recipientId: String,
    val interested: Boolean
)