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

public class MainDashboardForm extends JFrame {

    private final JButton btnSongs = new JButton("Songs");
    private final JButton btnAlbums = new JButton("Albums");
    private final JButton btnArtist = new JButton("Artist");
    private final JButton btnAddFolder = new JButton("Add Folder");
    private final JButton btnAddFile = new JButton("Add File");

    private final JPanel panNavigation;
    private final JPanel panDashboard = new JPanel(new BorderLayout());

    private final JTable tblTabulka = new JTable();

    private JPanel panSongs;
    private JPanel panAlbums;
    private JPanel panArtists;
    private JPanel panGenres;

    private SongImporterDialog songImporter = new SongImporterDialog();

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
        JPanel navigationPanel = new JPanel(new MigLayout());
        navigationPanel.add(btnSongs, "wrap");
        navigationPanel.add(btnAlbums, "wrap");
        navigationPanel.add(btnArtist, "wrap");
        navigationPanel.add(btnAddFile, "wrap");
        navigationPanel.add(btnAddFolder, "wrap");

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

        pack();
        repaint();
    }

    private void btnAlbumsActionPerformed(ActionEvent e) {
        if (panAlbums == null) {
            panAlbums = new AlbumsPanel();
        }
        panDashboard.removeAll();
        panDashboard.add(panAlbums, BorderLayout.CENTER);

        pack();
        repaint();
    }

    private void btnArtistActionPerformed(ActionEvent e) {
        if (panArtists == null) {
            panArtists = new AlbumsPanel();
        }
        panDashboard.removeAll();

        //panDashboard.add(panArtists, BorderLayout.CENTER);
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
