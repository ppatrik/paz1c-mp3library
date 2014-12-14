package sk.upjs.ics.paz1c.mp3library.gui;

import java.awt.Component;
import java.net.URL;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import sk.upjs.ics.paz1c.mp3library.Artist;

class ArtistPaneListCellRenderer implements ListCellRenderer<Artist> {

    //private ImageCoverService imageCoverService = BeanFactory.INSTANCE.imageCoverService();
    private DefaultListCellRenderer delegate = new DefaultListCellRenderer();

    private Icon getIcon(Artist artist) {
        //ImageIcon imageCover = imageCoverService.getImageCover(book);
        //if (imageCover == null) {
            URL bookIconUrl = ArtistPaneListCellRenderer.class.getResource("book-icon.png");
            return new ImageIcon(bookIconUrl);
        //}
        //return imageCover;
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Artist> list, Artist artist, int index, boolean isSelected, boolean cellHasFocus) {
        Component component = delegate.getListCellRendererComponent(list, artist, index, isSelected, cellHasFocus);
        if (component instanceof JLabel) {
            JLabel label = (JLabel) component;
            label.setText(artist.getName());
            label.setIcon(getIcon(artist));
            return label;
        } else {
            throw new ClassCastException("Illegal JList component type. "
                    + "Expected JLabel, but found "
                    + component.getClass());
        }
    }
}
