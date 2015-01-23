package sk.upjs.ics.paz1c.mp3library.dao;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import sk.upjs.ics.paz1c.mp3library.Song;

public class SongRowMapper implements RowMapper<Song> {

    private ArtistRowMapper artistRowMapper = new ArtistRowMapper();
    private AlbumRowMapper albumRowMapper = new AlbumRowMapper();
    private GenreRowMapper genreRowMapper = new GenreRowMapper();

    @Override
    public Song mapRow(ResultSet rs, int rowNum) throws SQLException {
        Song song = new Song();
        song.setId(rs.getLong("song_id"));
        song.setTitle(rs.getString("song_title"));
        song.setArtist(artistRowMapper.mapRow(rs, rowNum));
        song.setAlbum(albumRowMapper.mapRow(rs, rowNum));
        song.setYear(rs.getInt("song_year"));
        song.setTrack(rs.getInt("song_track"));
        song.setDisc(rs.getInt("song_disc"));
        song.setGenre(genreRowMapper.mapRow(rs, rowNum));
        song.setRating(rs.getInt("song_rating"));

        try {
            song.setFile_path(new File(rs.getString("song_file_path")));
        } catch (NullPointerException e) {
            song.setFile_path(null);
        }
        song.setQuality(rs.getInt("song_quality"));
        song.setFormat(rs.getString("song_format"));

        return song;
    }

}
