package porridge.my.way.dddarchitecturej.architecture.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AggregateRoot<TId extends Comparable<TId>> extends Entity<TId> implements IAggregateRoot {
    public AggregateRoot(TId id) {
        super(id);
    }

    protected List<DomainEvent> domainEvents = new ArrayList<>();

    protected void addDomainEvent(DomainEvent domainEvent) {
        this.domainEvents.add(domainEvent);
    }

    @Override
    public List<DomainEvent> getDomainEvents() {
        var domainEvents = new ArrayList<>(this.domainEvents);
        return Collections.unmodifiableList(domainEvents);
    }

    @Override
    public void clearDomainEvents() {
        this.domainEvents.clear();
    }

    @Override
    public List<DomainEvent> popDomainEvents() {
        List<DomainEvent> domainEvents = getDomainEvents();
        clearDomainEvents();
        return domainEvents;
    }
}
