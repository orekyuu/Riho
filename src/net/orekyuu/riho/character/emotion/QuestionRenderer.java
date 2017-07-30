package net.orekyuu.riho.character.emotion;

import com.intellij.ui.JBColor;
import net.orekyuu.riho.character.ImageResources;
import net.orekyuu.riho.character.ImageUtil;
import net.orekyuu.riho.character.Loop;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.Duration;
import java.time.Instant;

public class QuestionRenderer extends EmotionRenderer {

    private final BufferedImage image;
    private double startMoveTime = 700;
    private double waitTime = 500;
    private double loopTime = 2000;

    public QuestionRenderer(Loop loopCount) {
        super(loopCount);
        image = ImageResources.emotionQuestion();
    }

    @Override
    public void render(Graphics g, int charaX, int charaY) {
        Duration currentLoopTime = Duration.between(getLoopStartTime(), Instant.now());

        int posY = charaY + 10;
        long millis = currentLoopTime.toMillis();

        if (millis < startMoveTime) {
            double a = ImageUtil.defaultScale(60) * Math.sin(millis / (startMoveTime * 2) * Math.PI * 2);
            posY -= a;
        } else if (millis < startMoveTime + waitTime) {
            // ignored
        } else {
            float alpha = Math.max(((float)loopTime - millis) / (float)loopTime, 0);
            ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        }
        ImageUtil.drawImage(g, image, charaX + ImageUtil.defaultScale(190), posY);

        if (loopTime < millis) {
            nextLoop();
        }
    }
}
