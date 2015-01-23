/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.paz1c.mp3library.gui;

import java.util.LinkedList;
import java.util.List;
import javax.swing.AbstractListModel;
import sk.upjs.ics.paz1c.mp3library.Artist;
import sk.upjs.ics.paz1c.mp3library.ArtistDao;
import sk.upjs.ics.paz1c.mp3library.BeanFactory;

/**
 *
 * @author patrik
 */
class ArtistListModel extends AbstractListModel {

    private final ArtistDao artistDao = BeanFactory.INSTANCE.artistDao();

    private final List<Artist> artists = new LinkedList<Artist>();

    @Override
    public int getSize() {
        return artists.size();
    }

    @Override
    public Object getElementAt(int index) {
        return artists.get(index);
    }

    public void refresh() {
        artists.clear();
        artists.addAll(artistDao.findAll());

        fireContentsChanged(this, 0, getSize());
    }

}
