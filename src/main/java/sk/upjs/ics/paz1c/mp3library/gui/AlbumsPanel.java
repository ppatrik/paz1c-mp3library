package sk.upjs.ics.paz1c.mp3library.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import sk.upjs.ics.paz1c.mp3library.Album;
import sk.upjs.ics.paz1c.mp3library.AlbumDao;
import sk.upjs.ics.paz1c.mp3library.BeanFactory;
import sk.upjs.ics.paz1c.mp3library.Song;

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
        lstAlbums.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                lstAlbumsSelectionChanged(e);
            }
        });
        lstAlbums.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    lstAlbumsMouseDoubleClicked(e);
                }
            }
        });
        //lstAlbums.setComponentPopupMenu(createLstBooksPopupMenu());

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

    private void lstAlbumsSelectionChanged(ListSelectionEvent e) {
        lstAlbumsOpenAlbum();
    }

    private void lstAlbumsMouseDoubleClicked(MouseEvent e) {
        lstAlbumsOpenAlbum();
    }

    private void lstAlbumsOpenAlbum() {
        Album album = (Album) lstAlbums.getSelectedValue();
        if (album != null) {
            albumSongsTableModel.setAlbum(album);
            refresh();
        }
    }

    @Override
    public void refresh() {
        albumSongsTableModel.refresh();
        lstAlbums.setListData(albumDao.findAll().toArray());
    }

}
