package io.almishev.matcherexample.controller

import io.almishev.matcherexample.controller.dto.ImpressionDto
import io.almishev.matcherexample.controller.dto.request.UnmatchRq
import io.almishev.matcherexample.controller.dto.response.ImpressRs
import io.almishev.matcherexample.controller.dto.response.MatchesRs
import io.almishev.matcherexample.service.ImpressionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1/matcher")
class MatcherController(
    private val impressionService: ImpressionService
) {

    @PostMapping("/impression")
    fun impress(@RequestBody impressionDto: ImpressionDto): ResponseEntity<ImpressRs> =
        ResponseEntity.ok(impressionService.impress(impressionDto))

    @PostMapping("/unmatch")
    fun unmatch(@RequestBody unmatchRq: UnmatchRq): ResponseEntity<Unit> {
        impressionService.unmatch(unmatchRq)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/matches")
    fun getMatches(@RequestParam userId: String): ResponseEntity<MatchesRs> =
        ResponseEntity.ok(impressionService.findMatches(userId))

    @GetMapping("/relevantPeople")
    fun getRelevantPeople(@RequestParam userId: String): ResponseEntity<List<String>> =
        ResponseEntity.ok(impressionService.findRelevantPeople(userId))

}