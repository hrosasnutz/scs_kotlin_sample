package io.spring.scs_kotlin_sample.dto.objb

import java.time.LocalDateTime
import java.util.UUID

data class ObjectB(
    val uuid: UUID,
    val data: String,
    val createdAt: LocalDateTime?
)