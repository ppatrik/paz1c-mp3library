package sk.upjs.ics.paz1c.mp3library.gui;

import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import sk.upjs.ics.paz1c.mp3library.BeanFactory;
import sk.upjs.ics.paz1c.mp3library.Song;
import sk.upjs.ics.paz1c.mp3library.SongDao;

public class SongsTableModel extends AbstractTableModel {

    protected static final int SONGS_COLUMN_COUNT = 7;

    protected static final int COLUMN_INDEX_TITLE = 1;
    protected static final int COLUMN_INDEX_TRACK = 0;
    protected static final int COLUMN_INDEX_ARTIST = 2;
    protected static final int COLUMN_INDEX_ALBUM = 3;
    protected static final int COLUMN_INDEX_GENRE = 4;
    protected static final int COLUMN_INDEX_DISC = 5;
    protected static final int COLUMN_INDEX_YEAR = 6;

    protected static final String[] COLUMN_NAMES = {"Číslo skladby", "Názov skladby", "Interpret", "Album", "Žáner", "Disk", "Rok"};

    private final SongDao songDao = BeanFactory.INSTANCE.songDao();

    private final List<Song> songs = new LinkedList<Song>();

    @Override
    public int getRowCount() {
        return songs.size();
    }

    @Override
    public int getColumnCount() {
        return SONGS_COLUMN_COUNT;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Song song = songs.get(rowIndex);
        switch (columnIndex) {
            case -1:
                return song;
            case COLUMN_INDEX_TITLE:
                return song.getTitle();
            case COLUMN_INDEX_ARTIST:
                if (song.getArtist() == null) {
                    return "";
                }
                return song.getArtist().getName();
            case COLUMN_INDEX_ALBUM:
                if (song.getAlbum() == null) {
                    return "";
                }
                return song.getAlbum().getName();
            case COLUMN_INDEX_GENRE:
                if (song.getGenre() == null) {
                    return "";
                }
                return song.getGenre().getName();
            case COLUMN_INDEX_TRACK:
                return song.getTrack();
            case COLUMN_INDEX_DISC:
                return song.getDisc();
            case COLUMN_INDEX_YEAR:
                return song.getYear();
        }
        return "-";
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    public Song getValueAt(int rowIndex) {
        return songs.get(rowIndex);
    }

    public void remove(int rowIndex) {
        // TODO: otazka ci naozaj zmazat
        songDao.delete(getValueAt(rowIndex));
        refresh();
    }

    public void refresh() {
        songs.clear();
        songs.addAll(songDao.findAll());

        fireTableDataChanged();
    }

}
