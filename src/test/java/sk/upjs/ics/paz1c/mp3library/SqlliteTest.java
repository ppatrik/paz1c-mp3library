package sk.upjs.ics.paz1c.mp3library;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SqlliteTest {

    @Test
    public void connectionTest() {
        SongDao songDao = BeanFactory.INSTANCE.songDao();
        for (Song song : songDao.findAll()) {
            System.out.println(song);
        }
    }
}
