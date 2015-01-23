package sk.upjs.ics.paz1c.mp3library.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import sk.upjs.ics.paz1c.mp3library.Album;
import sk.upjs.ics.paz1c.mp3library.AlbumDao;
import sk.upjs.ics.paz1c.mp3library.BeanFactory;

public class AlbumPopupMenu extends JPopupMenu {

    AlbumDao albumDao = BeanFactory.INSTANCE.albumDao();

    public void generate(final Album album, final Component c, final JFrame opener) {
        removeAll();

        JMenuItem editItem = new JMenuItem("Upraviť album " + album.getName());
        editItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (album != null) {
                    AlbumEditForm albumEditForm = new AlbumEditForm(opener, album);
                    albumEditForm.setVisible(true);
                }
            }
        });
        add(editItem);

        JMenuItem deleteItem = new JMenuItem("Zmazať z knižnice");
        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (album != null) {
                    int result = DialogUtils.yesNoDialog(c,
                            "Naozaj chcete zmazať? "
                            + album.getName());
                    if (result == JOptionPane.YES_OPTION) {
                        albumDao.delete(album);
                    }
                }
            }
        });
        add(deleteItem);
    }
}
