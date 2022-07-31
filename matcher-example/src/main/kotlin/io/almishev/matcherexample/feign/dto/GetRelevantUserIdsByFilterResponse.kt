package io.almishev.matcherexample.feign.dto

data class GetRelevantUserIdsByFilterResponse (
    val relevantUserIds: Set<String> = emptySet()
)