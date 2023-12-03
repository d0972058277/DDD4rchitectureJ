package porridge.my.way.dddarchitecturej.order.domain.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import porridge.my.way.dddarchitecturej.architecture.SequentialUUID;
import porridge.my.way.dddarchitecturej.architecture.core.AggregateRoot;
import porridge.my.way.dddarchitecturej.order.domain.events.OrderAddedDomainEvent;
import porridge.my.way.dddarchitecturej.order.domain.events.OrderCreatedDomainEvent;

@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends AggregateRoot<UUID> {
    @Getter
    @Embedded
    private CustomerInfo customerInfo;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "order_id", nullable = false)
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
        UUID id = SequentialUUID.generateUUID();
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        Order order = new Order(id, customerInfo, orderItems);
        order.addDomainEvent(new OrderCreatedDomainEvent(id));
        return order;
    }

    public static Order restore(UUID id, CustomerInfo customerInfo, List<OrderItem> orderItems) {
        return new Order(id, customerInfo, orderItems);
    }

    public void add(OrderItem orderItem) {
        orderItems.add(orderItem);
        addDomainEvent(new OrderAddedDomainEvent(id));
    }
}
