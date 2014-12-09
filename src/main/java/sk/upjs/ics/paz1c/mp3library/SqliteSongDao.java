package sk.upjs.ics.paz1c.mp3library;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import sk.upjs.ics.paz1c.mp3library.dao.SongRowMapper;

class SqliteSongDao implements SongDao {

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final RowMapper<Song> songRowMapper = new SongRowMapper();

    public SqliteSongDao() {
        // empty constructor
    }

    public SqliteSongDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.jdbcTemplate);
    }

    @Override
    public void saveOrUpdate(Song book) {
        if (book.getId() == null) {
            insert(book);
        } else {
            update(book);
        }
    }

    private void insert(Song book) {
        Map<String, Object> insertMap = new HashMap<String, Object>();
        insertMap.put("id", book.getId());
        insertMap.put("title", book.getTitle());
        insertMap.put("path", "file://null");
        insertMap.put("year", book.getYear());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(SqlQueries.Song.INSERT, new MapSqlParameterSource(insertMap), keyHolder);
        Long id = keyHolder.getKey().longValue();
        book.setId(id);

    }

    private void update(Song book) {
        Map<String, Object> updateMap = new HashMap<String, Object>();
        updateMap.put("id", book.getId());
        updateMap.put("title", book.getTitle());
        updateMap.put("year", book.getYear());

        namedParameterJdbcTemplate.update(SqlQueries.Song.UPDATE, updateMap);
    }

    @Override
    public void delete(Song song) {
        jdbcTemplate.update(SqlQueries.Song.DELETE, song.getId());
    }

    @Override
    public Song findById(Long id) {
        return jdbcTemplate.queryForObject(SqlQueries.Song.FIND_ONE_BY_ID, songRowMapper, id);
    }

    @Override
    public List<Song> findAll() {
        return jdbcTemplate.query(SqlQueries.Song.FIND_ALL, songRowMapper);
    }

    @Override
    public List<Song> findAllByTitle(String title) {
        return jdbcTemplate.query(SqlQueries.Song.FIND_ALL_BY_TITLE, songRowMapper, title);
    }

    @Override
    public List<Song> findAllByArtist(Artist artist) {
        return jdbcTemplate.query(SqlQueries.Song.FIND_ALL_BY_ARTIST, songRowMapper, artist.getId());
    }

    @Override
    public List<Song> findAllByAlbum(Album album) {
        return jdbcTemplate.query(SqlQueries.Song.FIND_ALL_BY_ALBUM, songRowMapper, album.getId());
    }

    @Override
    public List<Song> findAllByGenre(Genre genre) {
        return jdbcTemplate.query(SqlQueries.Song.FIND_ALL_BY_GENRE, songRowMapper, genre.getId());
    }

    @Override
    public Song findByFilePath(File file_path) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
