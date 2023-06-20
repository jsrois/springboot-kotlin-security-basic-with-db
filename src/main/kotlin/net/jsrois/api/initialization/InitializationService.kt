package net.jsrois.api.initialization

import net.jsrois.api.configuration.CreateUserRequest
import net.jsrois.api.repositories.UserAccount
import net.jsrois.api.repositories.UserAccountRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class InitializationService(
        private val userAccountRepository: UserAccountRepository,
        private val passwordEncoder: PasswordEncoder
) {
    fun enabled(): Boolean = userAccountRepository.findAll().isEmpty()
    fun addUser(request: CreateUserRequest) {
        userAccountRepository.save(
                with(request) {
                    UserAccount(username,
                            true,
                            passwordEncoder.encode(password),
                            UUID.randomUUID())
                }
        )
    }
}