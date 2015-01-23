/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.paz1c.mp3library.gui;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import sk.upjs.ics.paz1c.mp3library.BeanFactory;
import sk.upjs.ics.paz1c.mp3library.Song;
import sk.upjs.ics.paz1c.mp3library.SongDao;

/**
 *
 * @author patrik
 */
public class SongPopupMenu extends JPopupMenu {

    SongDao songDao = BeanFactory.INSTANCE.songDao();

    public void generate(final Song song, final Component c, final JFrame opener) {
        removeAll();

        JMenuItem playItem = new JMenuItem("Spustiť pieseň");
        playItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().open(song.getFile_path());
                } catch (IOException ex) {
                    System.err.println("Nepodarilo sa otvorit");
                }
            }
        });
        add(playItem);

        JMenuItem editItem = new JMenuItem("Upraviť informácie");
        editItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (song != null) {
                    SongEditForm songEditForm = new SongEditForm(opener, song);
                    songEditForm.setVisible(true);
                }
            }
        });
        add(editItem);

        JMenuItem deleteItem = new JMenuItem("Zmazať z knižnice");
        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (song != null) {
                    int result = DialogUtils.yesNoDialog(c,
                            "Naozaj chcete zmazať? "
                            + song.getTitle());
                    if (result == JOptionPane.YES_OPTION) {
                        songDao.delete(song);
                        GuiFactory.INSTANCE.mainDashboardForm().refresh();
                    }
                }
            }
        });
        add(deleteItem);
    }
}
