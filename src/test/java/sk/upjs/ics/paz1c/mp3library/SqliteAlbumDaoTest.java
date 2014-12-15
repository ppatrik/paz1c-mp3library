package sk.upjs.ics.paz1c.mp3library;

import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SqliteAlbumDaoTest {

    private final AlbumDao albumDao = BeanFactory.INSTANCE.albumDao();

    /**
     * Sets testing environment
     */
    @BeforeClass
    public static void setUp() {
        System.setProperty("junit", "true");
    }

    @Test
    public void insertTest() {
        // vlozit album
        Album insertAlbum = new Album();
        insertAlbum.setName("Jozko");
        insertAlbum.setTracks(10);
        insertAlbum.setDiscs(1);
        albumDao.saveOrUpdate(insertAlbum);
        assertNotNull(insertAlbum.getId());

        // kontrola vlozeneho albumu
        Album checkAlbum = albumDao.findById(insertAlbum.getId());
        assertNotNull(checkAlbum);
        assertTrue(insertAlbum.equals(checkAlbum));
    }

    @Test
    public void updateTest() {
        // vlozit album
        Album insertAlbum = new Album();
        insertAlbum.setName("Jozko");
        insertAlbum.setTracks(10);
        insertAlbum.setDiscs(1);
        albumDao.saveOrUpdate(insertAlbum);
        assertNotNull(insertAlbum.getId());

        // nacitat vlozeny album
        Album updateAlbum = albumDao.findById(insertAlbum.getId());
        assertNotNull(updateAlbum);

        // upravit interpreta
        updateAlbum.setName("Peter");
        updateAlbum.setTracks(11);
        updateAlbum.setDiscs(2);
        albumDao.saveOrUpdate(updateAlbum);

        // kontrola upraveneho albumu
        Album checkAlbum = albumDao.findById(insertAlbum.getId());
        assertNotNull(checkAlbum);
        assertTrue(updateAlbum.equals(checkAlbum));
    }

    @Test
    public void deleteTest() {
        // vlozit album
        Album insertAlbum = new Album();
        insertAlbum.setName("Jozko");
        insertAlbum.setTracks(10);
        insertAlbum.setDiscs(1);
        albumDao.saveOrUpdate(insertAlbum);
        assertNotNull(insertAlbum.getId());

        // nacitat vlozeny album
        Album deleteAlbum = albumDao.findById(insertAlbum.getId());
        assertNotNull(deleteAlbum);
        albumDao.delete(deleteAlbum);

        // pokus o najdenie zmazaneho albumu
        Album checkAlbum = albumDao.findById(insertAlbum.getId());
        assertNull(checkAlbum);
    }

    @Test
    public void findByNonExistsIdTest() {
        Album nonExists = albumDao.findById(-1l);
        assertNull(nonExists);
    }

    @Test
    public void findAllTest() {
        List<Album> albums = albumDao.findAll();
        assertNotNull(albums);
    }

    @Test
    public void findByNameTest() {
        Album checkAlbum = albumDao.findByName("Jozko");

        if (checkAlbum == null) {
            // vlozit interpreta
            Album insertAlbum = new Album();
            insertAlbum.setName("Jozko");
            insertAlbum.setTracks(10);
            insertAlbum.setDiscs(1);
            albumDao.saveOrUpdate(insertAlbum);
            assertNotNull(insertAlbum.getId());

            checkAlbum = albumDao.findByName("Jozko");
        }

        assertNotNull(checkAlbum);
    }

}
