/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.paz1c.mp3library.gui;

/**
 *
 * @author patrik
 */
public enum GuiFactory {

    INSTANCE;

    private MainDashboardForm mainDashboardForm;
    private SongsPanel songsPanel;
    private AlbumsPanel albumsPanel;
    private ArtistsPanel artistsPanel;
    private GenresPanel genresPanel;
    private SongImporterDialog songImporterDialog;

    public MainDashboardForm mainDashboardForm() {
        if (mainDashboardForm == null) {
            mainDashboardForm = new MainDashboardForm();
        }
        return mainDashboardForm;
    }

    public SongsPanel songsPanel() {
        if (songsPanel == null) {
            songsPanel = new SongsPanel();
        }
        return songsPanel;
    }

    public AlbumsPanel albumsPanel() {
        if (albumsPanel == null) {
            albumsPanel = new AlbumsPanel();
        }
        return albumsPanel;
    }

    public ArtistsPanel artistsPanel() {
        if (artistsPanel == null) {
            artistsPanel = new ArtistsPanel();
        }
        return artistsPanel;
    }

    public GenresPanel genresPanel() {
        if (genresPanel == null) {
            genresPanel = new GenresPanel();
        }
        return genresPanel;
    }
    public SongImporterDialog songImporterDialog() {
        if(songImporterDialog == null) {
            songImporterDialog = new SongImporterDialog();
        }
        return songImporterDialog;
    }
}
