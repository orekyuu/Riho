package net.orekyuu.riho.character.emotion;

import net.orekyuu.riho.character.ImageResources;
import net.orekyuu.riho.character.ImageUtil;
import net.orekyuu.riho.character.Loop;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.Duration;
import java.time.Instant;

public class SweatRenderer extends EmotionRenderer {

    private final BufferedImage image;
    private double loopTime = 1200;
    private double waitTime = 600;
    public SweatRenderer(Loop maxLoopCount) {
        super(maxLoopCount);
        image = ImageResources.emotionSweat();
    }

    @Override
    public void render(Graphics g, int charaX, int charaY) {
        Duration currentLoopTime = Duration.between(getLoopStartTime(), Instant.now());
        long millis = currentLoopTime.toMillis();

        int posY = charaY + ImageUtil.defaultScale(40);
        double a = Math.min(millis, loopTime - waitTime) / (loopTime - waitTime);

        posY += ImageUtil.defaultScale(20) * a;
        ImageUtil.drawImage(g, image, charaX + ImageUtil.defaultScale(20), posY, a);
        if (loopTime < millis) {
            nextLoop();
        }
    }
}
