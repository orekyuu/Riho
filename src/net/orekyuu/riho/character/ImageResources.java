package net.orekyuu.riho.character;

import net.orekyuu.riho.RihoPlugin;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.HashMap;

public class ImageResources {

    private static HashMap<String, BufferedImage> resources = new HashMap<>();

    public static BufferedImage character() {
        return lazyLoad("character", "/riho.png");
    }

    // Emotions
    public static BufferedImage emotionQuestion() {
        return lazyLoad("emotion.question", "/emotion/question.png");
    }

    public static BufferedImage emotionDrop() {
        return lazyLoad("emotion.drop", "/emotion/drop.png");
    }

    public static BufferedImage emotionMojya() {
        return lazyLoad("emotion.mojya", "/emotion/mojya.png");
    }

    public static BufferedImage emotionSweat1() {
        return lazyLoad("emotion.sweat1", "/emotion/sweat1.png");
    }

    public static BufferedImage emotionSweat2() {
        return lazyLoad("emotion.sweat2", "/emotion/sweat2.png");
    }

    public static BufferedImage emotionSurprised() {
        return lazyLoad("emotion.surprised", "/emotion/surprised.png");
    }

    public static BufferedImage emotionAnger() {
        return lazyLoad("emotion.anger", "/emotion/anger.png");
    }

    public static BufferedImage emotionFeelSad() {
        return lazyLoad("emotion.feel-sad", "/emotion/doyon.png");
    }

    // Faces
    public static BufferedImage faceNormal() {
        return lazyLoad("face.normal", "/face/normal.png");
    }

    public static BufferedImage faceSmile1() {
        return lazyLoad("face.smile1", "/face/smile1.png");
    }

    public static BufferedImage faceSmile2() {
        return lazyLoad("face.smile2", "/face/smile2.png");
    }

    public static BufferedImage faceAwawa() {
        return lazyLoad("face.awawa", "/face/awawa.png");
    }

    public static BufferedImage faceFun() {
        return lazyLoad("face.fun", "/face/fun.png");
    }

    public static BufferedImage faceJito() {
        return lazyLoad("face.jito", "/face/jito.png");
    }

    public static BufferedImage faceSurprise() {
        return lazyLoad("face.surprise", "/face/surprise.png");
    }

    public static BufferedImage faceSyun() {
        return lazyLoad("face.syun", "/face/syun.png");
    }

    private static BufferedImage lazyLoad(String key, String path) {
        return resources.computeIfAbsent(key, str -> {
            try {
                BufferedImage read = ImageIO.read(RihoPlugin.class.getResourceAsStream(path));
                int width = ImageUtil.defaultScale(read.getWidth());
                int height = ImageUtil.defaultScale(read.getHeight());
                BufferedImage resultImage = new BufferedImage(width, height, read.getType());
                Graphics resultImageGraphics = resultImage.getGraphics();
                resultImageGraphics.drawImage(read.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING), 0, 0, width, height, null);
                return resultImage;
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });
    }
}
