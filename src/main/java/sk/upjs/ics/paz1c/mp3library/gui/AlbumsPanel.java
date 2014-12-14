package sk.upjs.ics.paz1c.mp3library.gui;

import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import sk.upjs.ics.paz1c.mp3library.Album;
import sk.upjs.ics.paz1c.mp3library.AlbumDao;
import sk.upjs.ics.paz1c.mp3library.BeanFactory;

class AlbumsPanel extends JPanel implements PanelInterface {

    private final AlbumDao albumDao = BeanFactory.INSTANCE.albumDao();

    private final AlbumSongsTableModel albumSongsTableModel = new AlbumSongsTableModel();
    private final JTable tblAlbumSongs = new JTable(albumSongsTableModel);
    private final JScrollPane tblSongsScrollPane = new JScrollPane(tblAlbumSongs);

    private final AlbumPaneListCellRenderer albumPaneListCellRenderer = new AlbumPaneListCellRenderer();
    private final JList lstAlbums = new JList();
    private final JScrollPane lstAlbumsScrollPane = new JScrollPane(lstAlbums);

    public AlbumsPanel() {
        super(new BorderLayout());
        
        tblAlbumSongs.setModel(albumSongsTableModel);
        tblAlbumSongs.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblAlbumSongs.setAutoCreateRowSorter(true);
        tblAlbumSongs.setDragEnabled(false);
        tblAlbumSongs.getTableHeader().setReorderingAllowed(false);
        tblAlbumSongs.addMouseListener(new TableClickListener());

        lstAlbums.setCellRenderer(albumPaneListCellRenderer);
        lstAlbums.setPrototypeCellValue(getPrototypeBookValue());
        
        refresh();

        add(lstAlbumsScrollPane, BorderLayout.WEST);
        add(tblSongsScrollPane, BorderLayout.CENTER);
    }

    private Album getPrototypeBookValue() {
        Album album = new Album();
        album.setId(1l);
        album.setName("Veeeeeery looooong naaaaame");

        return album;
    }

    @Override
    public void refresh() {
        albumSongsTableModel.refresh();
        lstAlbums.setListData(albumDao.findAll().toArray());
    }

}
