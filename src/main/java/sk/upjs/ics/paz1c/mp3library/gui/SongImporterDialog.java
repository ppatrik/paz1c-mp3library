package sk.upjs.ics.paz1c.mp3library.gui;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import sk.upjs.ics.paz1c.mp3library.SongImporter;
import sk.upjs.ics.paz1c.mp3library.SongImporterListener;

public class SongImporterDialog {

    SongImporter songImporter = new SongImporter();

    public SongImporterDialog() {
        songImporter.setListener(new Listener());
    }

    public class Listener implements SongImporterListener {

        private LoaderFrame frame = null;
        @Override
        public void started() {
            frame = new LoaderFrame();
            frame.setVisible(true);
        }

        @Override
        public void statusChanges(String message) {
            frame.setMessage(message);
        }

        @Override
        public void finished() {
            frame.setVisible(false);
            frame.dispose();
        }

    }

    public void importFolder() {
        JFileChooser j = new JFileChooser(new File("c:\\_data\\music\\"));
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        j.setDialogTitle("Vyberte priečinok s hudbou");
        j.showOpenDialog(new JFrame());
        if (j.getSelectedFile() == null) {
            return;
        }
        songImporter.importFolder(j.getSelectedFile());
    }

    public void importSong() {
        JFileChooser j = new JFileChooser(new File("c:\\_data\\music\\"));
        j.setFileSelectionMode(JFileChooser.FILES_ONLY);
        j.setDialogTitle("Vyberte mp3 subor");
        j.showOpenDialog(new JFrame());
        if (j.getSelectedFile() == null) {
            return;
        }
        songImporter.importSong(j.getSelectedFile());
    }
}
