package sk.upjs.ics.paz1c.mp3library.gui;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;
import sk.upjs.ics.paz1c.mp3library.Album;
import sk.upjs.ics.paz1c.mp3library.BeanFactory;

public class AlbumEditForm extends JDialog {

    private final JLabel lblName = new JLabel("Name:");
    private final JLabel lblTracks = new JLabel("Tracks:");
    private final JLabel lblDiscs = new JLabel("Discs:");
    
    
    private final JTextField txtName = new JTextField();
    private final JFormattedTextField txtTracks = new JFormattedTextField(NumberFormat.getNumberInstance());
    private final JFormattedTextField txtDiscs = new JFormattedTextField(NumberFormat.getNumberInstance());

    private final JButton btnOk = new JButton("OK");
    private final JButton btnCancel = new JButton("Cancel");
    
    private Runnable refresh = null;
    private Album album;

    public AlbumEditForm(Frame owner) {
        this(owner, new Album());
    }

    public AlbumEditForm(Frame owner, Album album) {
        super(owner, "Edit Album", /* modal*/ true);

        this.album = album;

        setLayout(new MigLayout("wrap 3, w 400", "[][grow, fill][]", "[][][][nogrid]"));

        /* -- Name - */
        add(lblName);
        add(txtName, "span 2");
        txtName.setText(album.getName());

        /* -- Tracks - */
        add(lblTracks);
        add(txtTracks, "span 2");
        if (album.getTracks() != null) {
            txtTracks.setText(album.getTracks().toString());
        }

        /* -- Discs - */
        add(lblDiscs);
        add(txtDiscs, "span 2");
        if (album.getDiscs() != null) {
            txtDiscs.setText(album.getDiscs().toString());
        }

        
        
        /* -- Buttons - */
        add(btnOk, "tag ok");
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnOkActionPerformed(e);
            }
        });

        
        add(btnCancel, "tag cancel");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnCancelActionPerformed(e);
            }
        });

        DialogUtils.addEscapeListener(this);

        pack();
        setLocationRelativeTo(null);
    }

    private void btnOkActionPerformed(ActionEvent e) {
        album.setName(txtName.getText());
        try {
            album.setTracks(Integer.parseInt(txtTracks.getText()));
        } catch (NumberFormatException ex) {
            album.setTracks(null);
        }
        try {
            album.setDiscs(Integer.parseInt(txtDiscs.getText()));
        } catch (NumberFormatException ex) {
            album.setDiscs(null);
        }

        BeanFactory.INSTANCE.albumDao().saveOrUpdate(album);

        if (refresh != null) {
            refresh.run();
        }
        GuiFactory.INSTANCE.mainDashboardForm().refresh();

        setVisible(false);
    }

    private void btnCancelActionPerformed(ActionEvent e) {
        setVisible(false);
    }


    
    
    
    
    void setRefresh(Runnable refresh) {
        this.refresh = refresh;
    }

}
