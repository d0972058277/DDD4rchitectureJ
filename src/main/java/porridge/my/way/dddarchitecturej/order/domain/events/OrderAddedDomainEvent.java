package porridge.my.way.dddarchitecturej.order.domain.events;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import porridge.my.way.dddarchitecturej.architecture.core.DomainEvent;

public class OrderAddedDomainEvent extends DomainEvent {
    @Getter
    private UUID orderId;

    public OrderAddedDomainEvent(UUID orderId) {
        this.orderId = orderId;
    }

    @Override
    protected Iterable<Object> getEqualityComponents() {
        return List.of(orderId);
    }
}
