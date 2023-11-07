package porridge.my.way.dddarchitecturej.architecture.shell.cqrs;

import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Voidy;
import porridge.my.way.dddarchitecturej.architecture.core.IAggregateRoot;
import porridge.my.way.dddarchitecturej.architecture.core.IDomainEvent;

public class Mediator implements IMediator {

    private Pipeline pipeline;

    public Mediator(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    @Override
    public Voidy execute(INoneCommand command) {
        return pipeline.send(command);
    }

    @Override
    public <TResult> TResult execute(ICommand<TResult> command) {
        return pipeline.send(command);
    }

    @Override
    public <TResult> TResult fetch(IQuery<TResult> query) {
        return pipeline.send(query);
    }

    @Override
    public void publish(IDomainEvent domainEvent) {
        pipeline.send(domainEvent);
    }

    @Override
    public void publishAndClearDomainEvents(IAggregateRoot aggregateRoot) {
        var domainEvents = aggregateRoot.getDomainEvents();
        aggregateRoot.clearDomainEvents();

        for (var domainEvent : domainEvents) {
            pipeline.send(domainEvent);
        }
    }
}
