package io.spring.scs_kotlin_sample.api

import com.fasterxml.jackson.databind.ObjectMapper
import io.spring.scs_kotlin_sample.ScsKotlinSampleApplicationTests
import io.spring.scs_kotlin_sample.data.EmployeeRepository
import io.spring.scs_kotlin_sample.util.Factories
import org.apache.http.client.methods.RequestBuilder
import org.assertj.core.internal.bytebuddy.matcher.EqualityMatcher
import org.hamcrest.core.IsEqual
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext

@AutoConfigureMockMvc
@DirtiesContext
internal class EmployeeControllerTest : ScsKotlinSampleApplicationTests() {
    
    private lateinit var mockMvc: MockMvc
    
    @Autowired
    private lateinit var objectMapper: ObjectMapper
    
    @Autowired
    private lateinit var repository: EmployeeRepository

    @BeforeEach
    fun setUp(wac: WebApplicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
            .build()
    }

    @Test
    @Transactional
    fun getById() {
        val employee = repository.save(Factories.getEmployee())
        mockMvc.get("/api/employees/{id}", employee.id) {
            this.accept = MediaType.APPLICATION_JSON
        }.andExpect { 
            this.status { isOk() }
            this.content { contentTypeCompatibleWith(MediaType.APPLICATION_JSON) }
            this.content { jsonPath("$.id", IsEqual.equalTo("${employee.id}")) }
        }
    }

    @Test
    fun create() {
        mockMvc.post("/api/employees") {
            this.contentType = MediaType.APPLICATION_JSON
            this.content = objectMapper.writeValueAsString(Factories.getEmployee())
        }.andExpect { 
            this.status { isCreated() }
            this.header { exists(HttpHeaders.LOCATION) }
        }
    }
}