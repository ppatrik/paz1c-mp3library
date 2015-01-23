package sk.upjs.ics.paz1c.mp3library;

import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SqliteGenreDaoTest {

    private final GenreDao genreDao = BeanFactory.INSTANCE.genreDao();

    /**
     * Sets testing environment
     */
    @BeforeClass
    public static void setUp() {
        System.setProperty("junit", "true");
    }

    @Test
    public void insertTest() {
        // vlozit zaner
        Genre insertGenre = new Genre();
        insertGenre.setName("Jozko");
        genreDao.saveOrUpdate(insertGenre);
        assertNotNull(insertGenre.getId());

        // kontrola vlozeneho zanra
        Genre checkGenre = genreDao.findById(insertGenre.getId());
        assertNotNull(checkGenre);
        assertTrue(insertGenre.equals(checkGenre));
    }

    @Test
    public void updateTest() {
        // vlozit interpreta
        Genre insertGenre = new Genre();
        insertGenre.setName("Jozko");
        genreDao.saveOrUpdate(insertGenre);
        assertNotNull(insertGenre.getId());

        // nacitat vlozeny zaner
        Genre updateGenre = genreDao.findById(insertGenre.getId());
        assertNotNull(updateGenre);

        // upravit zaner
        updateGenre.setName("Peter");
        genreDao.saveOrUpdate(updateGenre);

        // kontrola upraveneho zanra
        Genre checkGenre = genreDao.findById(insertGenre.getId());
        assertNotNull(checkGenre);
        assertTrue(updateGenre.equals(checkGenre));
    }

    @Test
    public void deleteTest() {
        // vlozit zaner
        Genre insertGenre = new Genre();
        insertGenre.setName("Jozko");
        genreDao.saveOrUpdate(insertGenre);
        assertNotNull(insertGenre.getId());

        // nacitat vlozeny zaner
        Genre deleteGenre = genreDao.findById(insertGenre.getId());
        assertNotNull(deleteGenre);
        genreDao.delete(deleteGenre);

        // pokus o najdenie zmazaneho zanra
        Genre checkGenre = genreDao.findById(insertGenre.getId());
        assertNull(checkGenre);
    }

    @Test
    public void findByNonExistsIdTest() {
        Genre nonExists = genreDao.findById(-1l);
        assertNull(nonExists);
    }

    @Test
    public void findAllTest() {
        List<Genre> genres = genreDao.findAll();
        assertNotNull(genres);
    }

    @Test
    public void findByNameTest() {
        Genre checkGenre = genreDao.findByName("Jozko");

        if (checkGenre == null) {
            // vlozit zaner
            Genre insertGenre = new Genre();
            insertGenre.setName("Jozko");
            genreDao.saveOrUpdate(insertGenre);
            assertNotNull(insertGenre.getId());

            checkGenre = genreDao.findByName("Jozko");
        }

        assertNotNull(checkGenre);
    }

}
