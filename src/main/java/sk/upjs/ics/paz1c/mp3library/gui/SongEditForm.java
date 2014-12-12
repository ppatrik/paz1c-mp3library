package sk.upjs.ics.paz1c.mp3library.gui;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;
import net.miginfocom.swing.MigLayout;
import sk.upjs.ics.paz1c.mp3library.Album;
import sk.upjs.ics.paz1c.mp3library.AlbumDao;
import sk.upjs.ics.paz1c.mp3library.Artist;
import sk.upjs.ics.paz1c.mp3library.BeanFactory;
import sk.upjs.ics.paz1c.mp3library.Genre;
import sk.upjs.ics.paz1c.mp3library.Song;

public class SongEditForm extends JDialog {

    private JLabel lblTitle = new JLabel("Title:");

    private JLabel lblArtist = new JLabel("Artist:");

    private JLabel lblGenre = new JLabel("Genre:");
    ;
    
    private JLabel lblYear = new JLabel("Year:");

    private JLabel lblTrack = new JLabel("Track:");

    private JLabel lblAlbum = new JLabel("Album:");

    private JLabel lblQuality = new JLabel("Quality:");

    private JSpinner spTrack = new JSpinner();

    private JLabel lblRating = new JLabel("Rating:");

    private JSpinner spRating = new JSpinner();

    private JLabel lblDisc = new JLabel("Disc:");

    private JSpinner spDisc = new JSpinner();

    private JTextField txtQuality = new JTextField();

    private JTextField txtAlbum = new JTextField();

    private JTextField txtArtist = new JTextField();

    private JTextField txtTitle = new JTextField();
    ;
    
    private JComboBox cmbPublisher = new JComboBox();

    //   private ListCellRenderer albumListCellRenderer = new AlbumPaneListCellRenderer();
    private JButton btnGenreAdd = new JButton("+");

    private JFormattedTextField txtYear = new JFormattedTextField(createYearFormatter());

    private JLabel lblCover = new JLabel("Cover:");

    private JButton btnOk = new JButton("OK");

    private JButton btnCancel = new JButton("Cancel");

    private Song song;

    public SongEditForm(Frame owner) {
        this(owner, new Song());
    }

    public SongEditForm(Frame owner, Song song) {
        super(owner, "Edit Song", /* modal*/ true);

        this.song = song;

        setLayout(new MigLayout("wrap 3, width 200:300:", "[][grow, fill][]", "[][][][][][][][][][nogrid]"));

        /* -- Titles - */
        add(lblTitle);

        add(txtTitle, "span 2");
        txtTitle.setText(song.getTitle());

        /* -- Artists-*/
        add(lblArtist);
        add(txtArtist, "span 2");
        txtArtist.setText(song.getArtist().getName());

        /* -- Genre - */
        add(lblGenre);

        refreshCmbPublisherModel();
        //  cmbPublisher.setRenderer(albumListCellRenderer);
        if (song.getGenre() != null) {
            cmbPublisher.setSelectedItem(song.getGenre());
        }

        add(cmbPublisher);

        add(btnGenreAdd);
        btnGenreAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //   btnPublisherAddActionPerformed(e);
            }
        });

        /* -- Album */
        add(lblAlbum);
        add(txtAlbum, "span 2");
        txtAlbum.setText(song.getAlbum().getName());

        /* -- Year - */
        add(lblYear);

        add(txtYear, "span 2");
        txtYear.setText(Integer.toString(song.getYear()));

        /* -- Rating */
        add(lblRating);
        add(spRating, "span 2");
        spRating.setValue(song.getRating());

        /* -- TRacks */
        add(lblTrack);

        add(spTrack, "span 2");
        spTrack.setValue(song.getTrack());

        /* -- DIsc */
        add(lblDisc);
        add(spDisc, "span 2");
        spDisc.setValue(song.getDisc());

        /*-- Quality */
        add(lblQuality);
        add(txtQuality, "span 2");
        txtQuality.setText(song.getQuality().toString());

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

    private JFormattedTextField.AbstractFormatter createYearFormatter() {
        try {
            return new MaskFormatter("####");
        } catch (ParseException e) {
            // should not happen
            throw new IllegalStateException("Illegal syntax for year formatter");
        }
    }

    private ComboBoxModel getPublisherComboBoxModel() {
        List<Genre> genres = BeanFactory.INSTANCE.genreDao().findAll();
        return new DefaultComboBoxModel(genres.toArray());
    }

    private void btnOkActionPerformed(ActionEvent e) {
        song.setTitle(txtTitle.getText());

        song.setGenre((Genre) cmbPublisher.getSelectedItem());

        song.setYear(Integer.valueOf(txtYear.getText()));

        Artist newArtist = new Artist();
        newArtist.setName(txtArtist.getText());
        song.setArtist(newArtist);

        song.setTrack((Integer) spTrack.getValue());

        Album newAlbum = new Album();
        newAlbum.setName(txtAlbum.getText());
        song.setAlbum(newAlbum);

        song.setRating((Integer) spRating.getValue());

        BeanFactory.INSTANCE.songDao().saveOrUpdate(song);

        setVisible(false);
    }

    private void btnCancelActionPerformed(ActionEvent e) {
        setVisible(false);
    }


    /* private void btnGenreAddActionPerformed(ActionEvent e) {
        
     refreshCmbPublisherModel();
     }*/
    private void refreshCmbPublisherModel() {
        cmbPublisher.setModel(getPublisherComboBoxModel());
    }
}
