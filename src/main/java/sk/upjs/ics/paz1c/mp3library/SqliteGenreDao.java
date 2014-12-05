package sk.upjs.ics.paz1c.mp3library;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import sk.upjs.ics.paz1c.mp3library.dao.GenreRowMapper;

class SqliteGenreDao implements GenreDao {

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final RowMapper<Genre> genreRowMapper = new GenreRowMapper();

    public SqliteGenreDao() {
        // empty constructor
    }

    public SqliteGenreDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.jdbcTemplate);
    }

    @Override
    public void saveOrUpdate(Genre genre) {
        /*if (book.getId() == null) {
         insert(book);
         } else {
         update(book);
         }*/
    }

    private void insert(Genre genre) {
        /*Map<String, Object> insertMap = new HashMap<String, Object>();
         insertMap.put("id", book.getId());
         insertMap.put("title", book.getTitle());
         insertMap.put("path", "file://null");
         insertMap.put("year", book.getYear());

         KeyHolder keyHolder = new GeneratedKeyHolder();
         namedParameterJdbcTemplate.update(SqlQueries.Song.INSERT, new MapSqlParameterSource(insertMap), keyHolder);
         Long id = keyHolder.getKey().longValue();
         book.setId(id);*/
    }

    private void update(Genre genre) {
        /*Map<String, Object> updateMap = new HashMap<String, Object>();
         updateMap.put("id", book.getId());
         updateMap.put("title", book.getTitle());
         updateMap.put("year", book.getYear());

         namedParameterJdbcTemplate.update(SqlQueries.Song.UPDATE, updateMap);*/
    }

    @Override
    public void delete(Genre genre) {
        // TODO: overenie ci naozaj neexistuju piesne daneho albumu
        //jdbcTemplate.update(SqlQueries.Song.DELETE, song.getId());
    }

    @Override
    public Genre findById(Long id) {
        return jdbcTemplate.queryForObject(SqlQueries.Genre.FIND_ONE_BY_ID, genreRowMapper, id);
    }

    @Override
    public List<Genre> findAll() {
        return jdbcTemplate.query(SqlQueries.Genre.FIND_ALL, genreRowMapper);
    }

}
