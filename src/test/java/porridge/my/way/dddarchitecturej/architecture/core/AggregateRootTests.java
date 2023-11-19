package porridge.my.way.dddarchitecturej.architecture.core;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

class SampleDomainEvent extends DomainEvent {

    @Override
    protected Iterable<Object> getEqualityComponents() {
        return List.of();
    }
}

class SampleAggregateRoot extends AggregateRoot<Integer> {
    SampleAggregateRoot(Integer id) {
        super(id);
    }
}

public class AggregateRootTests {
    @Test
    public void testAddDomainEvent() {
        // Given
        var domainEvent = new SampleDomainEvent();
        var aggregate = new SampleAggregateRoot(1);

        // When
        aggregate.addDomainEvent(domainEvent);

        // Then
        assertThat(aggregate.domainEvents.size()).isEqualTo(1);
        assertThat(aggregate.domainEvents.get(0)).isEqualTo(domainEvent);
    }

    @Test
    public void testClearDomainEvents() {
        // Given
        var domainEvent = new SampleDomainEvent();
        var aggregate = new SampleAggregateRoot(1);
        aggregate.addDomainEvent(domainEvent);

        // When
        aggregate.clearDomainEvents();

        // Then
        assertThat(aggregate.domainEvents.size()).isZero();
    }

    @Test
    public void testPopDomainEvents() {
        // Given
        var domainEvent = new SampleDomainEvent();
        var aggregate = new SampleAggregateRoot(1);
        aggregate.addDomainEvent(domainEvent);

        // When
        var domainEvents = aggregate.popDomainEvents();

        // Then
        assertThat(domainEvents.size()).isEqualTo(1);
        assertThat(domainEvents).contains(domainEvent);
        assertThat(aggregate.domainEvents.size()).isZero();
    }
}
