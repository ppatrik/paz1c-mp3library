package sk.upjs.ics.paz1c.mp3library;

import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SqliteSongDaoTest {

    private final SongDao songDao = BeanFactory.INSTANCE.songDao();

    /**
     * Sets testing environment
     */
    @BeforeClass
    public static void setUp() {
        System.setProperty("junit", "true");
    }

    private void setSongInsertData(Song song) {
        song.setTitle("Jozko");
        Artist artist = new Artist();
        artist.setName("Jozko artist");
        song.setArtist(artist);
        Album album = new Album();
        album.setName("Jozko album");
        song.setAlbum(album);
        song.setYear(2014);
        song.setTrack(10);
        song.setDisc(1);
        Genre genre = new Genre();
        genre.setName("Jozko genre");
        song.setGenre(genre);
        song.setRating(3);
    }

    private void setSongUpdateData(Song song) {
        song.setTitle("Peter");
        Artist artist = new Artist();
        artist.setName("Peter artist");
        song.setArtist(artist);
        Album album = new Album();
        album.setName("Peter album");
        song.setAlbum(album);
        song.setYear(2014);
        song.setTrack(10);
        song.setDisc(1);
        Genre genre = new Genre();
        genre.setName("Peter genre");
        song.setGenre(genre);
        song.setRating(3);
    }

    @Test
    public void insertTest() {
        // vlozit piesen
        Song insertSong = new Song();
        setSongInsertData(insertSong);
        songDao.saveOrUpdate(insertSong);
        assertNotNull(insertSong.getId());

        // kontrola vlozenej piesne
        Song checkSong = songDao.findById(insertSong.getId());
        assertNotNull(checkSong);
        assertTrue(insertSong.equals(checkSong));
    }

    @Test
    public void updateTest() {
        // vlozit album
        Song insertSong = new Song();
        setSongInsertData(insertSong);
        songDao.saveOrUpdate(insertSong);
        assertNotNull(insertSong.getId());

        // nacitat vlozenu piesen
        Song updateSong = songDao.findById(insertSong.getId());
        assertNotNull(updateSong);

        // upravit piesen
        setSongInsertData(updateSong);
        songDao.saveOrUpdate(updateSong);

        // kontrola upravenej piesne
        Song checkSong = songDao.findById(insertSong.getId());
        assertNotNull(checkSong);
        assertTrue(updateSong.equals(checkSong));
    }

    @Test
    public void deleteTest() {
        // vlozit piesen
        Song insertSong = new Song();
        setSongInsertData(insertSong);
        songDao.saveOrUpdate(insertSong);
        assertNotNull(insertSong.getId());

        // nacitat vlozenu piesen
        Song deleteSong = songDao.findById(insertSong.getId());
        assertNotNull(deleteSong);
        songDao.delete(deleteSong);

        // pokus o najdenie zmazanej piesne
        Song checkSong = songDao.findById(insertSong.getId());
        assertNull(checkSong);
    }

    @Test
    public void findByNonExistsIdTest() {
        Song nonExists = songDao.findById(-1l);
        assertNull(nonExists);
    }

    @Test
    public void findAllTest() {
        List<Song> songs = songDao.findAll();
        assertNotNull(songs);
    }

}
