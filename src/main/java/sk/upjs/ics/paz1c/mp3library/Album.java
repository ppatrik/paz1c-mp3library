package sk.upjs.ics.paz1c.mp3library;

public class Album {

    private static final long NULL_ID = Long.MIN_VALUE;

    private Long id;
    private String name;
    private Integer tracs;
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

    public Integer getTracs() {
        return tracs;
    }

    public void setTracs(Integer tracs) {
        this.tracs = tracs;
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
}
