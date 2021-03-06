package sk.upjs.ics.paz1c.mp3library;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.id3.ID3v24Tag;

public class SongImporter {

    private SongImporterListener listener;
    private final SongDao songDao = BeanFactory.INSTANCE.songDao();

    private Thread scannerThread;
    private final List<Song> songs = new ArrayList<>();

    public void setListener(SongImporterListener listener) {
        this.listener = listener;
    }

    public void importFolder(File folder) {
        scanFolder(folder);
    }

    public void importSong(File file) {
        songs.add(fromFile(file));
        saveListToDatabase();
    }

    private void scanFolder(final File dir) {
        scannerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                listener.started();
                boolean interrupted = false;
                try {
                    scanFolderDo(dir);
                    saveListToDatabase();
                } catch (InterruptedException e) {
                    interrupted = true;
                }
                if (interrupted) {
                    listener.statusChanges("!INTERRUPTED! Scan finished (songs "
                            + songs.size() + ")");
                } else {
                    listener.statusChanges("Scan finished (songs "
                            + songs.size() + ")");
                }
                listener.finished();
            }
        });
        scannerThread.start();
    }

    private void scanFolderDo(File dir) throws InterruptedException {
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".mp3")) {
                listener.statusChanges(file.getName());
                Song song = fromFile(file);
                if (song != null) {
                    songs.add(song);
                }

            }
            if (file.isDirectory()) {
                scanFolderDo(file);
            }
            //Thread.sleep(0);
        }
    }

    private Song fromFile(File file) {
        try {

            MP3File f = (MP3File) AudioFileIO.read(file);
            ID3v24Tag tag = f.getID3v2TagAsv24();

            if (tag == null) {
                return null;
            }

            Song newSong = songDao.findByFilePath(file);
            if (newSong == null) {
                newSong = new Song();
            }

            newSong.setTitle(tag.getFirst(FieldKey.TITLE));

            Artist novyArtist = new Artist();
            novyArtist.setName(tag.getFirst(FieldKey.ARTIST));
            newSong.setArtist(novyArtist);

            Album novyAlbum = new Album();
            novyAlbum.setTracks(parseInt(tag.getFirst(FieldKey.TRACK_TOTAL)));
            novyAlbum.setTracks(parseInt(tag.getFirst(FieldKey.DISC_TOTAL)));
            novyAlbum.setName(tag.getFirst(FieldKey.ALBUM));
            newSong.setAlbum(novyAlbum);

            Genre newGenre = new Genre();
            newGenre.setName(tag.getFirst(FieldKey.GENRE));
            newSong.setGenre(newGenre);

            newSong.setTrack(parseInt(tag.getFirst(FieldKey.TRACK)));
            newSong.setYear(parseInt(tag.getFirst(FieldKey.YEAR)));
            newSong.setFile_path(file);

            return newSong;
        } catch (CannotReadException | IOException | TagException | ReadOnlyFileException | InvalidAudioFrameException | KeyNotFoundException e) {
            // ked uz bude chyba ktoru mi neobjavil ani sken terabajtu dát :)
            System.err.println("Unknown exception while loading " + file.toString());
        }
        return null;
    }

    private Integer parseInt(String integer) {
        try {
            return Integer.parseInt(integer);
        } catch (Exception e) {
            return null;
        }
    }

    private void saveListToDatabase() {
        for (Song song : songs) {
            listener.statusChanges("Saving: " + song.getTitle());
            songDao.saveOrUpdate(song);
        }
    }

}
