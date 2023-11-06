package porridge.my.way.dddarchitecturej.architecture.shell.cqrs;

import an.awesome.pipelinr.Voidy;
import porridge.my.way.dddarchitecturej.architecture.core.IDomainEvent;

public interface IMediator {
    Voidy execute(INoneCommand command);

    <TResult> TResult execute(ICommand<TResult> command);

    <TResult> TResult fetch(IQuery<TResult> query);

    void publish(IDomainEvent domainEvent);
}
