package sk.upjs.ics.paz1c.mp3library.gui;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
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

            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    frame = new LoaderFrame();
                    frame.setVisible(true);
                }
            });

        }

        @Override
        public void statusChanges(final String message) {
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    frame.setMessage(message);
                }
            });

        }

        @Override
        public void finished() {
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    frame.setVisible(false);
                    frame.dispose();
                    GuiFactory.INSTANCE.mainDashboardForm().refresh();
                }
            });
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

}
