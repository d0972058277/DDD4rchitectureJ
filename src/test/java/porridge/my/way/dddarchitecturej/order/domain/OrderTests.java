package porridge.my.way.dddarchitecturej.order.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class OrderTests {
    @Test
    public void test_應該能夠成功建立() {
        // Given
        CustomerInfo customerInfo = CustomerInfo.create("name", "address");

        // When
        Order order = Order.Create(customerInfo);

        // Then
        assertThat(order.getCustomerInfo()).isEqualTo(customerInfo);
    }

    @Test
    public void test_應該能夠加入orderItem() {
        // Given
        var order = createOrder();
        var orderItem = createOrderItem();

        // When
        order.Add(orderItem);

        // Then
        assertThat(order.getOrderItems()).contains(orderItem);
    }

    private Order createOrder() {
        return Order.Create(CustomerInfo.create("name", "address"));
    }

    private OrderItem createOrderItem() {
        return OrderItem.create(1, new BigDecimal(1), 1);
    }
}
