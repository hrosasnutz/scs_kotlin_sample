package io.spring.scs_kotlin_sample.api

import io.spring.scs_kotlin_sample.data.Employee
import io.spring.scs_kotlin_sample.data.EmployeeRepository
import io.spring.scs_kotlin_sample.dto.event.EmployeeEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.util.UUID

@RestController
@RequestMapping("/api/employees")
class EmployeeController(
    private val repository: EmployeeRepository,
    private val publisher: ApplicationEventPublisher
) {
    
    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): ResponseEntity<*> {
        return ResponseEntity.of(repository.findById(id))
    }
    
    @PostMapping
    @Transactional
    fun create(@RequestBody employee: Employee): ResponseEntity<Any> {
        val event = EmployeeEvent(repository.save(employee))
        publisher.publishEvent(event)
        return ResponseEntity.created(URI.create("/api/employees/${event.employee.id}")).build()
    }
}