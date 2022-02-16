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

        try {
            xdimKI = 16;
            ydimKI = 16;
            backgroundKI = new Vector<Vector<BufferedImage>>();

            for (int i = 0; i < ydimKI; i++) {
                Vector<BufferedImage> temp = new Vector<BufferedImage>();
                for (int j = 0; j < xkdimKI; j++) {
                    BufferedImage tempImg = ImageIO.read(new File("blank.png"));
                    temp.addElement(tempImg);
                }
                backgroundKI.addElement(temp);
            }

            for (int i = 0; i < backroundKI.size(); i++) {
                for (int j = 0; j < backgroundKI.elementAt(i).size(); j++) {
                    if ((j==5 && i == 10) || (j==5 && i==11) || (j==6 && i==10) ||
                             (j==6 &&i==11) || (j==7 && i==10) ||
                            (j==7 && i==10) || (j==7 && i==11) || (j==8 && i==9) ||
                            (j==8 && i==10)) {
                        String fileName = "KI";
                        if (j < 10) {
                            fileName = fileName + "0";
                        }
                        fileName = fileName + j;
                        if (i < 10) {
                            fileName = fileName + "0";
                        }
                        fileName = fileName + i + ".png";
                        backgroundKI.elementAt(i).set(j, ImageIO.read(new File(fileName)));
                    }
                }
            }

            wallsKI = new Vector<Vector<Vector<ImageObject>>>();
            for(int i = 0; i < ydimKI; i++) {
                Vector<Vector<ImageObject>> temp = new Vector<Vector<ImageObject>>();
                for (int j = 0; j < xdimKI; j++) {
                    Vector<ImageObject> tempWalls = new Vector<ImageObject>();
                    temp.addElement(tempWalls);
                }
                wallsKI.add(temp);
            }
            for (int i = 0; i < wallsKI.size(); i++) {
                for (int j = 0; j < wallsKI.elementAt(i).size(); j++) {
                    if (i == 5 && j == 10) {
                        wallsKI.elementAt(i).elementAt(j).addElement(
                                new ImageObject(270, 35, 68, 70, 0.0));
                        wallsKI.elementAt(i).elementAt(j).addElement(
                                new ImageObject(100, 100, 200, 35, 0.0));
                        wallsKI.elementAt(i).elementAt(j).addElement(
                                new ImageObject(100, 135, 35, 35, 0.0));
                        wallsKI.elementAt(i).elementAt(j).addElement(
                                new ImageObject(0, 165, 35, 135, 0.0));
                        wallsKI.elementAt(i).elementAt(j).addElement(
                                new ImageObject(100, 200, 35, 100, 0.0));
                        wallsKI.elementAt(i).elementAt(j).addElement(
                                new ImageObject(135, 270, 200, 35, 0.0));
                    }
                    if (i==8 && j==9) {
                        wallsKI.elementAt(i).elementAt(j).addElement(
                                new ImageObject(0, 35, 135, 35, 0.0));
                        wallsKI.elementAt(i).elementAt(j).addElement(
                                new ImageObject(100, 70, 35, 140, 0.0));
                        wallsKI.elementAt(i).elementAt(j).addElement(
                                new ImageObject(35, 135, 35, 100, 0.0));
                        wallsKI.elementAt(i).elementAt(j).addElement(
                                new ImageObject(0, 170, 35, 70, 0.0));
                        wallsKI.elementAt(i).elementAt(j).addElement(
                                new ImageObject(0, 235, 35, 70, 0.0));
                        wallsKI.elementAt(i).elementAt(j).addElement(
                                new ImageObject(0, 270, 135, 35, 0.0));
                        wallsKI.elementAt(i).elementAt(j).addElement(
                                new ImageObject(170, 270, 135, 35, 0.0));
                        wallsKI.elementAt(i).elementAt(j).addElement(
                                new ImageObject(300, 35, 35, 270, 0.0));
                        wallsKI.elementAt(i).elementAt(j).addElement(
                                new ImageObject(235, 35, 70, 35, 0.0));
                    }
                }
            }

            xdimTC = 9;
            ydimTC = 8;
            backgroundTC = new Vector<Vector<BufferedImage>>();

            for (int i = 0; i < ydimTC; i++) {
                Vector<BufferedImage> temp = new Vector<BufferedImage>();
                for (int j = 0; j < xdimTC; j++) {
                    BufferedImage tempImg = ImageIO.read(new File("blank.png"));
                    temp.addElement(tempImg);
                }
                backgroundTC.addElement(temp);
            }

            for(int i = 0; i < backgroundTC.size(); i++) {
                for (int j = 0; j < backgroundTC.elementAt(i).size(); j++) {
                    if ((j==0 && i==2) || (j==0 && i==3) ||
                            (j==0 && i==4) || (j==1 && i==1) || (j==1 && i==3) ||(j==1 && i==5) ||
                            (j==2 && i==1) ||(j==2 && i==2) ||
                            (j==2 && i==3) || (j==2 && i==4) ||
                            (j==2 && i==5) || (j==2 && i==6) ||
                            (j==3 && i==1) || (j==3 && i==2) ||
                            (j==3 && i==3) || (j==3 && i==4) ||
                            (j==3 && i==5) || (j==4 && i==2) ||
                            (j==4 && i==3) || (j==4 && i==4) ||
                            (j==5 && i==2) ||
                            (j==5 && i==3) || (j==6 && i==0) ||
                            (j==6 && i==1) || (j==6 && i==2) ||
                            (j==6 && i==3)) {
                        String fileName = "TC";
                        if(j < 10) {
                            fileName = fileName + "0";
                        }
                        fileName = fileName + j;
                        if (i < 10) {
                            fileName = fileName + "0";
                        }
                        fileName = fileName + i + ".png";
                        backgroundTC.elementAt(i).set(j, ImageIO.read(new File(fileName)));
                    }
                }
            }

            wallsTC = new Vector<Vector<Vector<ImageObject>>>();
            for (int i = 0; i < ydimTC; i++) {
                Vector<Vector<ImageObject>> temp = new Vector<Vector<ImageObject>>();
                for (int j = 0; j < xdimTC; j++) {
                    Vector<ImageObject> tempWalls = new Vector <ImageObject>();
                    temp.addElement(tempWalls);
                }
                wallsTC.add(temp);
            }

            player = ImageIO.read(new File("link00.png"));

            link = new Vector<BufferedImage>();
            for (int i = 0; i < 72; i++) {
                if (i < 10) {
                    String fileName = "link0" + i + ".png";
                    link.addElement(ImageIO.read(new File(fileName)));
                }
                else {
                    String fileName = "link" + i + ".png";
                    link.addElement(ImageIO.read(new File(fileName)));
                }
            }

            bluePigEnemies = new Vector<ImageObject>();
            bluePigEnemy = new Vector<BufferedImage>();
            bluePigEnemy.addElement(ImageIO.read(new File("BPB1.png")));
            bluePigEnemey.addElement(ImageIO.read(new File("BPB2.png")));
            bluePigEnemy.addElement(ImageIO.read(new File("BPF1.png")));
            bluePigEnemy.addElement(ImageIO.read(new File("BPF2.png")));
            bluePigEnemy.addElement(ImageIO.read(new File("BPL1.png")));
            bluePigEnemy.addElement(ImageIO.read(new File("BPL2.png")));
            bluePigEnemy.addElement(ImageIO.read(new File("BPR1.png")));
            bluePigEnemy.addElement(ImageIO.read(new File("BPR2.png")));

            bubbleBossEnemies = new Vector<ImageObject>();
            leftHeartOutline = ImageIO.read(new File("heartOutlineLeft.png"));
            rightHeartOutline = ImageIO.read(new File("heartOutlineRight.png"));
            leftHeart = ImageIO.read(new File ("heartLeft.png"));
            rightHeart = ImageIO.read(new File("heartRight.png"));
        }
        catch(IOException ioe) {

        }
   }
}

private static class Animate implements Runnable {
    public void run() {
        while(endgame == false) {
            backgroundDraw();
            enemiesDraw();
            playerDraw();
            healthDraw();

            try {
                Thread.sleep(32);
            }
            catch (InterruptedException e) {

            }
        }
    }
}

private static class AudioLooper implements Runnable {
    try {
        clip.stop();
    }
    catch (Exception e) {

    }
}
