package net.orekyuu.riho.animation;

import java.time.Duration;
import java.time.Instant;

public class LinearAnimation extends Animation {
    public LinearAnimation(Instant startTime, Duration duration) {
        super(startTime, duration);
    }

    public LinearAnimation(Instant startTime, Duration duration, Duration delay) {
        super(startTime, duration, delay);
    }

    @Override
    public double getValue(Instant now) {
        double distance = (valueTo - valueFrom) * rate(time(now));
        return valueFrom + distance;
    }
}
