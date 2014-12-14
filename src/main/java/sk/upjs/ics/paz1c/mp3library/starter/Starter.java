package sk.upjs.ics.paz1c.mp3library.starter;

import com.jgoodies.looks.windows.WindowsLookAndFeel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import sk.upjs.ics.paz1c.mp3library.gui.GuiFactory;
import sk.upjs.ics.paz1c.mp3library.gui.MainDashboardForm;

public class Starter {

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new WindowsLookAndFeel());

        MainDashboardForm mainDashboardForm = GuiFactory.INSTANCE.mainDashboardForm();
        mainDashboardForm.setDefaultCloseOperation(MainDashboardForm.EXIT_ON_CLOSE);
        mainDashboardForm.setVisible(true);
        
        // Swing-shell sa nezatvaral preto System exit
        mainDashboardForm.addWindowListener(new WindowAdapter() {
            
            @Override
            public void windowClosing(WindowEvent e) {
                JFrame f = (JFrame) e.getComponent();
                f.dispose();
                System.exit(0);
            }
        });
    }
}
