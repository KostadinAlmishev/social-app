package io.almishev.matcherexample.exception

import feign.FeignException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletResponse


@RestControllerAdvice
class MatcherExceptionHandler {

    @ExceptionHandler(FeignException::class)
    fun handleFeignStatusException(e: FeignException, response: HttpServletResponse): String {
        response.status = e.status()
        return "feignError" // TODO: add normal feign exception handling
    }

}