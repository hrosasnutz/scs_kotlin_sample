package io.spring.scs_kotlin_sample.api

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import io.spring.scs_kotlin_sample.ScsKotlinSampleApplicationTests
import io.spring.scs_kotlin_sample.util.Factories

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@AutoConfigureMockMvc
class ScsControllerTest : ScsKotlinSampleApplicationTests() {
    
    private lateinit var mockMvc: MockMvc
    
    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @BeforeEach
    fun setUp(wac: WebApplicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
            .build()
    }

    @Test
    fun sendTextMessage() {
        mockMvc.post("/api/scs/text") {
            this.contentType = MediaType.APPLICATION_JSON
            this.content = objectMapper.writeValueAsString(Factories.getTextDto())
        }.andExpect {
            this.status { isOk() }
        }
    }

    @Test
    fun sendUserMessage() {
        mockMvc.post("/api/scs/user") {
            this.contentType = MediaType.APPLICATION_JSON
            this.content = objectMapper.writeValueAsString(Factories.getUserDto())
        }.andExpect {
            this.status { isOk() }
        }
    }

    @Test
    fun sendObjectMessage() {
        mockMvc.post("/api/scs/object") {
            this.contentType = MediaType.APPLICATION_JSON
            this.content = objectMapper.writeValueAsString(Factories.getObjectA())
        }.andExpect {
            this.status { isOk() }
        }
    }
}