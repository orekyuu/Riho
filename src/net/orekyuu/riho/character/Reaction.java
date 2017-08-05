package net.orekyuu.riho.character;

import net.orekyuu.riho.emotion.Emotion;

import java.time.Duration;

public class Reaction {
    private final Emotion emotion;
    private final Loop emotionLoop;
    private final Duration duration ;
    private final FacePattern facePattern;

    public Reaction(FacePattern facePattern, Duration duration, Emotion emotion, Loop emotionLoop) {
        this.emotion = emotion;
        this.emotionLoop = emotionLoop;
        this.duration = duration;
        this.facePattern = facePattern;
    }

    public static Reaction of(FacePattern facePattern, Duration duration) {
        return of(facePattern, duration, Emotion.NONE, Loop.once());
    }

    public static Reaction of(FacePattern facePattern, Duration duration, Emotion emotion, Loop emotionLoop) {
        return new Reaction(facePattern, duration, emotion, emotionLoop);
    }

    public Emotion getEmotion() {
        return emotion;
    }

    public Loop getEmotionLoop() {
        return emotionLoop;
    }

    public Duration getDuration() {
        return duration;
    }

    public FacePattern getFacePattern() {
        return facePattern;
    }
}
