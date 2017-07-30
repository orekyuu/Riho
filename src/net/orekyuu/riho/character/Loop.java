package net.orekyuu.riho.character;

import java.util.Objects;

public class Loop {
    private final int loopCount;
    private static final Loop INFINITE = new Loop(-1);
    private static final Loop ONCE = Loop.of(1);

    private Loop(int count) {
        this.loopCount = count;
    }

    public static Loop of(int loopCount) {
        if (loopCount < 0) {
            throw new IllegalArgumentException();
        }
        return new Loop(loopCount);
    }

    public boolean isInfinite() {
        return this == INFINITE;
    }

    public int getLoopCount() {
        return loopCount;
    }

    public static Loop infinite() {
        return INFINITE;
    }

    public static Loop once() {
        return ONCE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loop loop = (Loop) o;
        return loopCount == loop.loopCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(loopCount);
    }
}
