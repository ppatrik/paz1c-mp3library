package sk.upjs.ics.paz1c.mp3library.dao;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.springframework.jdbc.core.RowMapper;
import sk.upjs.ics.paz1c.mp3library.AlbumDao;
import sk.upjs.ics.paz1c.mp3library.ArtistDao;
import sk.upjs.ics.paz1c.mp3library.BeanFactory;
import sk.upjs.ics.paz1c.mp3library.GenreDao;
import sk.upjs.ics.paz1c.mp3library.Song;

public class SongRowMapper implements RowMapper<Song> {
    
    private final ArtistDao artistDao = BeanFactory.INSTANCE.artistDao();
    private final AlbumDao albumDao = BeanFactory.INSTANCE.albumDao();
    private final GenreDao genreDao = BeanFactory.INSTANCE.genreDao();
    

    @Override
    public Song mapRow(ResultSet rs, int rowNum) throws SQLException {
        Song song = new Song();
        song.setId(rs.getLong("song_id"));
        song.setTitle(rs.getString("title"));
        song.setArtist(artistDao.findById(rs.getLong("artist_id")));
        song.setAlbum(albumDao.findById(rs.getLong("album_id")));
        song.setYear(rs.getInt("year"));
        song.setTrack(rs.getInt("track"));
        song.setDisc(rs.getInt("disc"));
        song.setGenre(genreDao.findById(rs.getLong("genre_id")));
        song.setRating(rs.getInt("rating"));
        
        song.setFile_path(new File(rs.getString("file_path")));
        
        try {
            InputStream binaryStream = rs.getBinaryStream("cover");
            BufferedImage bufferedImage = ImageIO.read(binaryStream);
            song.setCover(new ImageIcon(bufferedImage));
        } catch (IOException e) {
            throw new SQLException("Unable to convert image cover data", e);
        }
        
        song.setQuality(rs.getInt("quality"));
        song.setFormat(rs.getString("format"));
        
        return song;
    }

}
