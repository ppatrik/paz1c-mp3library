/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.paz1c.mp3library.gui;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import sk.upjs.ics.paz1c.mp3library.Album;

/**
 *
 * @author patrik
 */
class AlbumListCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Album album = (Album) value;
        if (Album.isNull(album)) {
            album.setName("-- Select -- ");
        }
        return super.getListCellRendererComponent(list, album.getName().equals("") ? album.getId() : album.getName(), index, isSelected, cellHasFocus);
    }
}
