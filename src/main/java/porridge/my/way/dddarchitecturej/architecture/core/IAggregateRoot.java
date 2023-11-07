package porridge.my.way.dddarchitecturej.architecture.core;

import java.util.List;

public interface IAggregateRoot {
    List<IDomainEvent> getDomainEvents();

    void clearDomainEvents();
}
