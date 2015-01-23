/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.paz1c.mp3library.gui;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import sk.upjs.ics.paz1c.mp3library.Artist;

/**
 *
 * @author patrik
 */
public class ArtistListCellRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Artist artist = (Artist) value;
        /*if (Album.isNull(artist)) {
            artist.setName("-- Select -- ");
        }*/
        return super.getListCellRendererComponent(list, artist.getName(), index, isSelected, cellHasFocus);
    }
}
