package io.spring.scs_kotlin_sample.api

import io.spring.scs_kotlin_sample.dto.obja.ObjectA
import io.spring.scs_kotlin_sample.dto.TextDto
import io.spring.scs_kotlin_sample.dto.UserDto
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.http.ResponseEntity
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/scs")
class ScsController(
    private val streamBridge: StreamBridge
) {
    
    @PostMapping("/text")
    fun sendTextMessage(@RequestBody textDto: TextDto): ResponseEntity<Any> {
        val message = MessageBuilder.withPayload(textDto.message)
            .setHeader(KafkaHeaders.MESSAGE_KEY, textDto.message.toByteArray())
            .build()
        streamBridge.send("text-out-0", message)
        return ResponseEntity.ok().build()
    }
    
    @PostMapping("/user")
    fun sendUserMessage(@RequestBody userDto: UserDto): ResponseEntity<Any> {
        val message = MessageBuilder.withPayload(userDto)
            .setHeader(KafkaHeaders.MESSAGE_KEY, userDto.uuid.toString().toByteArray())
            .build()
        streamBridge.send("user-out-0", message)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/object")
    fun sendObjectMessage(@RequestBody objectA: ObjectA): ResponseEntity<Any> {
        val message = MessageBuilder.withPayload(objectA)
            .setHeader(KafkaHeaders.MESSAGE_KEY, objectA.uuid.toString().toByteArray())
            .build()
        streamBridge.send("object-out-0", message)
        return ResponseEntity.ok().build()
    }
}