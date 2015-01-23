package sk.upjs.ics.paz1c.mp3library;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SqliteArtistDaoTest {
    
    private final ArtistDao artistDao = BeanFactory.INSTANCE.artistDao();

    /**
     * Sets testing environment
     */
    @BeforeClass
    public static void setUp() {
        System.setProperty("junit", "true");
    }
    
    @Test
    public void insertTest() throws MalformedURLException {
        // vlozit interpreta
        Artist insertArtist = new Artist();
        insertArtist.setName("Jozko");
        insertArtist.setWiki(new URL("http://jozko.sk"));
        artistDao.saveOrUpdate(insertArtist);
        assertNotNull(insertArtist.getId());

        // kontrola vlozeneho interpreta
        Artist checkArtist = artistDao.findById(insertArtist.getId());
        assertNotNull(checkArtist);
        assertTrue(insertArtist.equals(checkArtist));
    }
    
    @Test
    public void updateTest() throws MalformedURLException {
        // vlozit interpreta
        Artist insertArtist = new Artist();
        insertArtist.setName("Jozko");
        insertArtist.setWiki(new URL("http://jozko.sk"));
        artistDao.saveOrUpdate(insertArtist);
        assertNotNull(insertArtist.getId());

        // nacitat vlozeneho interpreta
        Artist updateArtist = artistDao.findById(insertArtist.getId());
        assertNotNull(updateArtist);

        // upravit interpreta
        updateArtist.setName("Peter");
        updateArtist.setWiki(new URL("http://peter.sk"));
        artistDao.saveOrUpdate(updateArtist);

        // kontrola upraveneho interpreta
        Artist checkArtist = artistDao.findById(insertArtist.getId());
        assertNotNull(checkArtist);
        assertTrue(updateArtist.equals(checkArtist));
    }
    
    @Test
    public void deleteTest() throws MalformedURLException {
        // vlozit interpreta
        Artist insertArtist = new Artist();
        insertArtist.setName("Jozko");
        insertArtist.setWiki(new URL("http://jozko.sk"));
        artistDao.saveOrUpdate(insertArtist);
        assertNotNull(insertArtist.getId());

        // nacitat vlozeneho interpreta
        Artist deleteArtist = artistDao.findById(insertArtist.getId());
        assertNotNull(deleteArtist);
        artistDao.delete(deleteArtist);

        // pokus o najdenie zmazaneho interpreta
        Artist checkArtist = artistDao.findById(insertArtist.getId());
        assertNull(checkArtist);
    }
    
    @Test
    public void findByNonExistsIdTest() {
        Artist nonExists = artistDao.findById(-1l);
        assertNull(nonExists);
    }
    
    @Test
    public void findAllTest() {
        List<Artist> artists = artistDao.findAll();
        assertNotNull(artists);
    }
    
    @Test
    public void findByNameTest() throws MalformedURLException {
        Artist checkArtist = artistDao.findByName("Jozko");
        
        if (checkArtist == null) {
            // vlozit interpreta
            Artist insertArtist = new Artist();
            insertArtist.setName("Jozko");
            insertArtist.setWiki(new URL("http://jozko.sk"));
            artistDao.saveOrUpdate(insertArtist);
            assertNotNull(insertArtist.getId());
            
            checkArtist = artistDao.findByName("Jozko");
        }
        
        assertNotNull(checkArtist);
    }
    
}
