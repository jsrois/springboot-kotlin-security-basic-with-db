package net.jsrois.api.configuration

import net.jsrois.api.repositories.UserAccount
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserAccountDetails(private val user: UserAccount) : UserDetails {
    override fun getAuthorities() = if (user.isAdminUser) {
        listOf("ROLE_USER", "ROLE_ADMIN")
    } else {
        listOf("ROLE_USER")
    }.map { SimpleGrantedAuthority(it) }

    override fun getPassword() = user.password
    override fun getUsername() = user.name
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true
}