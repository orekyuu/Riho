package net.orekyuu.riho.character.emotion;

import net.orekyuu.riho.character.Loop;

import java.awt.*;
import java.time.Instant;

public abstract class EmotionRenderer {

    private final Loop maxLoopCount;
    private Instant loopStartTime;
    private boolean finished;
    private int loopCount;

    public EmotionRenderer(Loop maxLoopCount) {
        this.maxLoopCount = maxLoopCount;
        this.loopStartTime = Instant.now();
    }

    protected void finish() {
        finished = true;
    }

    public boolean isFinished() {
        return finished;
    }

    public Loop getMaxLoopCount() {
        return maxLoopCount;
    }

    public Instant getLoopStartTime() {
        return loopStartTime;
    }

    public void resetLoopTime() {
        loopStartTime = Instant.now();
    }

    protected void nextLoop() {
        loopCount++;
        resetLoopTime();
        if (!maxLoopCount.isInfinite() && maxLoopCount.getLoopCount() <= loopCount) {
            finish();
        }
    }

    public abstract void render(Graphics g, int charaX, int charaY);
}
