package net.orekyuu.riho.character.renderer;

import net.orekyuu.riho.character.Riho;

import java.awt.*;
import java.time.Instant;

public interface Renderer {

    void render(Instant now, Graphics2D g, CharacterPosition pos, Riho riho);
}
