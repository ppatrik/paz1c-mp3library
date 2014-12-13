package sk.upjs.ics.paz1c.mp3library.gui;

import com.jgoodies.looks.windows.WindowsLookAndFeel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import net.miginfocom.swing.MigLayout;
import sk.upjs.ics.paz1c.mp3library.gui.components.ImageJButton;

public class MainDashboardForm extends JFrame {

    private final ImageJButton btnSongs = new ImageJButton("songs.png");
    private final ImageJButton btnAlbums = new ImageJButton("albums.png");
    private final ImageJButton btnArtist = new ImageJButton("artists.png");
    private final JButton btnAddFolder = new JButton("Add Folder");
    private final JButton btnAddFile = new JButton("Add File");

    private final JPanel panNavigation;
    private final JPanel panDashboard = new JPanel(new BorderLayout());

    private final JTable tblTabulka = new JTable();

    private JPanel panSongs;
    private JPanel panAlbums;
    private JPanel panArtists;
    private JPanel panGenres;

    private final SongImporterDialog songImporter = new SongImporterDialog();

    public MainDashboardForm() {
        setTitle("MP3 Library");

        setLayout(new BorderLayout());

        panNavigation = createNavigationPanel();
        add(panNavigation, BorderLayout.WEST);
        add(panDashboard, BorderLayout.CENTER);

        btnSongsActionPerformed(null);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setPreferredSize(new Dimension(800, 600));

        pack();
    }

    private JPanel createNavigationPanel() {
        JPanel navigationPanel = new JPanel(new MigLayout("gap rel 0, insets 0"));
        navigationPanel.add(btnSongs, "wrap, grow, h 30%");
        navigationPanel.add(btnAlbums, "wrap, grow, h 30%");
        navigationPanel.add(btnArtist, "wrap, grow, h 30%");
        navigationPanel.add(btnAddFile, "wrap, grow, h 5%");
        navigationPanel.add(btnAddFolder, "wrap, grow, h 5%");

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

        btnAddFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAddFileActionPerformed(e);
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
        if (panSongs == null) {
            panSongs = new SongsPanel();
        }
        panDashboard.removeAll();
        panDashboard.add(panSongs, BorderLayout.CENTER);
        
        btnSongs.setChecked();
        btnArtist.setUnchecked();
        btnAlbums.setUnchecked();

        pack();
        repaint();
    }

    private void btnAlbumsActionPerformed(ActionEvent e) {
        if (panAlbums == null) {
            panAlbums = new AlbumsPanel();
        }
        panDashboard.removeAll();
        panDashboard.add(panAlbums, BorderLayout.CENTER);

        btnSongs.setUnchecked();
        btnArtist.setUnchecked();
        btnAlbums.setChecked();
        
        pack();
        repaint();
    }

    private void btnArtistActionPerformed(ActionEvent e) {
        if (panArtists == null) {
            panArtists = new AlbumsPanel();
        }
        panDashboard.removeAll();

        btnSongs.setUnchecked();
        btnArtist.setChecked();
        btnAlbums.setUnchecked();
        
        pack();
        repaint();
    }

    private void btnAddFileActionPerformed(ActionEvent e) {
        songImporter.importSong();
    }

    private void btnAddFolderActionPerformed(ActionEvent e) {
        songImporter.importFolder();
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new WindowsLookAndFeel());

        MainDashboardForm mainDashboardForm = new MainDashboardForm();
        mainDashboardForm.setDefaultCloseOperation(MainDashboardForm.DISPOSE_ON_CLOSE);
        mainDashboardForm.setVisible(true);
    }
}
