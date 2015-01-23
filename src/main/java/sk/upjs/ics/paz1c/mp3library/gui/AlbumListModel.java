/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.paz1c.mp3library.gui;

import java.util.LinkedList;
import java.util.List;
import javax.swing.AbstractListModel;
import sk.upjs.ics.paz1c.mp3library.Album;
import sk.upjs.ics.paz1c.mp3library.AlbumDao;
import sk.upjs.ics.paz1c.mp3library.BeanFactory;

/**
 *
 * @author patrik
 */
class AlbumListModel extends AbstractListModel {

    private final AlbumDao albumDao = BeanFactory.INSTANCE.albumDao();

    private final List<Album> albums = new LinkedList<Album>();

    @Override
    public int getSize() {
        return albums.size();
    }

    @Override
    public Object getElementAt(int index) {
        return albums.get(index);
    }

    public void refresh() {
        albums.clear();
        albums.addAll(albumDao.findAll());

        fireContentsChanged(this, 0, getSize());
    }

}
