/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.paz1c.mp3library.gui.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;

public class ImageJButton extends JButton {

    BufferedImage image;
    private Color hoverBackgroundColor;
    private Color pressedBackgroundColor;

    public ImageJButton(String imageString) {
        try {
            image = ImageIO.read(ImageJButton.class.getResource(imageString));
        } catch (IOException | java.lang.IllegalArgumentException e) {
            System.err.println("Nepodarilo sa načítať obrázok " + imageString);
        }
        setUnchecked();
    }

    public void setChecked() {
        setBackground(new Color(21, 57, 140));
        hoverBackgroundColor = new Color(21, 57, 140);
    }

    public void setUnchecked() {
        setBackground(new Color(18, 38, 83));
        hoverBackgroundColor = new Color(41, 55, 87);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image == null) {
            return;
        }
        if (getModel().isPressed()) {
            g.setColor(pressedBackgroundColor);
        } else if (getModel().isRollover()) {
            g.setColor(hoverBackgroundColor);
        } else {
            g.setColor(getBackground());
        }
        g.fillRect(0, 0, getWidth(), getHeight());

        int padding = 15;
        int width = Math.min(getWidth(), getHeight()) - padding * 2;
        int x = (getWidth() - width) / 2;
        int y = (getHeight() - width) / 2;

        g.drawImage(image.getScaledInstance(width, width, Image.SCALE_SMOOTH), x, y, null);
    }
}
