package io.spring.scs_kotlin_sample.listener

import io.spring.scs_kotlin_sample.dto.ErrorDto
import mu.KotlinLogging
import org.springframework.context.annotation.Bean
import org.springframework.messaging.support.ErrorMessage
import org.springframework.stereotype.Component

@Component
class ExceptionListener {
    
    private val logger = KotlinLogging.logger {  }

    @Bean
    fun customThrowException(): (ErrorDto) -> Unit {
        return {
            logger.info { "throwing custom exception: ${it.message}" }
            throw RuntimeException(it.message)
        }
    }

    @Bean
    fun customExceptionHandler(): (ErrorMessage) -> Unit {
        return {
            logger.error { "catch custom error: ${it.payload.message}" }
        }
    }

    @Bean
    fun generalThrowException(): (ErrorDto) -> Unit {
        return {
            logger.info { "throwing general exception : ${it.message}" }
            throw RuntimeException(it.message)
        }
    }

    @Bean
    fun generalExceptionHandler(): (ErrorMessage) -> Unit {
        return {
            logger.error { "catch general error." }
        }
    }
}