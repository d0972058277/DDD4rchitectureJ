package porridge.my.way.dddarchitecturej.architecture.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SampleDomainEvent implements IDomainEvent {
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
}
