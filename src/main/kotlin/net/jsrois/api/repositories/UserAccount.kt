package net.jsrois.api.repositories

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name="user_accounts")
data class UserAccount(
    var name: String,
    var isAdminUser: Boolean,
    var password: String,
    @Id
    var id: UUID
)
