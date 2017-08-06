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

public class SurprisedRenderer extends EmotionRendererBase {

    private static final Duration ANIM_DURATION = Duration.ofMillis(1600);
    private final BufferedImage image;
    private final LinearAnimation fadeAnim = new LinearAnimation(Instant.now(), Duration.ofMillis(200), Duration.ofMillis(1300));

    public SurprisedRenderer(Loop maxLoopCount) {
        super(maxLoopCount);
        image = ImageResources.emotionSurprised();

        fadeAnim.setFromValue(1);
        fadeAnim.setToValue(0);
    }

    @Override
    public void render(Instant now, Graphics2D g, CharacterPosition pos, Riho riho) {
        Duration currentLoopTime = Duration.between(getLoopStartTime(), now);
        long millis = currentLoopTime.toMillis();
        boolean visible = true;
        if (millis < 800) {
            visible = ((millis / 200) % 2) == 0;
        }

        if (visible) {
            ImageUtil.drawImage(g, image, pos.getX() - ImageUtil.defaultScale(25), pos.getY() + ImageUtil.defaultScale(10), fadeAnim.getValue(now));
        }

        if (currentLoopTime.compareTo(ANIM_DURATION) > 0) {
            nextLoop();
            fadeAnim.resetAndRestart(now);
        }
    }
}
