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
import sk.upjs.ics.paz1c.mp3library.dao.AlbumRowMapper;

class SqliteAlbumDao implements AlbumDao {

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final RowMapper<Album> albumRowMapper = new AlbumRowMapper();

    public SqliteAlbumDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.jdbcTemplate);
    }

    @Override
    public void saveOrUpdate(Album album) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("album_id", album.getId());
        dataMap.put("name", album.getName());
        dataMap.put("tracs", album.getTracs());
        dataMap.put("discs", album.getDiscs());

        if (album.getId() == null) {
            insert(album, dataMap);
        } else {
            update(album, dataMap);
        }
    }

    private void insert(Album album, Map<String, Object> insertMap) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(SqlQueries.Album.INSERT, new MapSqlParameterSource(insertMap), keyHolder);
        Long id = keyHolder.getKey().longValue();
        album.setId(id);
    }

    private void update(Album album, Map<String, Object> updateMap) {
        namedParameterJdbcTemplate.update(SqlQueries.Album.UPDATE, updateMap);
    }

    @Override
    public void delete(Album album) {
        SongDao songDao = BeanFactory.INSTANCE.songDao();
        List<Song> songs = songDao.findAllByAlbum(album);
        if (songs.isEmpty()) {
            jdbcTemplate.update(SqlQueries.Album.DELETE, album.getId());
        }
    }

    @Override
    public Album findById(Long id) {
        try {
            return jdbcTemplate.queryForObject(SqlQueries.Album.FIND_ONE_BY_ID, albumRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Album findByName(String name) {
        try {
            return jdbcTemplate.queryForObject(SqlQueries.Album.FIND_ONE_BY_NAME, albumRowMapper, name);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Album> findAll() {
        return jdbcTemplate.query(SqlQueries.Album.FIND_ALL, albumRowMapper);
    }

}
