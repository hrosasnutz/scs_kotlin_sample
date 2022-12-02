package io.spring.scs_kotlin_sample.dto

import java.util.UUID

data class ErrorDto(
    val uuid: UUID = UUID.randomUUID(),
    val message: String
)