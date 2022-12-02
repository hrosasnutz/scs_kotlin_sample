package io.spring.scs_kotlin_sample.listener

import io.spring.scs_kotlin_sample.service.UserService
import io.spring.scs_kotlin_sample.util.Factories
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.support.MessageBuilder
import reactor.core.publisher.Flux
import reactor.test.StepVerifier

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class KafkaListenerTest {
    
    private lateinit var kafkaListener: KafkaListener
    private lateinit var userService: UserService

    @BeforeEach
    fun setUp() {
        userService = mock {
            on { unlock(any()) } doReturn Factories.getUserDto(locked = false)
        }
        kafkaListener = KafkaListener(userService)
    }

    @Test
    fun logText() {
        val dto = Factories.getTextDto()
        Assertions.assertThatCode {
            kafkaListener.logText()(MessageBuilder
                .withPayload(dto.message)
                .setHeader(KafkaHeaders.MESSAGE_KEY, dto.message.toByteArray())
                .build()
            )
        }.doesNotThrowAnyException()
    }

    @Test
    fun logUser() {
        val dto = Factories.getUserDto()
        Assertions.assertThatCode {
            kafkaListener.logUser()(dto)
        }.doesNotThrowAnyException()
    }

    @Test
    fun unlockUser() {
        val dto = Factories.getUserDto()
        val message = kafkaListener.unlockUser()(
            MessageBuilder.withPayload(dto)
                .setHeader(KafkaHeaders.MESSAGE_KEY, dto.uuid.toString().toByteArray())
                .build()
        )
        Assertions.assertThat(message).isNotNull
    }

    @Test
    fun logUserUnlocked() {
        val dto = Factories.getUserDto(locked = false)
        val message = MessageBuilder.withPayload(dto)
            .setHeader(KafkaHeaders.MESSAGE_KEY, dto.uuid.toString().toByteArray())
            .build()
        val input = Flux.just(message)
        val output = kafkaListener.logUserUnlocked()(input)
        StepVerifier.withVirtualTime { output }
            .verifyComplete()
    }

    @Test
    fun logObject() {
        val dto = Factories.getObjectB()
        val input = Flux.just(dto)
        val output = kafkaListener.logObject()(input)
        StepVerifier.withVirtualTime { output }
            .verifyComplete()
    }

    @Test
    fun logEmployee() {
        val dto = Factories.getEmployee()
        Assertions.assertThatCode { 
            kafkaListener.logEmployee()(dto)
        }.doesNotThrowAnyException()
    }
}