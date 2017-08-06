package net.orekyuu.riho.animation;

import java.time.Duration;
import java.time.Instant;

public class EaseOutAnimation extends Animation {
    public EaseOutAnimation(Instant startTime, Duration duration) {
        super(startTime, duration);
    }

    public EaseOutAnimation(Instant startTime, Duration duration, Duration delay) {
        super(startTime, duration, delay);
    }

    @Override
    public double getValue(double rate) {
        double distance = rate * (2 - rate);
        return valueFrom + (valueTo * distance);
    }
}
