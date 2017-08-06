package net.orekyuu.riho.emotion.renderer;

import net.orekyuu.riho.character.ImageResources;
import net.orekyuu.riho.character.ImageUtil;
import net.orekyuu.riho.character.Loop;
import net.orekyuu.riho.character.Riho;
import net.orekyuu.riho.character.renderer.CharacterPosition;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.Duration;
import java.time.Instant;

public class DropRenderer extends EmotionRendererBase {

    private final BufferedImage image;
    private double loopTime = 1200;
    private double waitTime = 600;
    public DropRenderer(Loop maxLoopCount) {
        super(maxLoopCount);
        image = ImageResources.emotionDrop();
    }

    @Override
    public void render(Instant now, Graphics2D g, CharacterPosition pos, Riho riho) {
        Duration currentLoopTime = Duration.between(getLoopStartTime(), now);
        long millis = currentLoopTime.toMillis();

        int posY = pos.getY() + ImageUtil.defaultScale(50);
        double a = Math.min(millis, loopTime - waitTime) / (loopTime - waitTime);

        posY += ImageUtil.defaultScale(20) * a;
        ImageUtil.drawImage(g, image, pos.getX() + ImageUtil.defaultScale(110), posY, a);
        if (loopTime < millis) {
            nextLoop();
        }
    }
}
