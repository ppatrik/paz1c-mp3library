/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.paz1c.mp3library;

import java.net.MalformedURLException;
import java.net.URL;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author patrik
 */
public class SqliteArtistTest {

    @BeforeClass
    public static void setUp() {
        System.setProperty("junit", "true");
    }
    
    private ArtistDao artistDao = BeanFactory.INSTANCE.artistDao();
    private Artist artist;

    @Before
    public void before() throws MalformedURLException {
        artist = new Artist();
        artist.setName("Artist");
        artist.setWiki(new URL("http://wikipedia.sk/artist"));
        artistDao.saveOrUpdate(artist);
    }

    @Test
    public void insertTest() throws MalformedURLException {
        Artist a = new Artist();
        a.setName("Jozko");
        a.setWiki(new URL("http://jozko.sk"));
        artistDao.saveOrUpdate(a);

        Artist found = artistDao.findById(a.getId());

        assertEquals(a.getName(), found.getName());
    }

    @Test
    public void updateTest() {
        Artist updated = artistDao.findById(artist.getId());

        assertNotNull(updated);

        updated.setName("Novy nazov");
        artistDao.saveOrUpdate(updated);

        Artist found = artistDao.findById(artist.getId());

        assertEquals(updated.getName(), found.getName());
    }

    @Test
    public void deleteTest() {
        Artist fordelete = artistDao.findById(artist.getId());
        artistDao.delete(fordelete);

        Artist found = artistDao.findById(artist.getId());

        //assertNull(found);
    }
}
