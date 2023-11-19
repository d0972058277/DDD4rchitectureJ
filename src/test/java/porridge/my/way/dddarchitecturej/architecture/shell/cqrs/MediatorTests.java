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
    public void testExecuteNoneCommand() {
        // Given
        var pipeline = Mockito.mock(Pipeline.class);
        var mediator = new Mediator(pipeline);
        var command = new NoneCommand();

        // When
        mediator.execute(command);

        // Then
        Mockito.verify(pipeline, times(1)).send(command);
    }

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
        var domainEvent = new DomainEventForTest();

        // When
        mediator.publish(domainEvent);

        // Then
        Mockito.verify(pipeline, times(1)).send(domainEvent);
    }

    @Test
    public void testPublishAggregate() {
        // Given
        var pipeline = Mockito.mock(Pipeline.class);
        var mediator = new Mediator(pipeline);
        var aggregate = new Aggregate(1);
        aggregate.execute();

        // When
        mediator.publishAndClearDomainEvents(aggregate);

        // Then
        ArgumentCaptor<DomainEventForTest> domainEventCaptor = ArgumentCaptor.forClass(DomainEventForTest.class);
        Mockito.verify(pipeline, times(1)).send(domainEventCaptor.capture());
        assertThat(aggregate.getDomainEvents()).isEmpty();
    }
}

class NoneCommand implements INoneCommand {
}

class ResultCommand implements ICommand<Integer> {
}

class ResultQuery implements IQuery<Integer> {
}

class DomainEventForTest extends DomainEvent {

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
        addDomainEvent(new DomainEventForTest());
    }
}