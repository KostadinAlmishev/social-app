package io.almishev.matcherexample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class MatcherExampleApplication

fun main(args: Array<String>) {
    runApplication<MatcherExampleApplication>(*args)
}
