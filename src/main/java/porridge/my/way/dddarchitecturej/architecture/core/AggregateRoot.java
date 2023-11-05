package porridge.my.way.dddarchitecturej.architecture.core;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;

public abstract class AggregateRoot<TId extends Comparable<TId>> extends Entity<TId> {
    protected AggregateRoot() {
        super();
    }

    public AggregateRoot(TId id) {
        super(id);
    }

    @Getter(AccessLevel.PUBLIC)
    List<IDomainEvent> domainEvents = new ArrayList<>();

    protected void addDomainEvent(IDomainEvent domainEvent) {
        this.domainEvents.add(domainEvent);
    }

    public void clearDomainEvents() {
        this.domainEvents.clear();
    }
}
