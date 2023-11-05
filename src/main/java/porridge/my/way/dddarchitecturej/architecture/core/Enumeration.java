package porridge.my.way.dddarchitecturej.architecture.core;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

import lombok.AccessLevel;

public abstract class Enumeration implements Comparable<Enumeration> {

    @Setter(AccessLevel.PRIVATE)
    @Getter(AccessLevel.PUBLIC)
    private String name;

    @Setter(AccessLevel.PRIVATE)
    @Getter(AccessLevel.PUBLIC)
    private int id;

    @Override
    public String toString() {
        return this.name;
    }

    protected Enumeration(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Enumeration that = (Enumeration) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public int compareTo(Enumeration other) {
        return Integer.compare(this.id, other.id);
    }
}
