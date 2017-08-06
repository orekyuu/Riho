package net.orekyuu.riho.animation;

import java.time.Duration;
import java.time.Instant;

public class EaseInAnimation extends Animation {
    public EaseInAnimation(Instant startTime, Duration duration) {
        super(startTime, duration);
    }

    public EaseInAnimation(Instant startTime, Duration duration, Duration delay) {
        super(startTime, duration, delay);
    }

    @Override
    public double getValue(Instant now) {
        double rate = rate(time(now));
        double distance = rate * rate;
        return valueFrom + (valueTo * distance);
    }
}
