/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.paz1c.mp3library.gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

/**
 *
 * @author patrik
 */
class SongsPanel extends JPanel {

    private final JFrame opener;
    private final SongsTableModel songsTableModel = new SongsTableModel();
    private final JTable songsTable = new JTable();
    private final JScrollPane scrollPane = new JScrollPane(songsTable);

    public SongsPanel(JFrame opener) {
        super(new BorderLayout());
        
        this.opener = opener;
        
        songsTableModel.refresh();
        songsTable.setModel(songsTableModel);
        songsTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        songsTable.setAutoCreateRowSorter(true);
        songsTable.setDragEnabled(false);
        songsTable.getTableHeader().setReorderingAllowed(false);

        songsTable.addMouseListener(new TableClickListener(this.opener));

        add(scrollPane, BorderLayout.CENTER);
    }

}
