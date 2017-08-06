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

public class SweatRenderer extends EmotionRendererBase {

    private static final Duration ANIM_DURATION = Duration.ofMillis(2000);
    private final BufferedImage[] images;
    private final LinearAnimation fadeAnim = new LinearAnimation(Instant.now(), Duration.ofMillis(500), Duration.ofMillis(1500));

    public SweatRenderer(Loop maxLoopCount) {
        super(maxLoopCount);
        BufferedImage sweat1 = ImageResources.emotionSweat1();
        BufferedImage sweat2 = ImageResources.emotionSweat2();
        images = new BufferedImage[]{sweat1, sweat2};

        fadeAnim.setFromValue(1);
        fadeAnim.setToValue(0);
    }

    @Override
    public void render(Instant now, Graphics2D g, CharacterPosition pos, Riho riho) {
        Duration currentLoopTime = Duration.between(getLoopStartTime(), now);
        long millis = currentLoopTime.toMillis();

        BufferedImage image = images[(int) ((millis / 200) % 2)];
        ImageUtil.drawImage(g, image, pos.getX() - ImageUtil.defaultScale(25), pos.getY() + ImageUtil.defaultScale(200), fadeAnim.getValue(now));

        if (currentLoopTime.compareTo(ANIM_DURATION) > 0) {
            nextLoop();
            fadeAnim.resetAndRestart(now);
        }
    }
}
