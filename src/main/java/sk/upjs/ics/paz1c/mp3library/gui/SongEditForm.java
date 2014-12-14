package sk.upjs.ics.paz1c.mp3library.gui;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import sk.upjs.ics.paz1c.mp3library.Album;
import sk.upjs.ics.paz1c.mp3library.Artist;
import sk.upjs.ics.paz1c.mp3library.BeanFactory;
import sk.upjs.ics.paz1c.mp3library.Genre;
import sk.upjs.ics.paz1c.mp3library.Song;

public class SongEditForm extends JDialog {

    private final JLabel lblTitle = new JLabel("Title:");
    private final JLabel lblArtist = new JLabel("Artist:");
    private final JLabel lblAlbum = new JLabel("Album:");
    private final JLabel lblYear = new JLabel("Year:");
    private final JLabel lblTrack = new JLabel("Track no:");
    private final JLabel lblDisc = new JLabel("Disc no:");
    private final JLabel lblGenre = new JLabel("Genre:");
    private final JLabel lblRating = new JLabel("Rating:");
    private final JLabel lblFile_path = new JLabel("Path:");
    private final JLabel lblCover = new JLabel("Cover:");
    private final JLabel lblQuality = new JLabel("Quality:");
    private final JLabel lblFormat = new JLabel("Format:");

    private final JTextField txtTitle = new JTextField();
    private final JComboBox cmbArtist = new JComboBox();
    private final JButton btnArtistAdd = new JButton("+");
    private final JComboBox cmbAlbum = new JComboBox();
    private final JButton btnAlbumAdd = new JButton("+");
    private final JTextField txtYear = new JTextField();
    private final JSpinner spTrack = new JSpinner();
    private final JSpinner spDisc = new JSpinner();
    private final JComboBox cmbGenre = new JComboBox();
    private final JButton btnGenreAdd = new JButton("+");
    private final JSpinner spRating = new JSpinner();
    private final JTextField txtFile_path = new JTextField();
    private final JTextField txtCover = new JTextField();
    private final JTextField txtQuality = new JTextField();
    private final JTextField txtFormat = new JTextField();

    private final JButton btnOk = new JButton("OK");
    private final JButton btnCancel = new JButton("Cancel");

    private final ArtistListCellRenderer artistListCellRenderer = new ArtistListCellRenderer();
    private final AlbumListCellRenderer albumListCellRenderer = new AlbumListCellRenderer();
    private final GenreListCellRenderer genreListCellRenderer = new GenreListCellRenderer();
    
    private Song song;

    public SongEditForm(Frame owner) {
        this(owner, new Song());
    }

