package sk.upjs.ics.paz1c.mp3library.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import sk.upjs.ics.paz1c.mp3library.gui.components.ImageJButton;

public class MainDashboardForm extends JFrame {

    private final SongsPanel panSongs = GuiFactory.INSTANCE.songsPanel();
    private final AlbumsPanel panAlbums = GuiFactory.INSTANCE.albumsPanel();
    private final ArtistsPanel panArtists = GuiFactory.INSTANCE.artistsPanel();
    //private final GenresPanel panGenres = GuiFactory.INSTANCE.genresPanel();

    private final SongImporterDialog songImporterDialog = GuiFactory.INSTANCE.songImporterDialog();

    private final ImageJButton btnSongs = new ImageJButton("songs.png");
    private final ImageJButton btnAlbums = new ImageJButton("albums.png");
    private final ImageJButton btnArtist = new ImageJButton("artists.png");
    private final ImageJButton btnAddFolder = new ImageJButton("import.png");

    private final JPanel panNavigation;
    private final JPanel panDashboard;

    public MainDashboardForm() {
        setTitle("MP3 Library");

        setLayout(new BorderLayout());

        panNavigation = createNavigationPanel();
        add(panNavigation, BorderLayout.WEST);

        panDashboard = new JPanel(new BorderLayout());
        add(panDashboard, BorderLayout.CENTER);

        //setLocationByPlatform(false);
        setPreferredSize(new Dimension(800, 600));
        setSize(800, 600);

        // Otvorenie prveho panelu
        btnSongsActionPerformed(null);
    }

    public void setSize(int width, int height) {
        super.setSize(width, height);

        //Get the screen size
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        //Calculate the frame location
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;

        //Set the new frame location
        setLocation(x, y);
    }

    public void setSize(Dimension size) {
        setSize(size.width, size.height);
    }

    private JPanel createNavigationPanel() {
        JPanel navigationPanel = new JPanel(new MigLayout("fill, gap rel 0, insets 0"));
        navigationPanel.add(btnSongs, "wrap, grow, h 30%, w 80!");
        navigationPanel.add(btnAlbums, "wrap, grow, h 30%, w 80!");
        navigationPanel.add(btnArtist, "wrap, grow, h 30%, w 80!");
        navigationPanel.add(btnAddFolder, "wrap, grow, h 10%, w 80!");

        
        btnSongs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSongsActionPerformed(e);
            }
        });

        btnAlbums.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAlbumsActionPerformed(e);
            }
        });

        btnArtist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnArtistActionPerformed(e);
            }
        });

        btnAddFolder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAddFolderActionPerformed(e);
            }
        });

        return navigationPanel;
    }

    private void btnSongsActionPerformed(ActionEvent e) {
        panDashboard.removeAll();
        panDashboard.add(panSongs, BorderLayout.CENTER);

        btnSongs.setChecked();
        btnArtist.setUnchecked();
        btnAlbums.setUnchecked();
        btnAddFolder.setUnchecked();

        pack();
        repaint();
    }

    private void btnAlbumsActionPerformed(ActionEvent e) {
        panDashboard.removeAll();
        panDashboard.add(panAlbums, BorderLayout.CENTER);

        btnSongs.setUnchecked();
        btnArtist.setUnchecked();
        btnAlbums.setChecked();
        btnAddFolder.setUnchecked();
        pack();
        repaint();
    }

    private void btnArtistActionPerformed(ActionEvent e) {
        panDashboard.removeAll();
        panDashboard.add(panArtists, BorderLayout.CENTER);

        btnSongs.setUnchecked();
        btnArtist.setChecked();
        btnAlbums.setUnchecked();
        btnAddFolder.setUnchecked();
        pack();
        repaint();
    }

    private void btnAddFolderActionPerformed(ActionEvent e) {
        btnSongs.setUnchecked();
        btnArtist.setUnchecked();
        btnAlbums.setUnchecked();
        btnAddFolder.setChecked();
       
        
        songImporterDialog.importFolder();
        
        pack();
        repaint();
    }


    public void refresh() {
        panSongs.refresh();
        panAlbums.refresh();
        panArtists.refresh();
        //panGenres.refresh();
    }
}
