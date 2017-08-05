package net.orekyuu.riho.character;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageUtil {

    public static final double DEFAULT_SCALE = 0.5;

    public static int scale(int original, double scale) {
        return (int) (original * scale);
    }

    public static int defaultScale(int original) {
        return scale(original, DEFAULT_SCALE);
    }

    public static void drawImage(Graphics g, BufferedImage image, int x, int y) {
        g.drawImage(image, x, y, null);
    }

    public static void drawImage(Graphics g, BufferedImage image, int x, int y, double a) {
        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) a));
        g.drawImage(image, x, y, null);
    }
}
