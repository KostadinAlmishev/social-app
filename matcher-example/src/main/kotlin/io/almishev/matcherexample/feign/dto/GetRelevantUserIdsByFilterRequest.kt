package io.almishev.matcherexample.feign.dto

data class GetRelevantUserIdsByFilterRequest(
    val userId: String,
    val excludeIds: List<String>
)