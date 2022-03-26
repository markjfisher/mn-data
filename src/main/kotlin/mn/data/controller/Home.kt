package mn.data.controller

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import mn.data.domain.User
import mn.data.domain.UserPOJO
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

@Controller
class Home {
    private val staticDate = Instant.parse("2019-10-28T11:30:00.12345Z").atZone(ZoneOffset.UTC)
    @Get("/entity-user")
    fun entityUser(): User {
        return User(id = 0, boughtAt = LocalDateTime.from(staticDate))
    }

    @Get("/pojo-user")
    fun pojoUser(): UserPOJO {
        return UserPOJO(id = 0, boughtAt = LocalDateTime.from(staticDate))
    }

}

