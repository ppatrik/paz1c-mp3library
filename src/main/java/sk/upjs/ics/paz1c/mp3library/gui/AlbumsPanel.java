/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.paz1c.mp3library.gui;

import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import sk.upjs.ics.paz1c.mp3library.Album;
import sk.upjs.ics.paz1c.mp3library.AlbumDao;
import sk.upjs.ics.paz1c.mp3library.BeanFactory;

/**
 *
 * @author patrik
 */
class AlbumsPanel extends JPanel {

    private final AlbumDao albumDao = BeanFactory.INSTANCE.albumDao();
    
    private final AlbumSongsTableModel albumSongsTableModel = new AlbumSongsTableModel();
    private final JTable tblSongs = new JTable(albumSongsTableModel);
    private final JScrollPane tblSongsScrollPane = new JScrollPane(tblSongs);

    private final AlbumPaneListCellRenderer albumPaneListCellRenderer = new AlbumPaneListCellRenderer();
    private final JList lstAlbums = new JList();
    private final JScrollPane lstAlbumsScrollPane = new JScrollPane(lstAlbums);

    public AlbumsPanel() {
        super(new BorderLayout());
        albumSongsTableModel.refresh();

        lstAlbums.setCellRenderer(albumPaneListCellRenderer);
        //lstAlbums.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        //lstAlbums.setVisibleRowCount(-1);
        lstAlbums.setPrototypeCellValue(getPrototypeBookValue());
        lstAlbums.setListData(albumDao.findAll().toArray());


        add(lstAlbumsScrollPane, BorderLayout.WEST);
        add(tblSongsScrollPane, BorderLayout.CENTER);
    }

    private Album getPrototypeBookValue() {
        Album album = new Album();
        album.setId(1l);
        album.setName("Veeeeeery looooong naaaaame");
        
        return album;
    }

}
