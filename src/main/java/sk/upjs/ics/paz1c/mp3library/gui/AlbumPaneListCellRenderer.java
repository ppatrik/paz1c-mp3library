package sk.upjs.ics.paz1c.mp3library.gui;

import java.awt.Component;
import java.net.URL;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import sk.upjs.ics.paz1c.mp3library.Album;

/**
 *
 * @author patrik
 */
class AlbumPaneListCellRenderer implements ListCellRenderer<Album> {

    //private ImageCoverService imageCoverService = BeanFactory.INSTANCE.imageCoverService();
    private DefaultListCellRenderer delegate = new DefaultListCellRenderer();

    private Icon getIcon(Album album) {
        //ImageIcon imageCover = imageCoverService.getImageCover(book);
        //if (imageCover == null) {
            URL bookIconUrl = AlbumPaneListCellRenderer.class.getResource("book-icon.png");
            return new ImageIcon(bookIconUrl);
        //}
        //return imageCover;
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Album> list, Album album, int index, boolean isSelected, boolean cellHasFocus) {
        Component component = delegate.getListCellRendererComponent(list, album, index, isSelected, cellHasFocus);
        if (component instanceof JLabel) {
            JLabel label = (JLabel) component;
            label.setText(album.getName());
            label.setIcon(getIcon(album));
            return label;
        } else {
            throw new ClassCastException("Illegal JList component type. "
                    + "Expected JLabel, but found "
                    + component.getClass());
        }
    }
}
