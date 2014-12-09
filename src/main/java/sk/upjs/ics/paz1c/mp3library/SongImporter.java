package sk.upjs.ics.paz1c.mp3library;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import sk.upjs.ics.paz1c.mp3library.gui.SongImporterDialog;

public class SongImporter {

    private SongImporterListener listener;
    private SongDao songDao = BeanFactory.INSTANCE.songDao();

    private Thread scannerThread;
    private List<Song> songs = new ArrayList<>();

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
        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".mp3")) {
                listener.statusChanges(file.getName());
                songs.add(fromFile(file));

            }
            if (file.isDirectory()) {
                scanFolderDo(file);
            }
            Thread.sleep(0);
        }
    }

    private Song fromFile(File file) {
        try {

            AudioFile f = AudioFileIO.read(file);
            Tag tag = f.getTag();

            Song newSong = songDao.findByFilePath(file);
            if (newSong == null) {
                newSong = new Song();
            }

            // TODO: nacitat do vsetkych objektov
            newSong.setTitle(tag.getFirst(FieldKey.TITLE));
            Artist novyArtist = new Artist();
            novyArtist.setName(tag.getFirst(FieldKey.ARTIST));
            newSong.setArtist(novyArtist);
            Album novyAlbum = new Album();
            novyAlbum.setName(tag.getFirst(FieldKey.ALBUM));
            newSong.setAlbum(novyAlbum);

            return newSong;
        } catch (CannotReadException | IOException | TagException | ReadOnlyFileException | InvalidAudioFrameException ex) {
            // subor je chybny
        }
        return null;
    }

    private void saveListToDatabase() {
        System.out.println("poslat list do dao a ulozit ho");
    }

}
