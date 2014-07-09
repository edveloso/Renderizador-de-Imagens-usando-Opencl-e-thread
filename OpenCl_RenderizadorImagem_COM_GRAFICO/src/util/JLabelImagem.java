/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;

/**
 *
 * @author Lavinia
 */
public class JLabelImagem extends JLabel {

    private Image image;

    public JLabelImagem(BufferedImage imagem) {
        try {
            image = (Image) imagem;
        } catch (Exception e) { /*handled in paintComponent()*/ }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (image != null) {
            graphics.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}
