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
import sk.upjs.ics.paz1c.mp3library.dao.GenreRowMapper;

class SqliteGenreDao implements GenreDao {

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final RowMapper<Genre> genreRowMapper = new GenreRowMapper();

    public SqliteGenreDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.jdbcTemplate);
    }

    @Override
    public void saveOrUpdate(Genre genre) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("genre_id", genre.getId());
        dataMap.put("name", genre.getName());

        if (genre.getId() == null) {
            insert(genre, dataMap);
        } else {
            update(genre, dataMap);
        }
    }

    private void insert(Genre genre, Map<String, Object> insertMap) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(SqlQueries.Genre.INSERT, new MapSqlParameterSource(insertMap), keyHolder);
        Long id = keyHolder.getKey().longValue();
        genre.setId(id);
    }

    private void update(Genre genre, Map<String, Object> updateMap) {
        namedParameterJdbcTemplate.update(SqlQueries.Genre.UPDATE, updateMap);
    }

    @Override
    public void delete(Genre genre) {
        SongDao songDao = BeanFactory.INSTANCE.songDao();
        List<Song> songs = songDao.findAllByGenre(genre);
        if (songs.isEmpty()) {
            jdbcTemplate.update(SqlQueries.Genre.DELETE, genre.getId());
        }
    }

    @Override
    public Genre findById(Long id) {
        try {
            return jdbcTemplate.queryForObject(SqlQueries.Genre.FIND_ONE_BY_ID, genreRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Genre findByName(String name) {
        try {
            return jdbcTemplate.queryForObject(SqlQueries.Genre.FIND_ONE_BY_NAME, genreRowMapper, name);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Genre> findAll() {
        return jdbcTemplate.query(SqlQueries.Genre.FIND_ALL, genreRowMapper);
    }

}
