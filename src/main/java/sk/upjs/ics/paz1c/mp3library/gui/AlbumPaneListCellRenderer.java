package sk.upjs.ics.paz1c.mp3library.gui;

import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import sk.upjs.ics.paz1c.mp3library.Album;

/**
 *
 * @author patrik
 */
class AlbumPaneListCellRenderer implements ListCellRenderer<Album> {

    private int width = 50;
    //private ImageCoverService imageCoverService = BeanFactory.INSTANCE.imageCoverService();
    private DefaultListCellRenderer delegate = new DefaultListCellRenderer();

    private Icon getIcon(Album album) {
        //ImageIcon imageCover = imageCoverService.getImageCover(book);
        BufferedImage image = null;

        /*try {
            SongDao songDao = BeanFactory.INSTANCE.songDao();
            List<Song> song = songDao.findAllByAlbum(album);
            for (Song s : song) {
                System.err.println(s.getFile_path().getAbsoluteFile());
                MP3File f = (MP3File) AudioFileIO.read(s.getFile_path());
                ID3v24Tag tag = f.getID3v2TagAsv24();

                TagField coverArtField
                        = tag.getFirstField(org.jaudiotagger.tag.id3.ID3v23FieldKey.COVER_ART.getFieldName());

                FrameBodyAPIC body = (FrameBodyAPIC) ((ID3v23Frame) coverArtField).getBody();
                byte[] imageRawData = (byte[]) body.getObjectValue(DataTypes.OBJ_PICTURE_DATA);
                image = ImageIO.read(ImageIO.createImageInputStream(new ByteArrayInputStream(imageRawData)));
            }
        } catch (Exception e) {

        }*/
        if (image == null) {
            try {
                image = ImageIO.read(AlbumPaneListCellRenderer.class.getResource("defaultAlbum.png"));
            } catch (Exception ex) {
                return null;
            }
        }
        return new ImageIcon(image.getScaledInstance(width, width, Image.SCALE_DEFAULT));

    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Album> list, Album album, int index, boolean isSelected, boolean cellHasFocus) {
        Component component = delegate.getListCellRendererComponent(list, album, index, isSelected, cellHasFocus);
        if (component instanceof JLabel) {
            JLabel label = (JLabel) component;
            label.setText(album.getName());
            label.setIcon(getIcon(album));
            return label;
        } else {
            throw new ClassCastException("Illegal JList component type. "
                    + "Expected JLabel, but found "
                    + component.getClass());
        }
    }
}
