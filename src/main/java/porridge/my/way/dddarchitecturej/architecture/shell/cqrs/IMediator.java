package porridge.my.way.dddarchitecturej.architecture.shell.cqrs;

import porridge.my.way.dddarchitecturej.architecture.core.DomainEvent;

import java.util.List;

public interface IMediator {
    <TResult> TResult send(ICommand<TResult> command);

    <TResult> TResult send(IQuery<TResult> query);

    void publish(DomainEvent domainEvent);

    void publish(List<DomainEvent> domainEvents);
}
