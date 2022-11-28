package io.spring.scs_kotlin_sample.dto.event

import io.spring.scs_kotlin_sample.data.Employee
import org.springframework.context.ApplicationEvent

data class EmployeeEvent(val employee: Employee) : ApplicationEvent(employee) {
    
}