package sk.upjs.ics.paz1c.mp3library;

import java.sql.SQLException;
import javax.sql.DataSource;
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
        dataSource.setUrl("jdbc:sqlite:mp3library.db");

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
}
