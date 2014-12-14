package sk.upjs.ics.paz1c.mp3library.gui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import sk.upjs.ics.paz1c.mp3library.Artist;
import sk.upjs.ics.paz1c.mp3library.ArtistDao;
import sk.upjs.ics.paz1c.mp3library.BeanFactory;

class ArtistsPanel extends JPanel implements PanelInterface {

    private final ArtistDao artistDao = BeanFactory.INSTANCE.artistDao();

    private final ArtistSongsTableModel artistSongsTableModel = new ArtistSongsTableModel();
    private final JTable tblArtistSongs = new JTable(artistSongsTableModel);
    private final JScrollPane tblSongsScrollPane = new JScrollPane(tblArtistSongs);

    private final ArtistPaneListCellRenderer artistPaneListCellRenderer = new ArtistPaneListCellRenderer();
    private final JList lstArtists = new JList();
    private final JScrollPane lstArtistScrollPane = new JScrollPane(lstArtists);

    public ArtistsPanel() {
        super(new BorderLayout());

        tblArtistSongs.setModel(artistSongsTableModel);
        tblArtistSongs.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblArtistSongs.setAutoCreateRowSorter(true);
        tblArtistSongs.setDragEnabled(false);
        tblArtistSongs.getTableHeader().setReorderingAllowed(false);
        tblArtistSongs.addMouseListener(new TableClickListener());

        lstArtists.setCellRenderer(artistPaneListCellRenderer);
        lstArtists.setPrototypeCellValue(getPrototypeBookValue());
        lstArtists.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                lstArtistsSelectionChanged(e);
            }
        });
        lstArtists.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    lstArtistsMouseDoubleClicked(e);
                }
            }
        });
        //lstArtists.setComponentPopupMenu(createLstBooksPopupMenu());

        refresh();

        add(lstArtistScrollPane, BorderLayout.WEST);
        add(tblSongsScrollPane, BorderLayout.CENTER);
    }

    private Artist getPrototypeBookValue() {
        Artist artist = new Artist();
        artist.setId(1l);
        artist.setName("Veeeeeery looooong naaaaame");

        return artist;
    }

    private void lstArtistsSelectionChanged(ListSelectionEvent e) {
        lstArtistsOpenAlbum();
    }

    private void lstArtistsMouseDoubleClicked(MouseEvent e) {
        lstArtistsOpenAlbum();
    }

    private void lstArtistsOpenAlbum() {
        Artist artist = (Artist) lstArtists.getSelectedValue();
        if (artist != null) {
            artistSongsTableModel.setArtist(artist);
            refresh();
        }
    }

    @Override
    public void refresh() {
        artistSongsTableModel.refresh();
        lstArtists.setListData(artistDao.findAll().toArray());
    }

}
