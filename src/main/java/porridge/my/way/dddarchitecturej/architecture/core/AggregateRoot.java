package porridge.my.way.dddarchitecturej.architecture.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AggregateRoot<TId extends Comparable<TId>> extends Entity<TId> implements IAggregateRoot {
    protected AggregateRoot() {
        super();
    }

    public AggregateRoot(TId id) {
        super(id);
    }

    List<IDomainEvent> domainEvents = new ArrayList<>();

    protected void addDomainEvent(IDomainEvent domainEvent) {
        this.domainEvents.add(domainEvent);
    }

    @Override
    public List<IDomainEvent> getDomainEvents() {
        var domainEvents = new ArrayList<>(this.domainEvents);
        return Collections.unmodifiableList(domainEvents);
    }

    @Override
    public void clearDomainEvents() {
        this.domainEvents.clear();
    }
}
