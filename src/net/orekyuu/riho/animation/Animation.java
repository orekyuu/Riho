package net.orekyuu.riho.animation;

import java.time.Duration;
import java.time.Instant;

public abstract class Animation {

    private Instant startTime;
    private final Duration duration;
    private final Duration delay;
    double valueFrom;
    double valueTo;
    double value;

    public Animation(Instant startTime, Duration duration) {
        this(startTime, duration, Duration.ZERO);
    }

    public Animation(Instant startTime, Duration duration, Duration delay) {
        this.startTime = startTime;
        this.duration = duration;
        this.delay = delay;
    }

    final double rate(Duration time) {
        long duration = this.duration.toMillis();
        long delay = this.delay.toMillis();
        long currentTime = time.toMillis();
        // delay中はrateは増えない
        if (currentTime < delay) {
            return 0;
        }

        long totalTime = delay + duration + 1;

        double rate = (double) time.toMillis() / totalTime;
        return Math.min(1, Math.max(rate, 0));
    }

    final Duration time(Instant now) {
        return Duration.between(startTime, now);
    }

    public final void setFromValue(double value) {
        this.valueFrom = value;
    }

    public final void setToValue(double value) {
        this.valueTo = value;
    }

    public void resetAndRestart(Instant now) {
        startTime = now;
    }

    public double getValue(Instant now) {
        return getValue(rate(time(now)));
    }

    public abstract double getValue(double rate);
}
