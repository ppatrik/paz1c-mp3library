package sk.upjs.ics.paz1c.mp3library;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.dao.EmptyResultDataAccessException;
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
    public void saveOrUpdate(Song song) {
        if(song==null)
            return;
        
        song.performArtistExistsCheck();
        song.performAlbumExistsCheck();
        song.performGenreExistsCheck();
        
        BeanFactory.INSTANCE.artistDao().saveOrUpdate(song.getArtist());
        BeanFactory.INSTANCE.albumDao().saveOrUpdate(song.getAlbum());
        BeanFactory.INSTANCE.genreDao().saveOrUpdate(song.getGenre());
        
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("song_id", song.getId());
        dataMap.put("title", song.getTitle());
        dataMap.put("artist_id", song.getArtist().getId());
        dataMap.put("album_id", song.getAlbum().getId());
        dataMap.put("year", song.getYear());
        dataMap.put("track", song.getTrack());
        dataMap.put("disc", song.getDisc());
        dataMap.put("genre_id", song.getGenre().getId());
        dataMap.put("rating", song.getRating());
        dataMap.put("file_path", song.getFile_path().getAbsolutePath());
        dataMap.put("cover", song.getCover());
        dataMap.put("quality", song.getQuality());
        dataMap.put("format", song.getFormat());

        if (song.getId() == null) {
            insert(song, dataMap);
        } else {
            update(song, dataMap);
        }
    }

    private void insert(Song song, Map<String, Object> insertMap) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(SqlQueries.Song.INSERT, new MapSqlParameterSource(insertMap), keyHolder);
        Long id = keyHolder.getKey().longValue();
        song.setId(id);

    }

    private void update(Song book, Map<String, Object> updateMap) {
        namedParameterJdbcTemplate.update(SqlQueries.Song.UPDATE, updateMap);
    }

    @Override
    public void delete(Song song) {
        jdbcTemplate.update(SqlQueries.Song.DELETE, song.getId());
    }

    @Override
    public Song findById(Long id) {
        try {
            return jdbcTemplate.queryForObject(SqlQueries.Song.FIND_ONE_BY_ID, songRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
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
        try {
            return jdbcTemplate.queryForObject(SqlQueries.Song.FIND_ONE_BY_FILE_PATH, songRowMapper, file_path);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
