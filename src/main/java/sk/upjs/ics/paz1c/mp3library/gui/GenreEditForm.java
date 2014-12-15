package sk.upjs.ics.paz1c.mp3library.gui;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import sk.upjs.ics.paz1c.mp3library.BeanFactory;
import sk.upjs.ics.paz1c.mp3library.Genre;

public class GenreEditForm extends JDialog {

    private final JLabel lblName = new JLabel("Name:");

    private final JTextField txtName = new JTextField();

    private final JButton btnOk = new JButton("OK");
    private final JButton btnCancel = new JButton("Cancel");

    private Runnable refresh = null;
    
    private Genre genre;

    public GenreEditForm(Frame owner) {
        this(owner, new Genre());
    }

    public GenreEditForm(Frame owner, Genre genre) {
        super(owner, "Edit Genre", /* modal*/ true);

        this.genre = genre;

        setLayout(new MigLayout("wrap 3, w 400", "[][grow, fill][]", "[][nogrid]"));

        /* -- Name - */
        add(lblName);
        add(txtName, "span 2");
        txtName.setText(genre.getName());

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
        genre.setName(txtName.getText());

        BeanFactory.INSTANCE.genreDao().saveOrUpdate(genre);
        
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
