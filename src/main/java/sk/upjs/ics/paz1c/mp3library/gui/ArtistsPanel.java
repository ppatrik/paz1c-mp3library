package sk.upjs.ics.paz1c.mp3library.gui;

import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
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

    @Override
    public void refresh() {
        artistSongsTableModel.refresh();
        lstArtists.setListData(artistDao.findAll().toArray());
    }

}
