package io.spring.scs_kotlin_sample.service

import io.spring.scs_kotlin_sample.util.Factories
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class UserServiceTest {
    
    private lateinit var userService: UserService

    @BeforeEach
    fun setUp() {
        userService = UserService()
    }

    @Test
    fun unlock() {
        val userLocked = Factories.getUserDto(locked = true)
        val userUnLocked = userService.unlock(userLocked)
        Assertions.assertThat(userUnLocked).isNotNull
        Assertions.assertThat(userUnLocked.isLocked).isFalse
        
        val userNonLocked = Factories.getUserDto(locked = false)
        val sameUserNonLocked = userService.unlock(userNonLocked)
        Assertions.assertThat(sameUserNonLocked).isNotNull
        Assertions.assertThat(sameUserNonLocked.isLocked).isFalse
    }
}