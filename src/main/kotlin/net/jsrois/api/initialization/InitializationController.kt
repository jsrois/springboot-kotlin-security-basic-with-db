package net.jsrois.api.initialization

import jakarta.annotation.PostConstruct
import net.jsrois.api.configuration.CreateUserRequest
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.server.ResponseStatusException

@Controller
class InitializationController(private val initializationService: InitializationService) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("/init")
    fun initialize(): String {
        if (!initializationService.enabled()) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
        return "register"
    }

    @PostMapping("/register")
    fun registerUser(@RequestParam username: String, @RequestParam password: String) {
        val request = CreateUserRequest(username,password)
        logger.info("Creating new user $request")
        initializationService.addUser(request)
    }


    @PostConstruct
    private fun info(){

        if (initializationService.enabled()) {
            logger.info("⚡️ You can register an admin user at [localhost:${port}/init]!")
        }
    }

    @Value("\${server.port}")
    private lateinit var port: Integer

}