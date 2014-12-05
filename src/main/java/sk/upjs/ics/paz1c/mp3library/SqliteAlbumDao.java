package sk.upjs.ics.paz1c.mp3library;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import sk.upjs.ics.paz1c.mp3library.dao.AlbumRowMapper;

class SqliteAlbumDao implements AlbumDao {

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final RowMapper<Album> albumRowMapper = new AlbumRowMapper();

    public SqliteAlbumDao() {
        // empty constructor
    }

    public SqliteAlbumDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.jdbcTemplate);
    }

    @Override
    public void saveOrUpdate(Album album) {
        /*if (book.getId() == null) {
         insert(book);
         } else {
         update(book);
         }*/
    }

    private void insert(Album album) {
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

    private void update(Album album) {
        /*Map<String, Object> updateMap = new HashMap<String, Object>();
         updateMap.put("id", book.getId());
         updateMap.put("title", book.getTitle());
         updateMap.put("year", book.getYear());

         namedParameterJdbcTemplate.update(SqlQueries.Song.UPDATE, updateMap);*/
    }

    @Override
    public void delete(Album album) {
        // TODO: overenie ci naozaj neexistuju piesne daneho albumu
        //jdbcTemplate.update(SqlQueries.Song.DELETE, song.getId());
    }

    @Override
    public Album findById(Long id) {
        return jdbcTemplate.queryForObject(SqlQueries.Album.FIND_ONE_BY_ID, albumRowMapper, id);
    }

    @Override
    public List<Album> findAll() {
        return jdbcTemplate.query(SqlQueries.Album.FIND_ALL, albumRowMapper);
    }

}
