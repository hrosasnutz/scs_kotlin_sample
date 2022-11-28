package io.spring.scs_kotlin_sample.dto.obja

import io.spring.scs_kotlin_sample.dto.enum.Action
import java.util.UUID

data class ObjectA(
    val uuid: UUID,
    val data: String,
    val action: Action = Action.READ,
)