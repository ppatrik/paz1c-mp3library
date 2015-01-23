package sk.upjs.ics.paz1c.mp3library;

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
import sk.upjs.ics.paz1c.mp3library.dao.ArtistRowMapper;

class SqliteArtistDao implements ArtistDao {

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final RowMapper<Artist> artistRowMapper = new ArtistRowMapper();

    public SqliteArtistDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.jdbcTemplate);
    }

    @Override
    public void saveOrUpdate(Artist artist) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("artist_id", artist.getId());
        dataMap.put("name", artist.getName());
        dataMap.put("wiki", artist.getWiki());

        if (artist.getId() == null) {
            insert(artist, dataMap);
        } else {
            update(artist, dataMap);
        }
    }

    private void insert(Artist artist, Map<String, Object> insertMap) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(SqlQueries.Artist.INSERT, new MapSqlParameterSource(insertMap), keyHolder);
        Long id = keyHolder.getKey().longValue();
        artist.setId(id);
    }

    private void update(Artist artist, Map<String, Object> updateMap) {
        namedParameterJdbcTemplate.update(SqlQueries.Artist.UPDATE, updateMap);
    }

    @Override
    public void delete(Artist artist) {
        SongDao songDao = BeanFactory.INSTANCE.songDao();
        List<Song> songs = songDao.findAllByArtist(artist);
        for (Song song : songs) {
            BeanFactory.INSTANCE.songDao().delete(song);
        }
        jdbcTemplate.update(SqlQueries.Artist.DELETE, artist.getId());
    }

    @Override
    public Artist findById(Long id) {
        try {
            return jdbcTemplate.queryForObject(SqlQueries.Artist.FIND_ONE_BY_ID, artistRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Artist> findAll() {
        return jdbcTemplate.query(SqlQueries.Artist.FIND_ALL, artistRowMapper);
    }

    @Override
    public Artist findByName(String name) {
        try {
            return jdbcTemplate.queryForObject(SqlQueries.Artist.FIND_ONE_BY_NAME, artistRowMapper, name);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
