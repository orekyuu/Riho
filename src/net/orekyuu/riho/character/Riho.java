package net.orekyuu.riho.character;

import net.orekyuu.riho.emotion.Emotion;
import net.orekyuu.riho.topics.RihoReactionNotifier;

import java.time.Duration;
import java.time.Instant;

public class Riho implements RihoReactionNotifier {
    private Reaction reaction;
    private Instant reactionStartTime;

    public Reaction getReaction() {
        return reaction;
    }

    public FacePattern getFace() {
        if (reaction == null) {
            return FacePattern.NORMAL;
        }
        return reaction.getFacePattern();
    }

    public Emotion getEmotion() {
        if (reaction == null) {
            return Emotion.NONE;
        }
        return reaction.getEmotion();
    }

    void updateCharacter(Instant now) {
        updateReaction(now);
    }

    private void updateReaction(Instant now) {
        if (isDefault()) {
            return;
        }

        if (Duration.between(reactionStartTime, now).compareTo(reaction.getDuration()) > 0) {
            resetReaction();
        }
    }

    private void resetReaction() {
        reaction = null;
        reactionStartTime = null;
    }

    private boolean isDefault() {
        return reaction == null && reactionStartTime == null;
    }

    @Override
    public void reaction(Reaction reaction) {
        reactionStartTime = Instant.now();
        this.reaction = reaction;
    }
}
