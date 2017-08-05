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

public class QuestionRenderer extends EmotionRendererBase {

    private final BufferedImage image;
    private double startMoveTime = 700;
    private double waitTime = 500;
    private double loopTime = 2000;

    public QuestionRenderer(Loop loopCount) {
        super(loopCount);
        image = ImageResources.emotionQuestion();
    }
    @Override
    public void render(Instant now, Graphics2D g, CharacterPosition pos, Riho riho) {
        Duration currentLoopTime = Duration.between(getLoopStartTime(), now);

        int posY = pos.getY() + 40;
        long millis = currentLoopTime.toMillis();

        float alpha = 1.0f;
        if (millis < startMoveTime) {
            double a = ImageUtil.defaultScale(60) * Math.sin(millis / (startMoveTime * 2) * Math.PI * 2);
            posY -= a;
        } else if (millis < startMoveTime + waitTime) {
            // ignored
        } else {
            alpha = Math.max(((float)loopTime - millis) / (float)loopTime, 0);
        }
        ImageUtil.drawImage(g, image, pos.getX() + ImageUtil.defaultScale(340), posY, alpha);

        if (loopTime < millis) {
            nextLoop();
        }
    }
}
