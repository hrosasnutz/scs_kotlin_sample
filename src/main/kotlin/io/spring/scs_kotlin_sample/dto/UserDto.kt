package io.spring.scs_kotlin_sample.dto

import io.spring.scs_kotlin_sample.dto.enum.UserScope
import java.time.LocalDateTime
import java.util.UUID

data class UserDto(
    val uuid: UUID,
    val username: String,
    val scope: UserScope,
    val isLocked: Boolean,
    val age: Int,
    val createdAt: LocalDateTime
)