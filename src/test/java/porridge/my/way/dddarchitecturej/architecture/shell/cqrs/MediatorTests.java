package porridge.my.way.dddarchitecturej.architecture.shell.cqrs;

import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import an.awesome.pipelinr.Pipeline;
import porridge.my.way.dddarchitecturej.architecture.core.IDomainEvent;

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
        var domainEvent = new DomainEvent();

        // When
        mediator.publish(domainEvent);

        // Then
        Mockito.verify(pipeline, times(1)).send(domainEvent);
    }
}

class NoneCommand implements INoneCommand {
}

class ResultCommand implements ICommand<Integer> {
}

class ResultQuery implements IQuery<Integer> {
}

class DomainEvent implements IDomainEvent {
}