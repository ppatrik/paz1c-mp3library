package sk.upjs.ics.paz1c.mp3library;

public class Album {

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

}
