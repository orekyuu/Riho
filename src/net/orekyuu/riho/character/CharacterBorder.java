package net.orekyuu.riho.character;

import com.intellij.ui.JBColor;
import com.intellij.util.ui.JBUI;
import net.orekyuu.riho.RihoPlugin;
import net.orekyuu.riho.topics.RihoReactionNotifier;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CharacterBorder implements Border, RihoReactionNotifier, ActionListener {

    private final CharacterRenderer characterRenderer;
    private Component component;

    public CharacterBorder(JComponent component) throws IOException {
        new Timer(10, this);
        this.component = component;

        characterRenderer = new CharacterRenderer();
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g;
        characterRenderer.render(c, g, x, y, width, height);
        c.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        component.repaint();
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
    public void reaction(Reaction reaction) {
        characterRenderer.setReaction(reaction);
    }
}
