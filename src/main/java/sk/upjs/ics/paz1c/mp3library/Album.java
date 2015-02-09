package sk.upjs.ics.paz1c.mp3library;

import java.util.Objects;

public class Album {

    private static final long NULL_ID = Long.MIN_VALUE;

    private Long id;
    private String name;
    private Integer tracks;
    private Integer discs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTracks() {
        return tracks;
    }

    public void setTracks(Integer tracks) {
        this.tracks = tracks;
    }

    public Integer getDiscs() {
        return discs;
    }

    public void setDiscs(Integer discs) {
        this.discs = discs;
    }


    
    public static Album getNull() {
        Album album = new Album();
        album.setId(NULL_ID);
        return album;
    }

    public static boolean isNull(Album album) {
        return NULL_ID == album.getId();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.tracks);
        hash = 59 * hash + Objects.hashCode(this.discs);
        return hash;
    }

    @Override
    public String toString() {
        return "Album{" + "id=" + id + ", name=" + name + "}";
    }
}
