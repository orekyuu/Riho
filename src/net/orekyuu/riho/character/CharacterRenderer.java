package net.orekyuu.riho.character;

import net.orekyuu.riho.character.emotion.EmotionRenderer;
import net.orekyuu.riho.character.emotion.EmptyRenderer;
import net.orekyuu.riho.character.emotion.QuestionRenderer;
import net.orekyuu.riho.character.emotion.SweatRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.Duration;
import java.time.Instant;

public class CharacterRenderer {

    private Reaction reaction;
    private Instant reactionStartTime;
    private BufferedImage characterBase;
    private EmotionRenderer emotionRenderer = new SweatRenderer(Loop.infinite());

    CharacterRenderer() {
        characterBase = ImageResources.character();
    }

    void render(Component c, Graphics g, int x, int y, int width, int height) {
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

        int imageWidth = ImageUtil.defaultScale(characterBase.getWidth());
        int imageHeight = ImageUtil.defaultScale(characterBase.getHeight());
        int margin = ImageUtil.defaultScale(30);
        int charaX = width - imageWidth - margin + x;
        int charaY = height - imageHeight + y;

        if (reaction != null) {
            if (reaction.getDuration().toMillis() < Duration.between(reactionStartTime, Instant.now()).toMillis()) {
                reaction = null;
                emotionRenderer = new EmptyRenderer();
            }
        }

        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        renderCharaBase(g, charaX, charaY);
        renderFace(g, charaX, charaY);
        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        renderEmotion(g, charaX, charaY);

        if (emotionRenderer.isFinished()) {
            emotionRenderer = new EmptyRenderer();
        }
    }

    private void renderEmotion(Graphics g, int charaX, int charaY) {
        emotionRenderer.render(g, charaX, charaY);
    }

    private BufferedImage getFaceImage(FacePattern pattern) {
        switch (pattern) {
            case NORMAL: return ImageResources.faceNormal();
            case SMILE1: return ImageResources.faceSmile1();
            case SMILE2: return ImageResources.faceSmile2();
            case FUN: return ImageResources.faceFun();
            case AWAWA: return ImageResources.faceAwawa();
            case JITO: return ImageResources.faceJito();
            case SURPRISE: return ImageResources.faceSurprise();
            case SYUN: return ImageResources.faceSyun();
        }
        return ImageResources.faceNormal();
    }

    private EmotionRenderer getEmotionRenderer(Emotion emotion, Loop loop) {
        switch (emotion) {
            case NONE: return new EmptyRenderer();
            case QUESTION: return new QuestionRenderer(loop);
            case SWEAT: return new SweatRenderer(loop);
        }
        return new EmptyRenderer();
    }

    private void renderCharaBase(Graphics g, int x, int y) {
        ImageUtil.drawImage(g, characterBase, x, y);
    }

    private void renderFace(Graphics g, int x, int y) {
        FacePattern pattern;
        if (reaction == null) {
            pattern = FacePattern.NORMAL;
        } else {
            pattern = reaction.getFacePattern();
        }

        BufferedImage faceImage = getFaceImage(pattern);
        int mx = ImageUtil.defaultScale(107);
        int my = ImageUtil.defaultScale(120);
        ImageUtil.drawImage(g, faceImage, x + mx, y + my);
    }

    private boolean isHide(int width, int height) {
        return width < ImageUtil.defaultScale(characterBase.getWidth()) || height < ImageUtil.defaultScale(characterBase.getHeight());
    }

    void setReaction(Reaction reaction) {
        this.reaction = reaction;
        this.reactionStartTime = Instant.now();
        this.emotionRenderer = getEmotionRenderer(reaction.getEmotion(), reaction.getEmotionLoop());
    }
}