    public SongEditForm(Frame owner, Song song) {
        super(owner, "Edit Song", /* modal*/ true);

        this.song = song;

        setLayout(new MigLayout("wrap 3, width 400:300:", "[][grow, fill][]", "[][][][][][][][][][][][][nogrid]"));

        /* -- Title - */
        add(lblTitle);
        add(txtTitle, "span 2");
        txtTitle.setText(song.getTitle());

        /* -- Artist - */
        add(lblArtist);
        refreshCmbArtistModel();
        cmbArtist.setRenderer(artistListCellRenderer);
        if (song.getArtist() != null) {
            cmbArtist.setSelectedItem(song.getArtist());
        }
        add(cmbArtist);

        add(btnArtistAdd);
        btnArtistAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnArtistAddActionPerformed(e);
            }
        });

        /* -- Album - */
        add(lblAlbum);
        refreshCmbAlbumModel();
        cmbAlbum.setRenderer(albumListCellRenderer);
        if (song.getAlbum()!= null) {
            cmbAlbum.setSelectedItem(song.getAlbum());
        }
        add(cmbAlbum);

        add(btnAlbumAdd);
        btnAlbumAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAlbumAddActionPerformed(e);
            }
        });

        /* -- Year - */
        add(lblYear);
        add(txtYear, "span 2");
        txtYear.setText(Integer.toString(song.getYear()));

        /* -- Track - */
        add(lblTrack);
        add(spTrack, "span 2");
        spTrack.setValue(song.getTrack());

        /* -- Disc - */
        add(lblDisc);
        add(spDisc, "span 2");
        spDisc.setValue(song.getDisc());

        /* -- Genre - */
        add(lblGenre);
        refreshCmbGenreModel();
        cmbGenre.setRenderer(genreListCellRenderer);
        if (song.getGenre() != null) {
            cmbGenre.setSelectedItem(song.getGenre());
        }
        add(cmbGenre);

        add(btnGenreAdd);
        btnGenreAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnGenreAddActionPerformed(e);
            }
        });

        /* -- Rating - */
        add(lblRating);
        add(spRating, "span 2");
        spRating.setValue(song.getRating());

        /* -- File_path - */
        add(lblFile_path);
        add(txtFile_path, "span 2");
        txtFile_path.setEditable(false);
        if (song.getFile_path() != null) {
            txtFile_path.setText(song.getFile_path().getAbsolutePath());
        }

        /* -- Cover - */
        add(lblCover);
        txtCover.setEditable(false);
        add(txtCover, "span 2");
        if (song.getCover() != null) {
            txtCover.setText(song.getCover().toString());
        }

        /* -- Quality - */
        add(lblQuality);
        txtQuality.setEditable(false);
        add(txtQuality, "span 2");
        txtQuality.setText(song.getQuality().toString());

        /* -- Format - */
        add(lblFormat);
        txtFormat.setEditable(false);
        add(txtFormat, "span 2");
        txtFormat.setText(song.getFormat());

        /* -- Buttons - */
        add(btnOk, "tag ok");
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnOkActionPerformed(e);
            }
        });

        add(btnCancel, "tag cancel");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnCancelActionPerformed(e);
            }
        });

        DialogUtils.addEscapeListener(this);

        pack();
        setLocationRelativeTo(null);
    }

    /*private JFormattedTextField.AbstractFormatter createYearFormatter() {
        try {
            return new MaskFormatter("####");
        } catch (ParseException e) {
            // should not happen
            throw new IllegalStateException("Illegal syntax for year formatter");
        }
    }*/

    private ComboBoxModel getArtistComboBoxModel() {
        List<Artist> artists = BeanFactory.INSTANCE.artistDao().findAll();
        return new DefaultComboBoxModel(artists.toArray());
    }

    private ComboBoxModel getAlbumComboBoxModel() {
        List<Album> albums = BeanFactory.INSTANCE.albumDao().findAll();
        return new DefaultComboBoxModel(albums.toArray());
    }

    private ComboBoxModel getGenreComboBoxModel() {
        List<Genre> genres = BeanFactory.INSTANCE.genreDao().findAll();
        return new DefaultComboBoxModel(genres.toArray());
    }

    private void btnArtistAddActionPerformed(ActionEvent e) {
        // TODO: pridanie noveho formatu
        refreshCmbArtistModel();
    }

    private void btnAlbumAddActionPerformed(ActionEvent e) {
        // TODO: pridanie noveho formatu
        refreshCmbAlbumModel();
    }

    private void btnGenreAddActionPerformed(ActionEvent e) {
        // TODO: pridanie noveho formatu
        refreshCmbGenreModel();
    }

    private void refreshCmbArtistModel() {
        cmbArtist.setModel(getArtistComboBoxModel());
    }

    private void refreshCmbAlbumModel() {
        cmbAlbum.setModel(getAlbumComboBoxModel());
    }

    private void refreshCmbGenreModel() {
        cmbGenre.setModel(getGenreComboBoxModel());
    }

    private void btnOkActionPerformed(ActionEvent e) {
        song.setTitle(txtTitle.getText());
        song.setArtist((Artist) cmbArtist.getSelectedItem());
        song.setAlbum((Album) cmbAlbum.getSelectedItem());
        song.setYear(Integer.valueOf(txtYear.getText()));
        song.setTrack((Integer) spTrack.getValue());
        song.setDisc((Integer) spDisc.getValue());
        song.setGenre((Genre) cmbGenre.getSelectedItem());
        song.setRating((Integer) spRating.getValue());
        // TODO: cover

        BeanFactory.INSTANCE.songDao().saveOrUpdate(song);
        GuiFactory.INSTANCE.mainDashboardForm().refresh();
                
        setVisible(false);
    }

    private void btnCancelActionPerformed(ActionEvent e) {
        setVisible(false);
    }
}
