package net.jsrois.api.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserAccountRepository: JpaRepository<UserAccount, UUID> {
    fun findByName(name: String): Optional<UserAccount>
}