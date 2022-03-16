package mn.data.domain

import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.Optional
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class Pack(
    @Id @GeneratedValue var id: Long,

    @Column(length = 300)
    var name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    var artist: Artist? = null
)

@Repository
interface PackRepository: CrudRepository<Pack, Long> {
    fun findByName(name: String): Optional<Pack>
    fun save(pack: Pack): Pack
}
