package porridge.my.way.dddarchitecturej.architecture.core;

import java.util.Objects;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Entity<TId extends Comparable<TId>> implements Comparable<Entity<TId>> {
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PRIVATE)
    protected TId id;

    protected Entity(TId id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Entity<?> other = (Entity<?>) obj;

        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, getClass().getName());
    }

    @Override
    public int compareTo(Entity<TId> other) {
        if (other == null) {
            return 1;
        }
        if (this == other) {
            return 0;
        }
        return this.id.compareTo(other.id);
    }
}
