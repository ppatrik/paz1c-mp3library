package sk.upjs.ics.paz1c.mp3library;

import java.io.File;
import javax.swing.ImageIcon;

public class Song {

    private Long id;
    private String title;
    private Artist artist;
    private Album album;
    private Integer year;
    private Integer track;
    private Integer disc;
    private Genre genre;
    private Integer rating;
    private File file_path;
    private ImageIcon cover;
    private Integer quality;
    private String format;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getTrack() {
        return track;
    }

    public void setTrack(Integer track) {
        this.track = track;
    }

    public Integer getDisc() {
        return disc;
    }

    public void setDisc(Integer disc) {
        this.disc = disc;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public File getFile_path() {
        return file_path;
    }

    public void setFile_path(File file_path) {
        this.file_path = file_path;
    }

    public ImageIcon getCover() {
        return cover;
    }

    public void setCover(ImageIcon cover) {
        this.cover = cover;
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void performArtistExistsCheck() {
        ArtistDao artistDao = BeanFactory.INSTANCE.artistDao();
        Artist exists = artistDao.findByName(artist.getName());
        if (exists != null) {
            artist = exists;
        }
    }

    public void performAlbumExistsCheck() {
        AlbumDao albumDao = BeanFactory.INSTANCE.albumDao();
        Album exists = albumDao.findByName(album.getName());
        if (exists != null) {
            album = exists;
        }
    }

    public void performGenreExistsCheck() {
        GenreDao genreDao = BeanFactory.INSTANCE.genreDao();
        Genre exists = genreDao.findByName(genre.getName());
        if (exists != null) {
            genre = exists;
        }
    }

}
