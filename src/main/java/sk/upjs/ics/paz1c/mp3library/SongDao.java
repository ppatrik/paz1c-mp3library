package sk.upjs.ics.paz1c.mp3library;

import java.util.List;

public interface SongDao {

    public void saveOrUpdate(Song song);

    public void delete(Song song);

    public Song findById(Long id);

    public List<Song> findAll();

    public List<Song> findByTitle(String title);

    public List<Song> findByArtist(Artist artist);

    public List<Song> findByAlbum(Album album);

    public List<Song> findByAlbumArtist(Artist albumArtist);
    
    public List<Song> findByGenre(Genre genre);
}
