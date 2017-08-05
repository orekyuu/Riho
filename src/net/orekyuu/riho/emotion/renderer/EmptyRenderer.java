package net.orekyuu.riho.emotion.renderer;

import net.orekyuu.riho.character.Loop;
import net.orekyuu.riho.character.Riho;
import net.orekyuu.riho.character.renderer.CharacterPosition;

import java.awt.*;
import java.time.Instant;

public class EmptyRenderer extends EmotionRendererBase {
    public EmptyRenderer() {
        super(Loop.infinite());
    }

    @Override
    public void render(Instant now, Graphics2D g, CharacterPosition pos, Riho riho) {

    }
}
