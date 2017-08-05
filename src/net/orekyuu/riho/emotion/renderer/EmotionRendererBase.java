package net.orekyuu.riho.emotion.renderer;

import net.orekyuu.riho.character.Loop;
import net.orekyuu.riho.character.renderer.Renderer;

import java.time.Instant;

public abstract class EmotionRendererBase implements Renderer {

    private final Loop maxLoopCount;
    private Instant loopStartTime;
    private boolean finished;
    private int loopCount;

    public EmotionRendererBase(Loop maxLoopCount) {
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
}
