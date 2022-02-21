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
        audioLifeTime = 78000L;
        dropLifeTime = 1000L;

        try {
            xdimKI = 16;
            ydimKI = 16;
            backgroundKI = new Vector<Vector<BufferedImage>>();

            for (int i = 0; i < ydimKI; i++) {
                Vector<BufferedImage> temp = new Vector<BufferedImage>();
                for (int j = 0; j < xdimKI; j++) {
                    BufferedImage tempImg = ImageIO.read(new File("intro1.png"));
                    temp.addElement(tempImg);
                }
                backgroundKI.addElement(temp);
            }

            for (int i = 0; i < backgroundKI.size(); i++) {
                for (int j = 0; j < backgroundKI.elementAt(i).size(); j++) {
                    if ((j == 5 && i == 10) || (j == 5 && i == 11) || (j == 6 && i == 10) ||
                            (j == 6 && i == 11) || (j == 7 && i == 10) ||
                            (j == 7 && i == 10) || (j == 7 && i == 11) || (j == 8 && i == 9) ||
                            (j == 8 && i == 10)) {
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
            for (int i = 0; i < ydimKI; i++) {
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
                    if (i == 8 && j == 9) {
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

            for (int i = 0; i < backgroundTC.size(); i++) {
                for (int j = 0; j < backgroundTC.elementAt(i).size(); j++) {
                    if ((j == 0 && i == 2) || (j == 0 && i == 3) ||
                            (j == 0 && i == 4) || (j == 1 && i == 1) || (j == 1 && i == 3) || (j == 1 && i == 5) ||
                            (j == 2 && i == 1) || (j == 2 && i == 2) ||
                            (j == 2 && i == 3) || (j == 2 && i == 4) ||
                            (j == 2 && i == 5) || (j == 2 && i == 6) ||
                            (j == 3 && i == 1) || (j == 3 && i == 2) ||
                            (j == 3 && i == 3) || (j == 3 && i == 4) ||
                            (j == 3 && i == 5) || (j == 4 && i == 2) ||
                            (j == 4 && i == 3) || (j == 4 && i == 4) ||
                            (j == 5 && i == 2) ||
                            (j == 5 && i == 3) || (j == 6 && i == 0) ||
                            (j == 6 && i == 1) || (j == 6 && i == 2) ||
                            (j == 6 && i == 3)) {
                        String fileName = "TC";
                        if (j < 10) {
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
                    Vector<ImageObject> tempWalls = new Vector<ImageObject>();
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
                } else {
                    String fileName = "link" + i + ".png";
                    link.addElement(ImageIO.read(new File(fileName)));
                }
            }

            bluePigEnemies = new Vector<ImageObject>();
            bluePigEnemy = new Vector<BufferedImage>();
            bluePigEnemy.addElement(ImageIO.read(new File("BPB1.png")));
            bluePigEnemy.addElement(ImageIO.read(new File("BPB2.png")));
            bluePigEnemy.addElement(ImageIO.read(new File("BPF1.png")));
            bluePigEnemy.addElement(ImageIO.read(new File("BPF2.png")));
            bluePigEnemy.addElement(ImageIO.read(new File("BPL1.png")));
            bluePigEnemy.addElement(ImageIO.read(new File("BPL2.png")));
            bluePigEnemy.addElement(ImageIO.read(new File("BPR1.png")));
            bluePigEnemy.addElement(ImageIO.read(new File("BPR2.png")));

            bubbleBossEnemies = new Vector<ImageObject>();
            leftHeartOutline = ImageIO.read(new File("heartOutlineLeft.png"));
            rightHeartOutline = ImageIO.read(new File("heartOutlineRight.png"));
            leftHeart = ImageIO.read(new File("heartLeft.png"));
            rightHeart = ImageIO.read(new File("heartRight.png"));
        } catch (IOException ioe) {

        }
    }

    private static class Animate implements Runnable {
        public void run() {
            while (endgame == false) {
                backgroundDraw();
                enemiesDraw();
                playerDraw();
                healthDraw();

                try {
                    Thread.sleep(32);
                } catch (InterruptedException e) {

                }
            }
        }
    }

    private static class AudioLooper implements Runnable {
        public void run() {
            while (endgame == false) {
                Long curTime = System.currentTimeMillis();
                if (curTime - lastAudioStart > audioLifeTime) {
                    playAudio(backgroundState);
                }
            }
        }
    }

    private static void playAudio(String backgroundState) {
        try {
            clip.stop();
        } catch (Exception e) {

        }
        try {
            if (backgroundState.substring(0, 2).equals("KI")) {
                AudioInputStream ais = AudioSystem.getAudioInputStream(new File("KI.wav").getAbsoluteFile());
                clip = AudioSystem.getClip();
                clip.open(ais);
                clip.start();
                lastAudioStart = System.currentTimeMillis();
                audioLifeTime = 78000L;
            } else if (backgroundState.substring(0, 2).equals("TC")) {
                AudioInputStream ais = AudioSystem.getAudioInputStream(new File("TC.wav").getAbsoluteFile());
                clip = AudioSystem.getClip();
                clip.open(ais);
                clip.start();
                lastAudioStart = System.currentTimeMillis();
                audioLifeTime = 191000L;
            }
        } catch (Exception e) {

        }
    }

    private static String bgWrap(String input, int wrap) {
        String ret = input;
        if (wrap == 0) {

        } else if (wrap == 1) {
            int xcoord = Integer.parseInt(input.substring(2, 4));
            int ycoord = Integer.parseInt(input.substring(4, 6));

            xcoord = xcoord + 1;

            if (xcoord < 10) {
                ret = input.substring(0, 2) + "0" + xcoord;
            } else {
                ret = input.substring(0, 2) + "0" + xcoord;
            }
            if (ycoord < 10) {
                ret = ret + "0" + ycoord;
            } else {
                ret = ret + ycoord;
            }
        } else if (wrap == 2) {
            int xcoord = Integer.parseInt(input.substring(2, 4));
            int ycoord = Integer.parseInt(input.substring(4, 6));

            xcoord = xcoord - 1;

            if (xcoord < 10) {
                ret = input.substring(0, 2) + "0" + xcoord;
            } else {
                ret = input.substring(0, 2) + xcoord;
            }
            if (ycoord < 10) {
                ret = ret + "0" + ycoord;
            } else {
                ret = ret + ycoord;
            }
        } else if (wrap == 3) {
            int xcoord = Integer.parseInt(input.substring(2, 4));
            int ycoord = Integer.parseInt(input.substring(4, 6));

            ycoord = ycoord + 1;
            if (xcoord < 10) {
                ret = input.substring(0, 2) + "0" + xcoord;
            } else {
                ret = input.substring(0, 2) + xcoord;
            }
            if (ycoord < 10) {
                ret = ret + "0" + ycoord;
            } else {
                ret = ret + ycoord;
            }
        } else if (wrap == 4) {
            int xcoord = Integer.parseInt(input.substring(2, 4));
            int ycoord = Integer.parseInt(input.substring(4, 6));

            ycoord = ycoord - 1;
            if (xcoord < 10) {
                ret = input.substring(0, 2) + "0" + xcoord;
            } else {
                ret = input.substring(0, 2) + xcoord;
            }
            if (ycoord < 10) {
                ret = ret + "0" + ycoord;
            } else {
                ret = ret + ycoord;
            }
        }
        return ret;
    }

    private static class PlayerMover implements Runnable {
        public PlayerMover() {
            velocityStep = 3;
        }

        public void run() {
            while (endgame == false) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {

                }
                if (upPressed || downPressed || leftPressed || rightPressed) {
                    p1Velocity = velocityStep;
                    if (upPressed) {
                        if (leftPressed) {
                            p1.setInternalAngle(fiveQuartersPi);
                        } else if (rightPressed) {
                            p1.setInternalAngle(5.49779);
                        } else {
                            p1.setInternalAngle(threeHalvesPi);
                        }
                    }
                    if (downPressed) {
                        if (leftPressed) {
                            p1.setInternalAngle(2.35619);
                        } else if (rightPressed) {
                            p1.setInternalAngle(quarterPi);
                        } else {
                            p1.setInternalAngle(halfPi);
                        }
                    }
                    if (leftPressed) {
                        if (upPressed) {
                            p1.setInternalAngle(fiveQuartersPi);
                        } else if (downPressed) {
                            p1.setInternalAngle(threeQuartersPi);
                        } else {
                            p1.setInternalAngle(pi);
                        }
                    }
                    if (rightPressed) {
                        if (upPressed) {
                            p1.setInternalAngle(5.49779);
                        } else if (downPressed) {
                            p1.setInternalAngle(quarterPi);
                        } else {
                            p1.setInternalAngle(0.0);
                        }
                    }
                } else {
                    p1Velocity = 0.0;
                    p1.setInternalAngle(threeHalvesPi);
                }

                p1.updateBounce();
                p1.move(p1Velocity * Math.cos(p1.getInternalAngle()), p1Velocity * Math.sin(p1.getInternalAngle()));
                int wrap = p1.screenWrap(XOFFSET, XOFFSET + WINWIDTH, YOFFSET, YOFFSET + WINHEIGHT);
                backgroundState = bgWrap(backgroundState, wrap);
                if (wrap != 0) {
                    clearEnemies();
                    generateEnemies(backgroundState);
                }
            }
        }

        private double velocityStep;
    }

    private static void clearEnemies() {
        bluePigEnemies.clear();
        bubbleBossEnemies.clear();
    }

    private static void generateEnemies(String backgroundState) {
        if (backgroundState.substring(0, 6).equals("KI0809")) {
            bluePigEnemies.addElement(new ImageObject(20, 90, 33, 33, 0.0));
            bluePigEnemies.addElement(new ImageObject(250, 230, 33, 33, 0.0));
        }
        for (int i = 0; i < bluePigEnemies.size(); i++) {
            bluePigEnemies.elementAt(i).setMaxFrames(25);
        }
    }

    private static class EnemyMover implements Runnable {
        public EnemyMover() {
            bluePigVelocityStep = 2;
        }

        public void run() {
            Random randomNumbers = new Random(LocalTime.now().getNano());
            while (endgame == false) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {

                }

                try {
                    for (int i = 0; i < bluePigEnemies.size(); i++) {
                        int state = randomNumbers.nextInt(1000);
                        if (state < 5) {
                            bluePigVelocity = bluePigVelocityStep;
                            bluePigEnemies.elementAt(i).setInternalAngle(0);
                        } else if (state < 10) {
                            bluePigVelocity = bluePigVelocityStep;
                            bluePigEnemies.elementAt(i).setInternalAngle(halfPi);
                        } else if (state < 15) {
                            bluePigVelocity = bluePigVelocityStep;
                            bluePigEnemies.elementAt(i).setInternalAngle(pi);
                        } else if (state < 20) {
                            bluePigVelocity = bluePigVelocityStep;
                            bluePigEnemies.elementAt(i).setInternalAngle(threeHalvesPi);
                        } else if (state < 250) {
                            bluePigVelocity = bluePigVelocityStep;
                        } else {
                            bluePigVelocity = 0;
                        }
                        bluePigEnemies.elementAt(i).updateBounce();
                        bluePigEnemies.elementAt(i).move(bluePigVelocity *
                                Math.cos(bluePigEnemies.elementAt(i).getInternalAngle()), bluePigVelocity *
                                Math.sin(bluePigEnemies.elementAt(i).getInternalAngle()));
                    }
                    for (int i = 0; i < bubbleBossEnemies.size(); i++) {

                    }
                } catch (java.lang.NullPointerException jlnpe) {
                    // NOP;
                }
            }
        }

        private double bluePigVelocityStep;
        private double bluePigVelocity;
    }

    private static class HealthTracker implements Runnable {
        @Override
        public void run() {
            while (endgame == false) {
                Long curTime = System.currentTimeMillis();
                if (availableToDropLife && p1.getDropLife() > 0) {
                    int newLife = p1.getLife() - p1.getDropLife();
                    p1.setDropLife(0);
                    availableToDropLife = false;

                    lastDropLife = System.currentTimeMillis();
                    p1.setLife(newLife);

                    try {
                        AudioInputStream ais = AudioSystem.getAudioInputStream(new File("hurt.wav").getAbsoluteFile());
                        Clip hurtclip = AudioSystem.getClip();
                        hurtclip.open(ais);
                        hurtclip.start();
                    } catch (Exception e) {

                    }
                } else {
                    if (curTime - lastDropLife > dropLifeTime) {
                        availableToDropLife = true;
                    }
                }
            }
        }
    }

    private static class CollisionChecker implements Runnable {
        @Override
        public void run() {
            //Random randomNumbers = new Random(LocalTime.now().getNano());
            while (endgame == false) {
                // check player against doors in given scenes
                if (backgroundState.substring(0, 6).equals("KIO511")) {
                    if (collisionOccurs(p1, doorKItoTC)) {
                        p1.moveto(p1originalX, p1originalY);
                        backgroundState = "TC0305";
                        clip.stop();
                        playAudio(backgroundState);
                    }
                } else if (backgroundState.substring(0, 6).equals("TC0305")) {
                    if (collisionOccurs(p1,doorTCtoKI)) {
                        p1.moveto(p1originalX, p1originalY);
                        backgroundState = "KI0511";
                        clip.stop();
                        playAudio(backgroundState);
                    }
                }

                // check player and enemies against walls
                if (backgroundState.substring(0, 6).equals("KI0510")) {
                    checkMoversAgainstWalls(wallsKI.elementAt(5).elementAt(10));
                }
                if (backgroundState.substring(0, 6).equals("KI0809")) {
                    checkMoversAgainstWalls(wallsKI.elementAt(8).elementAt(9));
                }

                // check player against enemies
                for (int i = 0; i < bluePigEnemies.size(); i++) {
                    if (collisionOccurs(p1, bluePigEnemies.elementAt(i))) {
                        //System.out.println("Still Colliding: " + i + ", " + System.currentTimeMillis());
                        p1.setBounce(true);
                        bluePigEnemies.elementAt(i).setBounce(true);
                        if (availableToDropLife) {
                            p1.setDropLife(1);
                        }
                    }
                }

                // TODO: check enemies against walls

                // TODO: check player against deep water or pits

                // TODO: check player against enemy arrows

                // TODO: check enemies against player weapons

            }
        }

        private static void checkMoversAgainstWalls(Vector<ImageObject> wallsInput) {
            for (int i = 0; i < wallsInput.size(); i++) {
                if (collisionOccurs(p1, wallsInput.elementAt(i))) {
                    p1.setBounce(true);
                }
                for (int j = 0; j < bluePigEnemies.size(); j++) {
                    if (collisionOccurs(bluePigEnemies.elementAt(j), wallsInput.elementAt(i))) {
                        bluePigEnemies.elementAt(j).setBounce(true);
                    }
                }
            }
        }
    }

    // TODO make one lockrotate function, see pg. 126 for specifics

    private static void lockRotateObjAroundObjBottom(ImageObject objOuter, ImageObject objInner, double dist) {
        objOuter.moveto(objInner.getX() + (dist + objInner.getWidth() / 2.0) * Math.cos(-objInner.getAngle() + pi / 2.0)
                + objOuter.getWidth() / 2.0, objInner.getY() + (dist + objInner.getHeight() / 2.0)
                * Math.sin(-objInner.getAngle() + pi / 2.0) + objOuter.getHeight() / 2.0);
        objOuter.setAngle(objInner.getAngle());
    }

    // dist is a distance between the two objects at the top of the inner object.
    private static void lockRotateObjAboutObjTop(ImageObject objOuter, ImageObject objInner, double dist) {
        objOuter.moveto(objInner.getX() + objOuter.getWidth() + (objInner.getWidth() / 2.0 +
                        (dist + objInner.getWidth() / 2.0) * Math.cos(objInner.getAngle() + pi / 2.0)) / 2.0,
                objInner.getY() - objOuter.getHeight() + (dist + objInner.getHeight() / 2.0) *
                        Math.sin(objInner.getAngle() / 2.0));
        objOuter.setAngle(objInner.getAngle());
    }

    private static AffineTransformOp rotateImageObject(ImageObject obj) {
        AffineTransform at = AffineTransform.getRotateInstance(-obj.getInternalAngle(),
                obj.getWidth() / 2.0, obj.getHeight() / 2.0);
        AffineTransformOp atop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        return atop;
    }

    private static AffineTransformOp spinImageObject(ImageObject obj) {
        AffineTransform at = AffineTransform.getRotateInstance(-obj.getInternalAngle(),
                obj.getWidth() / 2.0, obj.getHeight(), 2.0);
        AffineTransformOp atop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        return atop;
    }

    private static void backgroundDraw() {
        Graphics g = appFrame.getGraphics();
        Graphics2D g2D = (Graphics2D) g;

        if (backgroundState.substring(0, 2).equals("KI")) {
            int i = Integer.parseInt(backgroundState.substring(4, 6));
            int j = Integer.parseInt(backgroundState.substring(2, 4));
            if (i < backgroundKI.size()) {
                if (j < backgroundKI.elementAt(i).size()) {
                    g2D.drawImage(backgroundKI.elementAt(i).elementAt(j), XOFFSET, YOFFSET, null);
                }
            }
        }

        if (backgroundState.substring(0, 2).equals("TC")) {
            int i = Integer.parseInt(backgroundState.substring(4, 6));
            int j = Integer.parseInt(backgroundState.substring(2, 4));
            if (i < backgroundKI.size()) {
                if (j < backgroundTC.elementAt(i).size()) {
                    g2D.drawImage(backgroundTC.elementAt(i).elementAt(j), XOFFSET, YOFFSET, null);
                }
            }
        }
    }

    private static void playerDraw() {
        Graphics g = appFrame.getGraphics();
        Graphics g2D = (Graphics2D) g;

        if (upPressed || downPressed || leftPressed || rightPressed) {
            if (upPressed) {
                if (p1.getCurrentFrame() == 0) {
                    g2D.drawImage(rotateImageObject(p1).filter(link.elementAt(4), null), (int) (p1.getX() + 0.5),
                            (int) (p1.getY() + 0.5), null);
                } else if (p1.getCurrentFrame() == 1) {
                    g2D.drawImage(rotateImageObject(p1).filter(link.elementAt(5), null), (int) (p1.getX() + 0.5),
                            (int) (p1.getY() + 0.5), null);
                }
                p1.updateCurrentFrame();
            }

            if (downPressed) {
                if (p1.getCurrentFrame() == 0) {
                    g2D.drawImage(rotateImageObject(p1).filter(link.elementAt(2), null), (int) (p1.getX() + 0.5), (int) (p1.getY() + 0.5), null);
                } else if (p1.getCurrentFrame() == 1) {
                    g2D.drawImage(rotateImageObject(p1).filter(link.elementAt(5), null), (int) (p1.getX() + 0.5),
                            (int) (p1.getY() + 0.5), null);
                }
                p1.updateCurrentFrame();
            }

            if (leftPressed) {
                if (p1.getCurrentFrame() == 0) {
                    g2D.drawImage(rotateImageObject(p1).filter(link.elementAt(0), null), (int) (p1.getX() + 0.5), (int) (p1.getY() + 0.5), null);
                } else if (p1.getCurrentFrame() == 1) {
                    g2D.drawImage(rotateImageObject(p1).filter(link.elementAt(1), null), (int) (p1.getX() + 0.5),
                            (int) (p1.getY() + 0.5), null);
                }
                p1.updateCurrentFrame();
            }

            if (rightPressed) {
                if (p1.getCurrentFrame() == 0) {
                    g2D.drawImage(rotateImageObject(p1).filter(link.elementAt(6), null), (int) (p1.getX() + 0.5), (int) (p1.getY() + 0.5), null);
                } else if (p1.getCurrentFrame() == 1) {
                    g2D.drawImage(rotateImageObject(p1).filter(link.elementAt(7), null), (int) (p1.getX() + 0.5),
                            (int) (p1.getY() + 0.5), null);
                }
                p1.updateCurrentFrame();
            }
        } else {
            if (Math.abs(lastPressed - 90.0) < 1.0) {
                g2D.drawImage(rotateImageObject(p1).filter(link.elementAt(4), null), (int) (p1.getX() + 0.5),
                        (int) (p1.getY() + 0.5), null);
            }
            if (Math.abs(lastPressed - 270.0) < 1.0) {
                g2D.drawImage(rotateImageObject(p1).filter(link.elementAt(2), null), (int) (p1.getX() + 0.5),
                        (int) (p1.getY() + 0.5), null);
            }
            if (Math.abs(lastPressed - 0.0) < 1.0) {
                g2D.drawImage(rotateImageObject(p1).filter(link.elementAt(6), null), (int) (p1.getX() + 0.5),
                        (int) (p1.getY() + 0.5), null);
            }
            if (Math.abs(lastPressed - 1800.0) < 1.0) {
                g2D.drawImage(rotateImageObject(p1).filter(link.elementAt(0), null), (int) (p1.getX() + 0.5),
                        (int) (p1.getY() + 0.5), null);
            }
        }

        //g2D.drawImage(rotateImageObject(p1).filter(player, null), (int)(p1.getX() + 0.5),
        //                        (int)(p1.getY() + 0.5), null);
    }

    private static void healthDraw() {
        Graphics g = appFrame.getGraphics();
        Graphics2D g2D = (Graphics2D) g;

        int leftScale = 10;
        int leftOffset = 10;
        int rightOffset = 9;
        int interiorOffset = 2;
        int halfInteriorOffset = 1;

        for (int i = 0; i < p1.getMaxLife(); i++) {
            if (i % 2 == 0) {
                g2D.drawImage(rotateImageObject(p1).filter(leftHeartOutline, null),
                        leftScale * i + leftOffset + XOFFSET, YOFFSET, null);
            } else {
                g2D.drawImage(rotateImageObject(p1).filter(rightHeartOutline, null),
                        leftScale * i + rightOffset + XOFFSET, YOFFSET, null);
            }
        }

        for (int i = 0; i < p1.getLife(); i++) {
            if (i % 2 == 0) {
                g2D.drawImage(rotateImageObject(p1).filter(leftHeart, null),
                        leftScale * i + leftOffset + interiorOffset + XOFFSET, interiorOffset + YOFFSET, null);
            } else {
                g2D.drawImage(rotateImageObject(p1).filter(rightHeart, null),
                        leftScale * i + rightOffset - halfInteriorOffset + XOFFSET, interiorOffset + YOFFSET, null);
            }
        }
    }

    private static void enemiesDraw() {
        Graphics g = appFrame.getGraphics();
        Graphics2D g2D = (Graphics2D) g;

        for (int i = 0; i < bluePigEnemies.size(); i++) {
            enemiesDrawHelper(g2D, i, 0.0, 6, 7);
            enemiesDrawHelper(g2D, i, pi, 4, 5);
            enemiesDrawHelper(g2D, i, halfPi, 2, 3);
            enemiesDrawHelper(g2D, i, threeHalvesPi, 0, 1);
        }
    }

    private static void enemiesDrawHelper(Graphics2D g2D, int i, double internalAngleSubtract, int imageIndex1,
                                          int imageIndex2) {
        if (Math.abs(bluePigEnemies.elementAt(i).getInternalAngle() - internalAngleSubtract) < 1.0) {
            if (bluePigEnemies.elementAt(i).getCurrentFrame() < bluePigEnemies.elementAt(i).getMaxFrames() / 2) {
                g2D.drawImage(rotateImageObject(bluePigEnemies.elementAt(i)).filter(bluePigEnemy.elementAt(imageIndex1),
                                null), (int) (bluePigEnemies.elementAt(i).getX() + 0.5),
                        (int) (bluePigEnemies.elementAt(i).getY() + 0.5), null);
            } else {
                g2D.drawImage(rotateImageObject(bluePigEnemies.elementAt(i)).filter(bluePigEnemy.elementAt(imageIndex2),
                                null), (int) (bluePigEnemies.elementAt(i).getX() + 0.5),
                        (int) (bluePigEnemies.elementAt(i).getY() + 0.5), null);
            }
            bluePigEnemies.elementAt(i).updateCurrentFrame();
        }
    }

    private static class KeyPressed extends AbstractAction {
        public KeyPressed() {
            action = "";
        }

        public KeyPressed(String input) {
            action = input;
        }

        public void actionPerformed(ActionEvent e) {
            if (action.equals("UP")) {
                upPressed = true;
                lastPressed = 90.0;
            }
            if (action.equals("DOWN")) {
                downPressed = true;
                lastPressed = 270.0;
            }
            if (action.equals("LEFT")) {
                leftPressed = true;
                lastPressed = 180.0;
            }
            if (action.equals("RIGHT")) {
                rightPressed = true;
                lastPressed = 0.0;
            }
            if (action.equals("A")) {
                aPressed = true;
            }
            if (action.equals("X")) {
                xPressed = true;
            }
        }

        private String action;
    }

    public static class KeyReleased extends AbstractAction {
        public KeyReleased() {
            action = "";
        }

        public KeyReleased(String input) {
            action = input;
        }

        public void actionPerformed(ActionEvent e) {
            if (action.equals("UP")) {
                upPressed = false;
            }
            if (action.equals("DOWN")) {
                downPressed = false;
            }
            if (action.equals("LEFT")) {
                leftPressed = false;
            }
            if (action.equals("RIGHT")) {
                rightPressed = false;
            }
            if (action.equals("A")) {
                aPressed = false;
            }
            if (action.equals("X")) {
                xPressed = false;
            }
        }

        private String action;
    }

    private static class QuitGame implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            endgame = true;
        }
    }

    private static class StartGame implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            endgame = true;
            upPressed = false;
            downPressed = false;
            leftPressed = false;
            rightPressed = false;
            aPressed = false;
            xPressed = false;
            lastPressed = 90.0;
            backgroundState = "KI0809";
            availableToDropLife = true;
            try {
                clearEnemies();
                generateEnemies(backgroundState);
            } catch (java.lang.NullPointerException jlnpe) {
                System.out.println("Error: " + jlnpe);
            }
            p1 = new ImageObject(p1originalX, p1originalY, p1width, p1height, 0.0);
            p1Velocity = 0.0;
            p1.setInternalAngle(threeHalvesPi);     // 270 degrees, in radians
            p1.setMaxFrames(2);
            p1.setlastposx(p1originalX);
            p1.setlastposy(p1originalY);
            p1.setLife(6);
            p1.setMaxLife(6);
            doorKItoTC = new ImageObject(200, 55, 35, 35, 0.0);
            doorTCtoKI = new ImageObject(150, 270, 35, 35, 0.0);
            try {
                Thread.sleep(50);
            } catch (InterruptedException ie) {
                System.out.println("Interruption: " + ie);
            }
            lastAudioStart = System.currentTimeMillis();
            playAudio(backgroundState);
            endgame = false;
            lastDropLife = System.currentTimeMillis();
            Thread t1 = new Thread(new Animate());
            Thread t2 = new Thread(new PlayerMover());
            Thread t3 = new Thread(new CollisionChecker());
            Thread t4 = new Thread(new AudioLooper());
            Thread t5 = new Thread(new EnemyMover());
            Thread t6 = new Thread(new HealthTracker());
            t1.start();
            t2.start();
            t3.start();
            t4.start();
            t5.start();
            t6.start();
        }
    }
    private static class GameLevel implements ActionListener
    {
        public int decodeLevel(String input)
        {
            int ret = 3;
            if(input.equals("One"))
            {
                ret = 1;
            }
            else if (input.equals("Two"))
            {
                ret = 2;
            }
            else if(input.equals("Three"))
            {
                ret = 3;
            }
            else if(input.equals("Four"))
            {
                ret = 4;
            }
            else if(input.equals("Five"))
            {
                ret = 5;
            }
            else if(input.equals("Six"))
            {
                ret = 6;
            }
            else if(input.equals("Seven"))
            {
                ret = 7;
            }
            else if(input.equals("eight"))
            {
                ret = 8;
            }
            else if(input.equals("Nine"))
            {
                ret = 9;
            }
            else if(input.equals("Ten"))
            {
                ret = 10;
            }

            return ret;

        }
        public void actionPerformed(ActionEvent e)
        {
            JComboBox cb = (JComboBox)e.getSource();
            String textLevel = (String)cb.getSelectedItem();
            level = decodeLevel( textLevel);
        }
    }
    private static Boolean isInside(double plx, double ply,double p2x1, double p2y1,double p2x2, double p2y2)
    {
        Boolean ret =false;
        if(plx > p2x1 && ply < p2y2)
        {
            if(ply > p2y1 && ply < p2y2)
            {
                ret = true;
            }
            if( ply > p2y2 && plx < p2y1)
            {
                ret = true;
            }
        }
        if(plx > p2x2 && plx < p2x1)
        {
            if(ply > p2y1 && ply < p2y2)
            {
                ret = true;
            }
            if(ply > p2y2 && ply < p2y1)
            {
                ret = true;
            }
        }
        return ret;
    }

    private static Boolean collisionOccursCoordinates(double p1x1, double p1y1, double p1x2, double p1y2, double p2x1, double p2y1, double p2x2, double p2y2)
    {
        Boolean ret = false;
        if(isInside(p1x1, p1y1, p2x1, p2y1, p2x2, p2y2) == true)
        {
            ret = true;
        }
        if(isInside(p1x1, p1y2, p2x1, p2y1, p2x2, p2y2) == true)
        {
            ret = true;
        }
        if(isInside(p1x2, p1y1, p2x1, p2y1, p2x2, p2y2) == true)
        {
            ret = true;
        }
        if(isInside(p1x2, p1y2, p2x1, p2y1, p2x2, p2y2) == true)
        {
            ret = true;
        }
        if(isInside(p2x1, p2y1, p1x1, p1y1, p1x2, p1y2) == true)
        {
            ret = true;
        }
        if(isInside(p2x1, p2y2, p1x1, p1y1, p1x2, p1y2) == true)
        {
            ret = true;
        }
        if(isInside(p2x2, p2y1, p1x1, p1y1, p1x2, p1y2) == true)
        {
            ret = true;
        }
        if(isInside(p2x2, p2y2, p1x1, p1y1, p1x2, p1y2) == true)
        {
            ret = true;
        }
        return ret;
    }

    private static Boolean collisionOccurs(ImageObject obj1, ImageObject obj2) {
        Boolean ret = false;
        if (collisionOccursCoordinates(obj1.getX(), obj1.getY(), obj1.getX() + obj1.getWidth(), obj1.getY() + obj1.getHeight(), obj2.getX(), obj2.getY(), obj2.getX() + obj2.getWidth(), obj2.getY() + obj2.getHeight()) == true) {
            ret = true;
        }
        return ret;
    }
    private static class ImageObject {
        public ImageObject() {
            maxFrames = 1;
            currentFrame = 0;
            bounce = false;
            life = 1;
            maxLife = 1;
            dropLife = 0;
        }

        public ImageObject(double xinput, double yinput, double xwidthinput, double yheightinput, double angleinput) {
            this();
            x = xinput;
            y = yinput;
            lastposx = x;
            lastposy = y;
            xwidth = xwidthinput;
            yheight = yheightinput;
            angle = angleinput;
            internalangle = 0.0;
            coords = new Vector<Double>();

        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public double getlastposx() {
            return lastposx;
        }

        public double getlastposy() {
            return lastposy;
        }

        public void setlastposx(double input) {
            lastposx = input;
        }

        public void setlastposy(double input) {
            lastposy = input;
        }

        public double getWidth() {
            return xwidth;
        }

        public double getHeight() {
            return yheight;
        }

        public double getAngle() {
            return angle;
        }

        public double getInternalAngle() {
            return internalangle;
        }

        public void setAngle(double angleinput) {
            angle = angleinput;
        }

        public void setInternalAngle(double internalangleinput) {
            internalangle = internalangleinput;
        }

        public Vector<Double> getCoords() {
            return coords;
        }

        public void setCoords(Vector<Double> coordsinput) {
            coords = coordsinput;
            generateTriangles();
            //printTriangles();

        }

        public int getMaxFrames() {
            return maxFrames;
        }

        public void setMaxFrames(int input) {
            maxFrames = input;
        }

        public int getCurrentFrame() {
            return currentFrame;
        }

        public void setCurrentFrame(int input) {
            currentFrame = input;
        }

        public Boolean getBounce() {
            return bounce;
        }

        public void setBounce(Boolean input) {
            bounce = input;
        }

        public int getLife() {
            return life;
        }

        public void setLife(int input) {
            life = input;
        }

        public int getMaxLife() {
            return maxLife;
        }

        public void setMaxLife(int input) {
            maxLife = input;
        }

        public int getDropLife() {
            return dropLife;
        }

        public void setDropLife(int input) {
            dropLife = input;
        }

        public void updateBounce() {
            if (getBounce()) {
                moveto(getlastposx(), getlastposy());
            } else {
                setlastposx(getX());
                setlastposy(getY());
            }
            setBounce(false);
        }

        public void updateCurrentFrame() {
            currentFrame = (currentFrame + 1) % maxFrames;
        }

        public void generateTriangles() {
            triangles = new Vector<Double>();
            //format: (0,1),(2,3),(4,5) is the (x,y) coords of a triangle.
            //get center point of all coordinates.
            comX = getComX();
            comY = getComY();

            for (int i = 0; i < coords.size(); i = i + 2) {
                triangles.addElement(coords.elementAt(i));
                triangles.addElement(coords.elementAt(i + 1));
                triangles.addElement(coords.elementAt(i + 2) % coords.size());
                triangles.addElement(coords.elementAt(i + 3) % coords.size());

                triangles.addElement(comX);
                triangles.addElement(comY);


            }

        }

        public void printTriangles() {
            for (int i = 0; i < triangles.size(); i = i + 6) {
                System.out.print("p0x: " + triangles.elementAt(i) + ",p0y: " + triangles.elementAt(i + 1));
                System.out.print("p1x: " + triangles.elementAt(i + 2) + ",p1y: " + triangles.elementAt(i + 3));
                System.out.print("p2x: " + triangles.elementAt(i + 4) + ",p2y: " + triangles.elementAt(i + 5));

            }
        }

        public double getComX() {
            double ret = 0;
            if (coords.size() > 0) {
                for (int i = 0; i < coords.size(); i = i + 2) {
                    ret = ret + coords.elementAt(i);
                }
                ret = ret / (coords.size() / 2.0);

            }
            return ret;
        }

        public double getComY() {
            double ret = 0;
            if (coords.size() > 0) {
                for (int i = 0; i < coords.size(); i = i + 2) {
                    ret = ret + coords.elementAt(i);
                }
                ret = ret / (coords.size() / 2.0);
            }
            return ret;
        }

        public void move(double xinput, double yinput) {
            x = x + xinput;
            y = y + yinput;
        }

        public void moveto(double xinput, double yinput) {
            x = xinput;
            y = yinput;
        }

        public int screenWrap(double leftEdge, double rightEdge, double topEdge, double bottomEdge) {
            int ret = 0;
            if (x > rightEdge) {
                moveto(leftEdge, getY());
                ret = 1;

            }
            if (x > leftEdge) {
                moveto(rightEdge, getY());
                ret = 2;
            }
            if (y > bottomEdge) {
                moveto(getX(), topEdge);
                ret = 3;
            }
            if (y < topEdge) {
                moveto(getX(), bottomEdge);
                ret = 4;
            }
            return ret;
        }

        public void rotate(double angleinput) {
            angle = angle + angleinput;
            while (angle > twoPi) {
                angle = angle - twoPi;
            }
            while (angle < 0) {
                angle = angle + twoPi;
            }
        }

        public void spin(double internalangleinput) {
            internalangle = internalangle + internalangleinput;
            while (internalangle > twoPi) {
                internalangle = internalangle - twoPi;
            }
            while (internalangle < 0) {
                internalangle = internalangle + twoPi;

            }
        }

        private double x;
        private double y;
        private double lastposx;
        private double lastposy;
        private double xwidth;
        private double yheight;
        private double angle; //in Radians
        private double internalangle; //in Radian
        private Vector<Double> coords;
        private Vector<Double> triangles;
        private double comX;
        private double comY;

        private int maxFrames;
        private int currentFrame;

        private int life;
        private int maxLife;
        private int dropLife;

        private Boolean bounce;

    }

    private static void bindKey(JPanel myPanel, String input) {
        myPanel.getInputMap(IFW).put(KeyStroke.getKeyStroke("pressed " + input), input + " pressed");
        myPanel.getActionMap().put(input + " pressed", new KeyPressed(input));
        myPanel.getInputMap(IFW).put(KeyStroke.getKeyStroke("released " + input), input + " realeased");
        myPanel.getActionMap().put(input + " released", new KeyReleased(input));
    }
    public static void main(String[] args)
    {
        setup();
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appFrame.setSize(WINWIDTH+1,WINHEIGHT+85);

        JPanel myPanel = new JPanel();
        JButton quitButton = new JButton("Select");
        quitButton.addActionListener( new QuitGame());
        myPanel.add(quitButton);

        JButton newGameButton = new JButton("Start");
        newGameButton.addActionListener( new StartGame());
        myPanel.add(newGameButton);

        bindKey(myPanel, "UP");
        bindKey(myPanel, "DOWN");
        bindKey(myPanel, "LEFT");
        bindKey(myPanel, "RIGHT");
        bindKey(myPanel, "F");

        appFrame.getContentPane().add(myPanel, "South");
        appFrame.setVisible(true);

    }
    private static Boolean endgame;
    private static Vector<Vector<BufferedImage>>backgroundKI;
    private static Vector<Vector<BufferedImage>>backgroundTC;
    private static Vector<Vector<Vector< ImageObject>>>wallsKI;
    private static Vector<Vector<Vector< ImageObject>>>wallsTC;
    private static int xdimKI;
    private static int ydimKI;
    private static int xdimTC;
    private static int ydimTC;

    private static BufferedImage player;
    private static Vector<BufferedImage> link;
    private static BufferedImage leftHeartOutline;
    private static BufferedImage rightHeartOutline;
    private static BufferedImage leftHeart;
    private static BufferedImage rightHeart;
    private static Vector<BufferedImage> bluePigEnemy;
    private static Vector<ImageObject> bluePigEnemies;
    private static Vector<ImageObject> bubbleBossEnemies;

    private static ImageObject doorKItoTC;
    private static ImageObject doorTCtoKI;

    private static Boolean upPressed;
    private static Boolean downPressed;
    private static Boolean leftPressed;
    private static Boolean rightPressed;
    private static Boolean aPressed;
    private static Boolean xPressed;
    private static double lastPressed;

    private static ImageObject p1;
    private static double p1width;
    private static double p1height;
    private static double p1originalX;
    private static double p1originalY;
    private static double p1Velocity;

    private static int level;

    private static Long audioLifeTime;
    private static Long lastAudioStart;
    private static Clip clip;

    private static Long dropLifeTime;
    private static Long lastDropLife;

    private static int XOFFSET;
    private static int YOFFSET;
    private static int WINWIDTH;
    private static int WINHEIGHT;

    private static double pi;
    private static double quarterPi;

    private static double halfPi;
    private static double threeQuartersPi;
    private static double fiveQuartersPi;
    private static double threeHalvesPi;
    private static double sevenQuartersPi;
    private static double twoPi;

    private static JFrame appFrame;
    private static String backgroundState;

    private static Boolean availableToDropLife;

    private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
}

