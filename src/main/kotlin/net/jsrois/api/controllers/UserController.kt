package net.jsrois.api.controllers

import net.jsrois.api.repositories.UserAccount
import net.jsrois.api.repositories.UserAccountRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userAccountRepository: UserAccountRepository) {
    @GetMapping("/api/users")
    fun allUsers(): List<UserDto> = userAccountRepository.findAll().map { with(it) { UserDto(name, id.toString()) } }
}