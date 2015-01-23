package sk.upjs.ics.paz1c.mp3library.gui;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import sk.upjs.ics.paz1c.mp3library.Artist;
import sk.upjs.ics.paz1c.mp3library.BeanFactory;

public class ArtistEditForm extends JDialog {

    private final JLabel lblName = new JLabel("Name:");
    private final JLabel lblWiki = new JLabel("Wiki:");

    private final JTextField txtName = new JTextField();
    private final JTextField txtWiki = new JTextField();

    private final JButton btnOk = new JButton("OK");
    private final JButton btnCancel = new JButton("Cancel");

    private Runnable refresh = null;
    
    private Artist artist;

    public ArtistEditForm(Frame owner) {
        this(owner, new Artist());
    }

    public ArtistEditForm(Frame owner, Artist artist) {
        super(owner, "Edit Artist", /* modal*/ true);

        this.artist = artist;

        setLayout(new MigLayout("wrap 3, w 400", "[][grow, fill][]", "[][][nogrid]"));

        /* -- Name - */
        add(lblName);
        add(txtName, "span 2");
        txtName.setText(artist.getName());

        /* -- Wiki - */
        add(lblWiki);
        add(txtWiki, "span 2");
        if (artist.getWiki() != null) {
            txtWiki.setText(artist.getWiki().toString());
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
        artist.setName(txtName.getText());
        try {
            artist.setWiki(new URL(txtWiki.getText()));
        } catch (MalformedURLException ex) {
            artist.setWiki(null);
        }

        BeanFactory.INSTANCE.artistDao().saveOrUpdate(artist);
        
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
