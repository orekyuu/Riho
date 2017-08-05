package net.orekyuu.riho.character;

import com.intellij.util.ui.JBUI;
import net.orekyuu.riho.character.renderer.CharacterRenderer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.Instant;

public class CharacterBorder implements Border, ActionListener {

    private final CharacterRenderer characterRenderer;
    private final Timer timer;
    private final Riho riho;
    private Component component;
    private final int REPAINT_DELAY = 30;

    public CharacterBorder(JComponent component, CharacterRenderer characterRenderer, Riho riho) throws IOException {
        this.component = component;
        timer = new Timer(REPAINT_DELAY, this);
        timer.start();
        this.characterRenderer = characterRenderer;
        this.riho = riho;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        characterRenderer.render(c, g, x, y, width, height);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return JBUI.emptyInsets();
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        riho.updateCharacter(Instant.now());
        component.repaint(REPAINT_DELAY);
    }

    public void dispose() {
        timer.stop();
    }
}
