package mn.data


import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@MicronautTest
class HomeTest {
    @Inject @field:Client("/")
    lateinit var client: HttpClient

    @Test
    fun `should get formatted value for datetime from entity endpoint`() {
        val request: HttpRequest<String> = HttpRequest.GET("/entity-user")
        val entityUserAsString = client.toBlocking().retrieve(request)
        assertThat(entityUserAsString).contains("2019-10-28T11:30:00.123Z")
    }

    @Test
    fun `should get formatted value for datetime from pojo endpoint`() {
        val request: HttpRequest<String> = HttpRequest.GET("/pojo-user")
        val pojoUserAsString = client.toBlocking().retrieve(request)
        assertThat(pojoUserAsString).contains("2019-10-28T11:30:00.123Z")
    }
}