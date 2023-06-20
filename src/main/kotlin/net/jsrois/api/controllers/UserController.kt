package net.jsrois.api.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {
    @GetMapping("/api/users")
    fun allUsers(): List<UserDto> = emptyList()
}