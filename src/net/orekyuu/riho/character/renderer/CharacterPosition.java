package net.orekyuu.riho.character.renderer;

import java.util.Objects;

public class CharacterPosition {

    private final int x;
    private final int y;

    private CharacterPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static CharacterPosition of(int x, int y) {
        return new CharacterPosition(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacterPosition that = (CharacterPosition) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CharacterPosition{");
        sb.append("x=").append(x);
        sb.append(", y=").append(y);
        sb.append('}');
        return sb.toString();
    }
}
