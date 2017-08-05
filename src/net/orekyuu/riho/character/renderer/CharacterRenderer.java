package net.orekyuu.riho.character.renderer;

import com.intellij.util.ui.JBSwingUtilities;
import net.orekyuu.riho.character.ImageResources;
import net.orekyuu.riho.character.ImageUtil;
import net.orekyuu.riho.character.Riho;
import net.orekyuu.riho.emotion.renderer.EmotionRenderer;
import net.orekyuu.riho.emotion.renderer.EmotionRendererBase;
import net.orekyuu.riho.emotion.renderer.EmptyRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.Instant;

public class CharacterRenderer {

    private Riho riho;
    private BufferedImage characterBase;
    private EmotionRendererBase emotionRendererBase = new EmptyRenderer();
    private FaceRenderer faceRenderer = new FaceRenderer();
    private EmotionRenderer emotionRenderer = new EmotionRenderer();

    public CharacterRenderer(Riho riho) {
        characterBase = ImageResources.character();
        this.riho = riho;
    }

    public void render(Component c, Graphics g, int x, int y, int width, int height) {
        if (!(c.getParent() instanceof JViewport)) {
            return;
        }
        JViewport viewport = (JViewport) c.getParent();
        width = viewport.getWidth();
        height = viewport.getHeight();
        x = viewport.getViewRect().x;
        y = viewport.getViewRect().y;

        if (isHide(width, height)) {
            return;
        }
        Instant now = Instant.now();

        int imageWidth = characterBase.getWidth();
        int imageHeight = characterBase.getHeight();
        int margin = ImageUtil.defaultScale(30);
        int charaX = width - imageWidth - margin + x;
        int charaY = height - imageHeight + y;
        Graphics2D g2d = JBSwingUtilities.runGlobalCGTransform((JComponent) c, g);
        CharacterPosition position = CharacterPosition.of(charaX, charaY);

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        ImageUtil.drawImage(g2d, characterBase, charaX, charaY);
        faceRenderer.render(now, g2d, position, riho);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        emotionRenderer.render(now, g2d, position, riho);
    }

    private boolean isHide(int width, int height) {
        return width < characterBase.getWidth() || height < characterBase.getHeight();
    }
}
