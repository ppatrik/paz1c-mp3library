package sk.upjs.ics.paz1c.mp3library;

import java.io.File;
import java.util.List;

public interface SongDao {

    public void saveOrUpdate(Song song);

    public void delete(Song song);

    public Song findById(Long id);

    public Song findByFilePath(File file_path);

    public List<Song> findAll();

    public List<Song> findAllByTitle(String title);

    public List<Song> findAllByArtist(Artist artist);

    public List<Song> findAllByAlbum(Album album);

    public List<Song> findAllByGenre(Genre genre);
}
