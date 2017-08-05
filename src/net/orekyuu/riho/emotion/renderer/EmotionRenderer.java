package net.orekyuu.riho.emotion.renderer;

import net.orekyuu.riho.character.Loop;
import net.orekyuu.riho.character.Reaction;
import net.orekyuu.riho.character.Riho;
import net.orekyuu.riho.character.renderer.CharacterPosition;
import net.orekyuu.riho.character.renderer.Renderer;
import net.orekyuu.riho.emotion.Emotion;

import java.awt.*;
import java.time.Instant;

public class EmotionRenderer implements Renderer{

    private Reaction prevReaction;
    private EmotionRendererBase emotionRendererBase = new EmptyRenderer();
    @Override
    public void render(Instant now, Graphics2D g, CharacterPosition pos, Riho riho) {
        Reaction currentReaction = riho.getReaction();
        if (currentReaction != prevReaction) {
            emotionRendererBase = getEmotionRenderer(currentReaction);
            prevReaction = currentReaction;
        }
        if (emotionRendererBase.isFinished()) {
            emotionRendererBase = new EmptyRenderer();
        }

        emotionRendererBase.render(now, g, pos, riho);
    }

    private EmotionRendererBase getEmotionRenderer(Reaction reaction) {
        if (reaction == null) {
            return new EmptyRenderer();
        }
        Emotion emotion = reaction.getEmotion();
        Loop loop = reaction.getEmotionLoop();
        switch (emotion) {
            case NONE: return new EmptyRenderer();
            case QUESTION: return new QuestionRenderer(loop);
            case DROP: return new SweatRenderer(loop);
        }
        return new EmptyRenderer();
    }
}
