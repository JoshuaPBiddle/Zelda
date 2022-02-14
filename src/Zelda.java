import java.util.Vector;
import java.util.Random;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import javax.swing.JComboBox;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Zelda {
    public Zelda() {

    }

   public static void setup() {
        appFrame = new JFrame("The Legend of Zelda: Link's Awakening");
        XOFFSET = 0;
        YOFFSET = 40;
        WINWIDTH = 500;
        WINHEIGHT = 500;
       pi = 3.14159265358979;
        quarterPi = 0.25 * pi;
        halfPi = 0.5 * pi;
        threeQuartersPi = 0.75 * pi;
        fiveQuartersPi = 1.25 * pi;
        threeHalvesPi = 1.5 * pi;
        sevenQuartersPi = 1.75 * pi;
       twoPi = 2.0 * pi;
       endgame = false;
       p1width = 20;
       p1height = 20;
       p1originalX = (double) XOFFSET + ((double) WINWIDTH / 2.0) - (p1width / 2.0);
       p1originalY = (double) YOFFSET + ((double) WINWIDTH / 2.0) - (p1width / 2.0);
        level = 3;
        audioLifeTime = new Long(78000);
        dropLifeLifeTime = new Long(1000);





   }
}
