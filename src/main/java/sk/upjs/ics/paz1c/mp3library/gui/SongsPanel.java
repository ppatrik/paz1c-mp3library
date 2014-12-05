/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.paz1c.mp3library.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author patrik
 */
class SongsPanel extends JPanel {

    private final SongsTableModel songsTableModel = new SongsTableModel();
    private final JTable songsTable = new JTable();
    private final JScrollPane scrollPane = new JScrollPane(songsTable);
    
    public SongsPanel() {
        super(new BorderLayout());
        songsTableModel.refresh();
        songsTable.setModel(songsTableModel);
        
        add(scrollPane, BorderLayout.CENTER);
    }

}
