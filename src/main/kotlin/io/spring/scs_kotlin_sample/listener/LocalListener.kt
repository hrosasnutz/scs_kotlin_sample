package io.spring.scs_kotlin_sample.listener

import io.spring.scs_kotlin_sample.dto.event.EmployeeEvent
import mu.KotlinLogging
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class LocalListener(
    private val streamBridge: StreamBridge
) {
    
    private val logger = KotlinLogging.logger {  }
    
    @TransactionalEventListener
    fun employeeEvent(event: EmployeeEvent) {
        val message = MessageBuilder.withPayload(event.employee)
            .setHeader(KafkaHeaders.MESSAGE_KEY, event.employee.id.toString().toByteArray())
            .build();
        val sent = streamBridge.send("employee-out-0", message)
        logger.info { "Employee message sended? $sent" }
    }
}