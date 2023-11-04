package porridge.my.way.dddarchitecturej.architecture.core;

import java.util.Objects;

public abstract class ValueObject {

    protected abstract Iterable<Object> getEqualityComponents();

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        ValueObject other = (ValueObject) obj;

        Iterable<Object> components = getEqualityComponents();
        Iterable<Object> otherComponents = other.getEqualityComponents();

        return Objects.deepEquals(components, otherComponents);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (Object component : getEqualityComponents()) {
            if (component != null) {
                hash ^= component.hashCode();
            }
        }
        return hash;
    }
}