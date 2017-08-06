package net.orekyuu.riho.emotion.renderer;

import net.orekyuu.riho.animation.Animation;
import net.orekyuu.riho.animation.KeyFrameAnimation;
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
import java.util.ArrayList;

public class AngerRenderer extends EmotionRendererBase {
    private final BufferedImage emotionImage;
    private Duration animationTime = Duration.ofMillis(2000);
    private Animation moveX;
    private Animation moveY;
    private Animation fade = new LinearAnimation(Instant.now(), Duration.ofMillis(400), Duration.ofMillis(1600));

    private int[][] pos = {
            {0, 0},
            {15, 15},
            {10, -5},
            {-5, 0},
            {0, 10},
            {5, 5},
            {10, -10},
            {10, 5},
            {15, 10},
            {10, 0},
            {7, -15},
    };

    public AngerRenderer(Loop maxLoopCount) {
        super(maxLoopCount);
        emotionImage = ImageResources.emotionAnger();

        ArrayList<KeyFrameAnimation.KeyFrame> animX = new ArrayList<>();
        ArrayList<KeyFrameAnimation.KeyFrame> animY = new ArrayList<>();
        Instant now = Instant.now();
        double r = 1f / pos.length;
        for (int i = 1; i < pos.length; i++) {
            LinearAnimation x = new LinearAnimation(now, Duration.ZERO);
            x.setFromValue(ImageUtil.defaultScale(pos[i - 1][0]));
            x.setToValue(ImageUtil.defaultScale(pos[i][0]));
            animX.add(new KeyFrameAnimation.KeyFrame(r * (i - 1), r * i, x));

            LinearAnimation y = new LinearAnimation(now, Duration.ZERO);
            y.setFromValue(ImageUtil.defaultScale(pos[i][1]));
            y.setToValue(ImageUtil.defaultScale(pos[i - 1][1]));
            animY.add(new KeyFrameAnimation.KeyFrame(r * (i - 1), r * i, y));
        }

        moveX = new KeyFrameAnimation(now, Duration.ofMillis(2000), animX);
        moveY = new KeyFrameAnimation(now, Duration.ofMillis(2000), animY);

        fade.setFromValue(1);
        fade.setToValue(0);
    }

    @Override
    public void render(Instant now, Graphics2D g, CharacterPosition pos, Riho riho) {
        Duration time = currentLoopDuration(now);

        int x = (int) moveX.getValue(now);
        int y = (int) moveY.getValue(now);
        double alpha = fade.getValue(now);

        x += ImageUtil.defaultScale(350);

        ImageUtil.drawImage(g, emotionImage, pos.getX() + x, pos.getY() + y, alpha);

        if (time.compareTo(animationTime) > 0) {
            nextLoop();
            moveX.resetAndRestart(now);
            moveY.resetAndRestart(now);
            fade.resetAndRestart(now);
        }
    }
}
