package net.orekyuu.riho.emotion.renderer;

import net.orekyuu.riho.animation.LinearAnimation;
import net.orekyuu.riho.character.ImageResources;
import net.orekyuu.riho.character.ImageUtil;
import net.orekyuu.riho.character.Loop;
import net.orekyuu.riho.character.Riho;
import net.orekyuu.riho.character.renderer.CharacterPosition;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.Duration;
import java.time.Instant;

public class SadRenderer extends EmotionRendererBase {

    private final BufferedImage emotionImage;
    private Duration animationTime = Duration.ofMillis(2000);

    private LinearAnimation move = new LinearAnimation(Instant.now(), Duration.ofMillis(700));
    private LinearAnimation fadeIn = new LinearAnimation(Instant.now(), Duration.ofMillis(700));
    private Duration fadeOutDelay = animationTime.minusMillis(400);
    private LinearAnimation fadeOut = new LinearAnimation(Instant.now(), Duration.ofMillis(300), fadeOutDelay);

    public SadRenderer(Loop maxLoopCount) {
        super(maxLoopCount);
        move.setFromValue(ImageUtil.defaultScale(-240));
        move.setToValue(ImageUtil.defaultScale(ImageUtil.defaultScale(-200)));

        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        emotionImage = ImageResources.emotionFeelSad();

        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
    }

    @Override
    public void render(Instant now, Graphics2D g, CharacterPosition pos, Riho riho) {
        Duration time = currentLoopDuration(now);
        double y = move.getValue(now);
        double alpha = time.compareTo(fadeOutDelay) > 0 ? fadeOut.getValue(now) : fadeIn.getValue(now);

        ImageUtil.drawImage(g, emotionImage, pos.getX() - ImageUtil.defaultScale(100), (int) (pos.getY() + y), alpha);

        if (time.compareTo(animationTime) > 0) {
            nextLoop();
            fadeIn.resetAndRestart(now);
            fadeOut.resetAndRestart(now);
            move.resetAndRestart(now);
        }
    }
}
