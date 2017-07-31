package net.orekyuu.riho.character;

import net.orekyuu.riho.RihoPlugin;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.HashMap;

public class ImageResources {

    private static HashMap<String, BufferedImage> resources = new HashMap<>();

    public static BufferedImage character() {
        return lazyLoad("character", "/riho.png");
    }

    public static BufferedImage emotionQuestion() {
        return lazyLoad("emotion.question", "/emotion/question.png");
    }

    public static BufferedImage emotionSweat() {
        return lazyLoad("emotion.sweat", "/emotion/sweat.png");
    }

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
                return ImageIO.read(RihoPlugin.class.getResourceAsStream(path));
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        });
    }
}
