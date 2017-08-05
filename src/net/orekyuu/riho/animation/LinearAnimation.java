package net.orekyuu.riho.animation;

import java.time.Duration;
import java.time.Instant;

public class LinearAnimation extends Animation {
    public LinearAnimation(Instant startTime, Duration duration) {
        super(startTime, duration);
    }

    @Override
    public double getValue(Instant now) {
        double distance = (valueTo - valueFrom) * rate(time(now));
        return valueFrom + distance;
    }
}
