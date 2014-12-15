package sk.upjs.ics.paz1c.mp3library;

import org.junit.Test;
import static org.junit.Assert.*;

public class SqlliteTest {

    @Test
    public void connectionTest() {
        SongDao songDao = BeanFactory.INSTANCE.songDao();
        assertNotNull(songDao);
    }
}
