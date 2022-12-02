package io.spring.scs_kotlin_sample.listener

import io.spring.scs_kotlin_sample.data.Employee
import io.spring.scs_kotlin_sample.dto.objb.ObjectB
import io.spring.scs_kotlin_sample.dto.UserDto
import io.spring.scs_kotlin_sample.service.UserService
import mu.KotlinLogging
import org.springframework.context.annotation.Bean
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class KafkaListener(
    private val userService: UserService
) {
    
    private val logger = KotlinLogging.logger {  }
    
    @Bean
    fun logText(): (Message<String>) -> Unit {
        return {
            logger.info { "logging text message: $it" }
        }
    }
    
    @Bean
    fun logUser(): (UserDto) -> Unit {
        return {
            logger.info { "logging user: $it" }
        }
    }
    
    @Bean
    fun unlockUser(): (Message<UserDto>) -> (Message<UserDto>) {
        return {
            val user = userService.unlock(it.payload)
            MessageBuilder.withPayload(user)
                .setHeader(KafkaHeaders.MESSAGE_KEY, user.uuid.toString().toByteArray())
                .build()
        }
    }
    
    @Bean
    fun logUserUnlocked(): (Flux<Message<UserDto>>) -> Mono<Void> {
        return {
            flux -> flux
                .map { it.payload }
                .doOnNext { logger.info { "logging user unlocked message: $it" } }
                .then()
        }
    }
    
    @Bean
    fun logObject(): (Flux<ObjectB>) -> Mono<Void> {
        return {
            flux -> flux
                .doOnNext { logger.info { "logging object: $it" } }
                .then()
        }
    }
    
    @Bean
    fun logEmployee(): (Employee) -> Unit {
        return {
            logger.info { "logging employee: $it" }
        }
    }
}