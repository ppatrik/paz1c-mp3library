package sk.upjs.ics.paz1c.mp3library;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.dao.support.DaoSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.sqlite.SQLiteDataSource;

public enum BeanFactory {

    INSTANCE;

    private SongDao songDao;
    private AlbumDao albumDao;
    private ArtistDao artistDao;
    private GenreDao genreDao;
    private JdbcTemplate jdbcTemplate;

    public SongDao songDao() {
        if (this.songDao == null) {
            this.songDao = new SqliteSongDao(jdbcTemplate());
        }
        return this.songDao;
    }

    public AlbumDao albumDao() {
        if (this.albumDao == null) {
            this.albumDao = new SqliteAlbumDao(jdbcTemplate());
        }
        return this.albumDao;
    }

    public ArtistDao artistDao() {
        if (this.artistDao == null) {
            this.artistDao = new SqliteArtistDao(jdbcTemplate());
        }
        return this.artistDao;
    }

    public GenreDao genreDao() {
        if (this.genreDao == null) {
            this.genreDao = new SqliteGenreDao(jdbcTemplate());
        }
        return this.genreDao;
    }

    private DataSource dataSource() {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        
        if ("true".equals(System.getProperty("junit"))) {
            dataSource.setUrl("jdbc:sqlite:mp3library-test.db");
        } else {
            dataSource.setUrl("jdbc:sqlite:mp3library.db");
        }

        return dataSource;
    }

    public JdbcTemplate jdbcTemplate() {
        if (this.jdbcTemplate == null) {
            this.jdbcTemplate = new JdbcTemplate(dataSource());
            initDatabase();
        }
        return this.jdbcTemplate;
    }

    private void initDatabase() {
        try {
            new SqliteMigration(jdbcTemplate).migrate();
        } catch (SQLException ex) {
            System.out.println("Zavazny databazovy problem, pri inicializacii nebola dostupna");
        }

    }

    private Properties getProperties() throws IOException {
        InputStream config = BeanFactory.class.getResourceAsStream("config.properties");
        Properties properties = new Properties();
        properties.load(config);

        return properties;
    }
}
