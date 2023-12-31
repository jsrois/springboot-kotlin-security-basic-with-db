package net.jsrois.api

import net.jsrois.api.controllers.ProductDTO
import net.jsrois.api.controllers.UserDto
import net.jsrois.api.repositories.UserAccount
import net.jsrois.api.repositories.UserAccountRepository
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus.FORBIDDEN
import org.springframework.http.HttpStatus.OK
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.UUID

@SpringBootTest(webEnvironment = RANDOM_PORT)
class SpringbootKotlinSecurityBasicWithDbApplicationTests(
    @Autowired private val api: TestRestTemplate
) {
    @Autowired
    private lateinit var userAccountRepository: UserAccountRepository

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @BeforeEach
    fun setUp() {
        userAccountRepository.deleteAll()
        userAccountRepository.save(UserAccount("user", false, passwordEncoder.encode("password"), UUID.randomUUID()))
        userAccountRepository.save(UserAccount("admin", true, passwordEncoder.encode("adminpassword"), UUID.randomUUID()))
    }

    @Test
    fun `returns data when providing credentials with basic auth`() {
        with(
            api
                .withBasicAuth("user", "password")
                .getForEntity("/api/products", Array<ProductDTO>::class.java)
        ) {
            assertThat(statusCode, equalTo(OK))
        }
    }

    @Test
    fun `does not allow regular users to access user account data`() {
        with(
            api
                .withBasicAuth("user", "password")
                .getForEntity("/api/users", Array<UserDto>::class.java)
        ) {
            assertThat(statusCode, equalTo(FORBIDDEN))
        }

        with(
            api
                .withBasicAuth("admin", "adminpassword")
                .getForEntity("/api/users", Array<UserDto>::class.java)
        ) {
            assertThat(statusCode, equalTo(OK))
        }
    }
}
