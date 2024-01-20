package porridge.my.way.dddarchitecturej.order.domain.events;

import lombok.Getter;
import porridge.my.way.dddarchitecturej.architecture.core.DomainEvent;

import java.util.List;
import java.util.UUID;

@Getter
public class OrderCreatedDomainEvent extends DomainEvent {
    private final UUID orderId;

    public OrderCreatedDomainEvent(UUID orderId) {
        this.orderId = orderId;
    }

    @Override
    protected Iterable<Object> getEqualityComponents() {
        return List.of(orderId);
    }
}
