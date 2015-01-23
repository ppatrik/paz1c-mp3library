/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.paz1c.mp3library.gui;

import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import sk.upjs.ics.paz1c.mp3library.Song;

public class SongClickListener implements MouseListener {

    private Song getInfo(MouseEvent e) {
        JTable source = (JTable) e.getSource();
        int row = source.rowAtPoint(e.getPoint());

        int modelRow = source.convertRowIndexToModel(row);
        int column = source.columnAtPoint(e.getPoint());
        TableModel model = source.getModel();
        Song song = (Song) model.getValueAt(modelRow, -1);
        if (!source.isRowSelected(row)) {
            source.changeSelection(row, column, false, false);
        }
        return song;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger()) {
            Song song = getInfo(e);
            SongPopupMenu popupMenu = new SongPopupMenu();
            popupMenu.generate(song, e.getComponent(), GuiFactory.INSTANCE.mainDashboardForm());

            popupMenu.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
        if (e.getClickCount() == 2) {
            Song song = getInfo(e);
            try {
                Desktop.getDesktop().open(song.getFile_path());
            } catch (IOException ex) {
                System.err.println("Nepodarilo sa otvorit");
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
