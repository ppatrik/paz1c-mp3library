package sk.upjs.ics.paz1c.mp3library;

import java.util.List;

public interface GenreDao {

    public void saveOrUpdate(Genre genre);

    public void delete(Genre genre);

    public Genre findById(Long id);
    
    public Genre findByName(String name);

    public List<Genre> findAll();

}
