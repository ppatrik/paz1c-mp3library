package sk.upjs.ics.paz1c.mp3library.gui;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import sk.upjs.ics.paz1c.mp3library.Genre;

public class GenreListCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Genre genre = (Genre) value;
        /*if (Album.isNull(album)) {
            album.setName("-- Select -- ");
        }*/
        return super.getListCellRendererComponent(list, genre.getName(), index, isSelected, cellHasFocus);
    }
}
