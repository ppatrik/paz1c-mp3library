package sk.upjs.ics.paz1c.mp3library;

import java.util.List;

public interface AlbumDao {

    public void saveOrUpdate(Album album);

    public void delete(Album album);

    public Album findById(Long id);

    public List<Album> findAll();
}
