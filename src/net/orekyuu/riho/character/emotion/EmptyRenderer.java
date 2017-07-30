package net.orekyuu.riho.character.emotion;

import net.orekyuu.riho.character.Loop;

import java.awt.*;

public class EmptyRenderer extends EmotionRenderer {
    public EmptyRenderer() {
        super(Loop.infinite());
    }

    @Override
    public void render(Graphics g, int charaX, int charaY) {

    }
}
