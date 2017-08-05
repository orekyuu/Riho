package net.orekyuu.riho.character.renderer;

import net.orekyuu.riho.character.ImageResources;
import net.orekyuu.riho.character.ImageUtil;
import net.orekyuu.riho.character.Riho;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.Instant;

public class FaceRenderer implements Renderer {
    @Override
    public void render(Instant now, Graphics2D g, CharacterPosition pos, Riho riho) {
        int mx = ImageUtil.defaultScale(107);
        int my = ImageUtil.defaultScale(120);
        ImageUtil.drawImage(g, getFaceImage(riho), pos.getX() + mx, pos.getY() + my);
    }

    private BufferedImage getFaceImage(Riho riho) {
        switch (riho.getFace()) {
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
}
