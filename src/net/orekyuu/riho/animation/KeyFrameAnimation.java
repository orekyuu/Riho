package net.orekyuu.riho.animation;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class KeyFrameAnimation extends Animation {

    private List<KeyFrame> keyFrames = new ArrayList<>();

    public KeyFrameAnimation(Instant startTime, Duration duration, List<KeyFrame> keyFrames) {
        super(startTime, duration);
        this.keyFrames = keyFrames;
        this.keyFrames.sort(Comparator.comparingDouble(KeyFrame::getStartRate));
    }

    public KeyFrameAnimation(Instant startTime, Duration duration, Duration delay, List<KeyFrame> keyFrames) {
        super(startTime, duration, delay);
        this.keyFrames = keyFrames;
        this.keyFrames.sort(Comparator.comparingDouble(KeyFrame::getStartRate));
    }

    @Override
    public double getValue(double rate) {
        Optional<KeyFrame> frameOpt = keyFrames.stream().filter(keyFrame -> keyFrame.inAnimation(rate)).findFirst();
        frameOpt.ifPresent(frame -> {
            Animation animation = frame.getAnimation();
            double r = frame.keyFrameRate(rate);
            value = animation.getValue(r);
        });
        return value;
    }

    @Override
    public void resetAndRestart(Instant now) {
        super.resetAndRestart(now);
        keyFrames.stream().map(KeyFrame::getAnimation).forEach(a -> a.resetAndRestart(now));
    }

    public static class KeyFrame {
        private double startRate;
        private double endRate;
        private Animation animation;

        public KeyFrame(double startRate, double endRate, Animation animation) {
            this.startRate = startRate;
            this.endRate = endRate;
            this.animation = animation;
        }

        double keyFrameRate(double rootRate) {
            double a = rootRate - startRate;
            double span = endRate - startRate;
            if (span == 0) {
                return 0;
            }
            double keyFrameRate = a / span;
            return Math.min(1, Math.max(keyFrameRate, 0));
        }

        public Animation getAnimation() {
            return animation;
        }

        public double getStartRate() {
            return startRate;
        }

        public double getEndRate() {
            return endRate;
        }

        public boolean inAnimation(double currentRate) {
            return startRate <= currentRate && currentRate <= endRate;
        }
    }
}
