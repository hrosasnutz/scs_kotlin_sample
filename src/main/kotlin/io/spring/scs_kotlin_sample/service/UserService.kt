package io.spring.scs_kotlin_sample.service

import io.spring.scs_kotlin_sample.dto.UserDto
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class UserService {
    
    private val logger = KotlinLogging.logger {  }
    
    fun unlock(userDto: UserDto): UserDto {
        return if(userDto.isLocked) {
            logger.info { "unlock user with uuid: ${userDto.uuid}" }
            UserDto(
                userDto.uuid,
                userDto.username,
                userDto.scope,
                    false,
                userDto.age,
                userDto.createdAt
            )
        } else {
            userDto
        }
    }
}