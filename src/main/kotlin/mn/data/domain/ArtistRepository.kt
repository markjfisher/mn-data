package mn.data.domain

import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.Optional
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class Artist(
    @Id @GeneratedValue var id: Long,

    @Column(length = 100)
    var name: String,

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, mappedBy = "artist")
    var packs: MutableSet<Pack> = mutableSetOf()
)

@Repository
interface ArtistRepository : CrudRepository<Artist, Long> {
    fun save(artist: Artist): Artist
    fun findByName(name: String): Optional<Artist>
}
