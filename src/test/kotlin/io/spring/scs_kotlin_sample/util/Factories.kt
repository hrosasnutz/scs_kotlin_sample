package io.spring.scs_kotlin_sample.util

import io.github.serpro69.kfaker.Faker
import io.spring.scs_kotlin_sample.data.Employee
import io.spring.scs_kotlin_sample.data.Type
import io.spring.scs_kotlin_sample.dto.ErrorDto
import io.spring.scs_kotlin_sample.dto.TextDto
import io.spring.scs_kotlin_sample.dto.UserDto
import io.spring.scs_kotlin_sample.dto.enum.Action
import io.spring.scs_kotlin_sample.dto.enum.UserScope
import io.spring.scs_kotlin_sample.dto.obja.ObjectA
import io.spring.scs_kotlin_sample.dto.objb.ObjectB
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

object Factories {
    
    private val faker = Faker()
    
    fun getErrorDto(
        uuid: UUID = UUID.randomUUID(),
        message: String = faker.lorem.words()
    ): ErrorDto {
        return ErrorDto(uuid, message)
    }
    
    fun getTextDto(): TextDto {
        return TextDto(faker.lorem.words())
    }
    
    fun getUserDto(
        uuid: UUID = UUID.randomUUID(),
        scope: UserScope = UserScope.BOTH,
        locked: Boolean = faker.random.nextBoolean()
    ): UserDto {
        return UserDto(
            uuid,
            faker.internet.email(),
            scope,
            locked,
            faker.random.nextInt(),
            LocalDateTime.now().minusDays(10)
        )
    }
    
    fun getObjectA(
        data: String = "data",
        action: Action = Action.READ
    ): ObjectA {
        return ObjectA(UUID.randomUUID(), data, action)
    }
    
    fun getObjectB(
        uuid: UUID = UUID.randomUUID(),
        data: String = "data",
        createdAt: LocalDateTime = LocalDateTime.now()
    ): ObjectB {
        return ObjectB(uuid, data, createdAt)
    }
    
    fun getEmployee(
        id: UUID = UUID.randomUUID(),
        type: Type = Type.INTERNAL
    ): Employee {
        return Employee(
            id,
            faker.name.name(),
            faker.random.nextBoolean(),
            faker.random.nextInt(),
            faker.random.nextDouble(),
            type,
            LocalDate.now().minusYears(20)
        )
    }
}