package porridge.my.way.dddarchitecturej.architecture.core;

import java.util.List;

public interface IAggregateRoot {
    List<DomainEvent> getDomainEvents();

    void clearDomainEvents();
}
