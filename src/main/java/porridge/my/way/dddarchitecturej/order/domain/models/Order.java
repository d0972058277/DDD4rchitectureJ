package porridge.my.way.dddarchitecturej.order.domain.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import porridge.my.way.dddarchitecturej.architecture.core.AggregateRoot;
import porridge.my.way.dddarchitecturej.order.domain.events.OrderAddedDomainEvent;
import porridge.my.way.dddarchitecturej.order.domain.events.OrderCreatedDomainEvent;

public class Order extends AggregateRoot<UUID> {
    @Getter
    private CustomerInfo customerInfo;
    private List<OrderItem> orderItems;

    public List<OrderItem> getOrderItems() {
        return Collections.unmodifiableList(orderItems);
    }

    private Order(UUID id, CustomerInfo customerInfo, List<OrderItem> orderItems) {
        super(id);
        this.customerInfo = customerInfo;
        this.orderItems = orderItems;
    }

    public static Order create(CustomerInfo customerInfo) {
        UUID id = UUID.randomUUID();
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        Order order = new Order(id, customerInfo, orderItems);
        order.addDomainEvent(new OrderCreatedDomainEvent(id));
        return order;
    }

    public void add(OrderItem orderItem) {
        orderItems.add(orderItem);
        addDomainEvent(new OrderAddedDomainEvent(id));
    }
}
