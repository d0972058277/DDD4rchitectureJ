package porridge.my.way.dddarchitecturej.architecture.shell.cqrs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import an.awesome.pipelinr.Pipeline;
import porridge.my.way.dddarchitecturej.architecture.core.AggregateRoot;
import porridge.my.way.dddarchitecturej.architecture.core.DomainEvent;

public class MediatorTests {
    @Test
    public void testExecuteResultCommand() {
        // Given
        var pipeline = Mockito.mock(Pipeline.class);
        var mediator = new Mediator(pipeline);
        var command = new ResultCommand();

        // When
        mediator.execute(command);

        // Then
        Mockito.verify(pipeline, times(1)).send(command);
    }

    @Test
    public void testFetchResultQuery() {
        // Given
        var pipeline = Mockito.mock(Pipeline.class);
        var mediator = new Mediator(pipeline);
        var query = new ResultQuery();

        // When
        mediator.fetch(query);

        // Then
        Mockito.verify(pipeline, times(1)).send(query);
    }

    @Test
    public void testPublishDomainEvent() {
        // Given
        var pipeline = Mockito.mock(Pipeline.class);
        var mediator = new Mediator(pipeline);
        var domainEvent = new ExecutedDomainEvent();

        // When
        mediator.publish(domainEvent);

        // Then
        Mockito.verify(pipeline, times(1)).send(domainEvent);
    }

    @Test
    public void testPublishDomainEvents() {
        // Given
        var pipeline = Mockito.mock(Pipeline.class);
        var mediator = new Mediator(pipeline);
        var aggregate = new Aggregate(1);
        aggregate.execute();
        var domainEvents = aggregate.popDomainEvents();

        // When
        mediator.publish(domainEvents);

        // Then
        ArgumentCaptor<ExecutedDomainEvent> domainEventCaptor = ArgumentCaptor.forClass(ExecutedDomainEvent.class);
        Mockito.verify(pipeline, times(1)).send(domainEventCaptor.capture());
        assertThat(aggregate.getDomainEvents()).isEmpty();
    }
}

class ResultCommand implements ICommand<Integer> {
}

class ResultQuery implements IQuery<Integer> {
}

class ExecutedDomainEvent extends DomainEvent {

    @Override
    protected Iterable<Object> getEqualityComponents() {
        return List.of();
    }
}

class Aggregate extends AggregateRoot<Integer> {
    Aggregate(Integer id) {
        super(id);
    }

    public void execute() {
        addDomainEvent(new ExecutedDomainEvent());
    }
}