package porridge.my.way.dddarchitecturej.order.domain.models;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import porridge.my.way.dddarchitecturej.order.domain.events.OrderAddedDomainEvent;
import porridge.my.way.dddarchitecturej.order.domain.events.OrderCreatedDomainEvent;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderTests {
    @SneakyThrows
    @Test
    public void test_應該能夠成功建立() {
        // Given
        CustomerInfo customerInfo = CustomerInfo.create("name", "address");

        // When
        Order order = Order.create(customerInfo);

        // Then
        assertThat(order.getCustomerInfo()).isEqualTo(customerInfo);
        assertThat(order.getDomainEvents()).contains(new OrderCreatedDomainEvent(order.getId()));
    }

    @Test
    public void test_應該能夠加入orderItem() {
        // Given
        var order = createOrder();
        var orderItem = createOrderItem();

        // When
        order.add(orderItem);

        // Then
        assertThat(order.getOrderItems()).contains(orderItem);
        assertThat(order.getDomainEvents()).contains(new OrderAddedDomainEvent(order.getId()));
    }

    @SneakyThrows
    private Order createOrder() {
        return Order.create(CustomerInfo.create("name", "address"));
    }

    @SneakyThrows
    private OrderItem createOrderItem() {
        return OrderItem.create(1, new BigDecimal(1), 1);
    }
}
