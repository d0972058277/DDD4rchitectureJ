package porridge.my.way.dddarchitecturej.architecture.shell.cqrs;

import java.util.List;

import org.springframework.stereotype.Component;

import an.awesome.pipelinr.Pipeline;
import porridge.my.way.dddarchitecturej.architecture.core.DomainEvent;

@Component
public class Mediator implements IMediator {

    private Pipeline pipeline;

    public Mediator(Pipeline pipeline) {
        this.pipeline = pipeline;
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
    public void publish(DomainEvent domainEvent) {
        pipeline.send(domainEvent);
    }

    @Override
    public void publish(List<DomainEvent> domainEvents) {
        for (var domainEvent : domainEvents) {
            publish(domainEvent);
        }
    }
}
