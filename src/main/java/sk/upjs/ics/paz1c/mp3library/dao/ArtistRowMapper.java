package sk.upjs.ics.paz1c.mp3library.dao;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import sk.upjs.ics.paz1c.mp3library.Artist;

public class ArtistRowMapper implements RowMapper<Artist> {

    @Override
    public Artist mapRow(ResultSet rs, int rowNum) throws SQLException {
        Artist artist = new Artist();
        artist.setId(rs.getLong("artist_id"));
        artist.setName(rs.getString("artist_name"));
        try {
            artist.setWiki(new URL(rs.getString("artist_wiki")));
        } catch (MalformedURLException ex) {
            artist.setWiki(null);
        }

        return artist;
    }

}
