package io.almishev.matcherexample.feign

import io.almishev.matcherexample.feign.dto.GetRelevantUserIdsByFilterRequest
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

// TODO mappings
@FeignClient(url = "\${user-service.url}", name = "user-service")
@RequestMapping(path = ["/user"])
interface UserClient {

    @GetMapping("/TODO")
    fun getRelevantUserIdsByFilterURL(request: GetRelevantUserIdsByFilterRequest): List<String>

}