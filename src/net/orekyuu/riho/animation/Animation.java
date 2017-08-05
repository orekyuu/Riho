package net.orekyuu.riho.animation;

import java.time.Duration;
import java.time.Instant;

public abstract class Animation {

    private Instant startTime;
    private final Duration duration;
    double valueFrom;
    double valueTo;
    double value;

    public Animation(Instant startTime, Duration duration) {
        this.startTime = startTime;
        this.duration = duration;
    }

    final double rate(Duration time) {
        if (duration.toMillis() == 0) {
            return 0;
        }
        double rate = (double) time.toMillis() / duration.toMillis();
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

    public final void resetAndRestart(Instant now) {
        startTime = now;
    }

    public abstract double getValue(Instant now);
}
