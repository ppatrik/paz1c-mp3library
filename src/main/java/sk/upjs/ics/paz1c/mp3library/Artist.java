package sk.upjs.ics.paz1c.mp3library;

import java.net.URL;

public class Artist {

    private Long id;
    private String name;
    private URL wiki;

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

    public URL getWiki() {
        return wiki;
    }

    public void setWiki(URL wiki) {
        this.wiki = wiki;
    }

}
