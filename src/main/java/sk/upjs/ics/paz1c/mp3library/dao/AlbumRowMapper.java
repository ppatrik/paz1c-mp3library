package sk.upjs.ics.paz1c.mp3library.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import sk.upjs.ics.paz1c.mp3library.Album;

public class AlbumRowMapper implements RowMapper<Album> {
    
    @Override
    public Album mapRow(ResultSet rs, int rowNum) throws SQLException {
        Album album = new Album();
        album.setId(rs.getLong("album_id"));
        album.setName(rs.getString("album_name"));
        try {
            album.setTracks(Integer.parseInt(rs.getString("album_tracks")));
        } catch(NumberFormatException e) {
            album.setTracks(null);
        }
        try {
            album.setDiscs(Integer.parseInt(rs.getString("album_discs")));
        } catch(NumberFormatException e) {
            album.setDiscs(null);
        }
        
        
        return album;
    }

}
