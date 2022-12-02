package io.spring.scs_kotlin_sample.api

import io.spring.scs_kotlin_sample.dto.ErrorDto
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.http.ResponseEntity
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/dlq")
class DlqController(
    private val streamBridge: StreamBridge
) {

    @PostMapping("/custom")
    fun sendDlqException(@RequestBody errorDto: ErrorDto): ResponseEntity<Any> {
        val message = MessageBuilder.withPayload(errorDto)
            .setHeader(KafkaHeaders.MESSAGE_KEY, errorDto.uuid.toString().toByteArray())
            .build()
        streamBridge.send("customDlqException-out-0", message)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/general")
    fun sendGeneralDlqException(@RequestBody errorDto: ErrorDto): ResponseEntity<Any> {
        val message = MessageBuilder.withPayload(errorDto)
            .setHeader(KafkaHeaders.MESSAGE_KEY, errorDto.uuid.toString().toByteArray())
            .build()
        streamBridge.send("generalDlqException-out-0", message)
        return ResponseEntity.ok().build()
    }
}