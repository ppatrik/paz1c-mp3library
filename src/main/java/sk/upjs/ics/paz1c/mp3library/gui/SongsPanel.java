package sk.upjs.ics.paz1c.mp3library.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import sk.upjs.ics.paz1c.mp3library.Song;

class SongsPanel extends JPanel implements PanelInterface {

    private final SongsTableModel songsTableModel = new SongsTableModel();
    private final JTable tblSongs = new JTable();
    private final JScrollPane scrollPane = new JScrollPane(tblSongs);

    public SongsPanel() {
        super(new BorderLayout());
        
        tblSongs.setModel(songsTableModel);
        tblSongs.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblSongs.setAutoCreateRowSorter(true);
        tblSongs.setDragEnabled(false);
        tblSongs.getTableHeader().setReorderingAllowed(false);
        tblSongs.addMouseListener(new TableClickListener());

        refresh();
        
        add(scrollPane, BorderLayout.CENTER);
    }
    public Song getSong(){
        return songsTableModel.getValueAt(tblSongs.getSelectedRow());
    }
    
    
    @Override
    public void refresh() {
        songsTableModel.refresh();
    }

}
