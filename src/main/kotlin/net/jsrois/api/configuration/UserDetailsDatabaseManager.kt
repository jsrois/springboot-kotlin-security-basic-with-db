package net.jsrois.api.configuration

import net.jsrois.api.repositories.UserAccountRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull


@Service
class UserDetailsDatabaseManager(private val userAccountRepository: UserAccountRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String) =
        userAccountRepository.findByName(username).getOrNull()
            ?.let { UserAccountDetails(it) }
            ?: throw UsernameNotFoundException("Unable to find user ${username}.")

}