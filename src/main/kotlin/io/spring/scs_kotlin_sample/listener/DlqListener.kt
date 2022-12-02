package io.spring.scs_kotlin_sample.listener

import io.spring.scs_kotlin_sample.dto.ErrorDto
import mu.KotlinLogging
import org.springframework.context.annotation.Bean
import org.springframework.messaging.Message
import org.springframework.stereotype.Component

@Component
class DlqListener {
    
    private val logger = KotlinLogging.logger {  }

    @Bean
    fun customDlqThrowException(): (ErrorDto) -> Unit {
        return {
            logger.info { "throwing dlq exception : ${it.message}" }
            throw RuntimeException(it.message)
        }
    }

    @Bean
    fun customDlqExceptionHandler(): (Message<ErrorDto>) -> Unit {
        return {
            logger.info { "custom dlq message: $it" }
        }
    }

    @Bean
    fun generalDlqThrowException(): (ErrorDto) -> Unit {
        return {
            logger.info { "throwing general dlq exception : ${it.message}" }
            throw RuntimeException(it.message)
        }
    }

    @Bean
    fun generalDlqExceptionHandler(): (Message<ErrorDto>) -> Unit {
        return {
            logger.info { "general dlq message: $it" }
        }
    }
}