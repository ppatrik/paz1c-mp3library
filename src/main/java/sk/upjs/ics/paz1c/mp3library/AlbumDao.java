package sk.upjs.ics.paz1c.mp3library;

import java.util.List;

public interface AlbumDao {

    public void saveOrUpdate(Album album);

    public void delete(Album album);

    public Album findById(Long id);
    
    public Album findByName(String name);

    public List<Album> findAll();
}
