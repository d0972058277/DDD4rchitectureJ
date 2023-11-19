package porridge.my.way.dddarchitecturej.architecture.shell.cqrs;

import java.util.List;

import an.awesome.pipelinr.Voidy;
import porridge.my.way.dddarchitecturej.architecture.core.DomainEvent;

public interface IMediator {
    Voidy execute(INoneCommand command);

    <TResult> TResult execute(ICommand<TResult> command);

    <TResult> TResult fetch(IQuery<TResult> query);

    void publish(DomainEvent domainEvent);

    void publish(List<DomainEvent> domainEvents);
}
