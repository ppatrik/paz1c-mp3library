package sk.upjs.ics.paz1c.mp3library.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import sk.upjs.ics.paz1c.mp3library.Album;
import sk.upjs.ics.paz1c.mp3library.ArtistDao;
import sk.upjs.ics.paz1c.mp3library.BeanFactory;

public class AlbumRowMapper implements RowMapper<Album> {
    
    @Override
    public Album mapRow(ResultSet rs, int rowNum) throws SQLException {
        Album album = new Album();
        album.setId(rs.getLong("album_id"));
        album.setName(rs.getString("name"));

        return album;
    }

}
