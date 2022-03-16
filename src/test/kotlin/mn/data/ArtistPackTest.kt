package mn.data

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import mn.data.domain.Artist
import mn.data.domain.ArtistRepository
import org.junit.jupiter.api.Test
import mn.data.domain.Pack
import mn.data.domain.PackRepository
import org.assertj.core.api.Assertions.assertThat

@MicronautTest
class ArtistPackTest {
    @Inject
    lateinit var artistRepository: ArtistRepository

    @Inject
    lateinit var packRepository: PackRepository

    @Test
    fun `can create artists with packs and the pack points back to the artist in parent child relationship`() {
        val pack = Pack(id = 0, name = "test pack 1")
        val artist = Artist(id = 0, name = "artist1", packs = mutableSetOf(pack))

        val savedArtist = artistRepository.save(artist)
        val foundArtist = artistRepository.findByName("artist1").get()
        assertThat(savedArtist).isEqualTo(artist)
        assertThat(savedArtist).isEqualTo(foundArtist)

        assertThat(artist.packs.first()).isEqualTo(pack)
        // And the data retrieved from the repository points to this pack
        assertThat(foundArtist.packs.first()).isEqualTo(pack)

        val foundPack = packRepository.findByName("test pack 1").get()

        // TEST FAILS HERE, foundPack.artist is null.
        // I was expecting this to work, but the parent relationship is not automatically created, so this test fails:
        assertThat(foundPack.artist).isEqualTo(artist)
    }

    @Test
    fun `can create artists with packs and back reference them`() {
        val pack = Pack(id = 0, name = "test pack 1")
        val artist = Artist(id = 0, name = "artist1", packs = mutableSetOf(pack))

        val savedArtist = artistRepository.save(artist)
        val foundArtist = artistRepository.findByName("artist1").get()
        assertThat(savedArtist).isEqualTo(artist)
        assertThat(savedArtist).isEqualTo(foundArtist)

        assertThat(artist.packs.first()).isEqualTo(pack)

        // TRY AND FORCE THE RELATIONSHIP THAT FIRST TEST PROVES DOES NOT HAPPEN
        // when I create the back reference and save it
        pack.artist = artist

        // ***** StackOverflow ERROR HERE ***** (trying to generate a hashCode)
        packRepository.update(pack)

        val foundPack = packRepository.findByName("test pack 1").get()
        assertThat(foundPack).isEqualTo(pack)
        assertThat(foundPack.artist).isEqualTo(artist)
    }

}