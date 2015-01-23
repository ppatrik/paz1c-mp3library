package sk.upjs.ics.paz1c.mp3library;

import java.util.List;

public interface ArtistDao {

    public void saveOrUpdate(Artist artist);

    public void delete(Artist artist);

    public Artist findById(Long id);
    
    public Artist findByName(String name);

    public List<Artist> findAll();

}
