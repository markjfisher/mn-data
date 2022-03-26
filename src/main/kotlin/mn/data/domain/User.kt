package mn.data.domain

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class User(
    @Id @GeneratedValue var id: Long,

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    var boughtAt: LocalDateTime? = null
)

data class UserPOJO(
    var id: Long,

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    var boughtAt: LocalDateTime? = null
)

