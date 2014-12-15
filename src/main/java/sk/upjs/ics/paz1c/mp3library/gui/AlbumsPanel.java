package sk.upjs.ics.paz1c.mp3library.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import sk.upjs.ics.paz1c.mp3library.Album;
import sk.upjs.ics.paz1c.mp3library.AlbumDao;
import sk.upjs.ics.paz1c.mp3library.BeanFactory;

class AlbumsPanel extends JPanel implements PanelInterface {

    private final AlbumDao albumDao = BeanFactory.INSTANCE.albumDao();

    private final AlbumSongsTableModel albumSongsTableModel = new AlbumSongsTableModel();
    private final JTable tblAlbumSongs = new JTable(albumSongsTableModel);
    private final JScrollPane tblSongsScrollPane = new JScrollPane(tblAlbumSongs);

    private final AlbumPaneListCellRenderer albumPaneListCellRenderer = new AlbumPaneListCellRenderer();
    private final AlbumListModel albumListModel = new AlbumListModel();
    private final JList lstAlbums = new JList();
    private final JScrollPane lstAlbumsScrollPane = new JScrollPane(lstAlbums);

    public AlbumsPanel() {
        super(new BorderLayout());

        tblAlbumSongs.setModel(albumSongsTableModel);
        tblAlbumSongs.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblAlbumSongs.setAutoCreateRowSorter(true);
        tblAlbumSongs.setDragEnabled(false);
        tblAlbumSongs.getTableHeader().setReorderingAllowed(false);
        tblAlbumSongs.addMouseListener(new SongClickListener());

        lstAlbums.setCellRenderer(albumPaneListCellRenderer);
        lstAlbums.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstAlbums.setPrototypeCellValue(getPrototypeBookValue());
        lstAlbums.setModel(albumListModel);
        lstAlbums.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    lstAlbumsSelectionChanged(e);
                }
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
        lstAlbums.setComponentPopupMenu(createLstAlbumsPopupMenu());

        refresh();

        lstAlbums.setSelectedIndex(0);
        lstAlbumsSelectionChanged(null);

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

    protected JPopupMenu createLstAlbumsPopupMenu() {
        JPopupMenu popupMenu = new JPopupMenu();

        popupMenu.add(new AbstractAction("Upraviť album") {
            @Override
            public void actionPerformed(ActionEvent e) {
                popupLstAlbumsEditActionPerformed(e);
            }
        });

        popupMenu.add(new AbstractAction("Zmazať album") {
            @Override
            public void actionPerformed(ActionEvent e) {
                popupLstAlbumsDeleteActionPerformed(e);
            }
        });

        return popupMenu;
    }

    private void popupLstAlbumsDeleteActionPerformed(ActionEvent e) {
        Album album = (Album) lstAlbums.getSelectedValue();
        if (album != null) {
            int result = DialogUtils.yesNoDialog(this,
                    "The album will be deleted! "
                    + album.getName());
            if (result == JOptionPane.YES_OPTION) {
                BeanFactory.INSTANCE.albumDao().delete(album);
            }
        }
        GuiFactory.INSTANCE.mainDashboardForm().refresh();
    }

    private void popupLstAlbumsEditActionPerformed(ActionEvent e) {
        Album album = (Album) lstAlbums.getSelectedValue();
        if (album != null) {
            AlbumEditForm albumEditForm = new AlbumEditForm(GuiFactory.INSTANCE.mainDashboardForm(), album);
            albumEditForm.setVisible(true);
        }
    }

    @Override
    public void refresh() {
        albumSongsTableModel.refresh();
        albumListModel.refresh();
    }

}
