package porridge.my.way.dddarchitecturej.order.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

public class OrderItemTests {
    @Test
    public void test_應該能夠成功建立() {
        // Given
        int productId = 1;
        BigDecimal price = new BigDecimal(1);
        int quantity = 1;

        // When
        OrderItem orderItem = OrderItem.create(productId, price, quantity);

        // Then
        assertThat(orderItem.productId).isEqualTo(productId);
        assertThat(orderItem.price).isEqualTo(price);
        assertThat(orderItem.quantity).isEqualTo(quantity);
    }

    @Test
    public void test_price為0_建立時應該拋出例外() {
        // Given
        int productId = 1;
        BigDecimal price = new BigDecimal(0);
        int quantity = 1;

        // When
        Supplier<OrderItem> supplier = () -> OrderItem.create(productId, price, quantity);

        // Then
        assertThatThrownBy(() -> supplier.get()).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void test_quantity為0_建立時應該拋出例外() {
        // Given
        int productId = 1;
        BigDecimal price = new BigDecimal(1);
        int quantity = 0;

        // When
        Supplier<OrderItem> supplier = () -> OrderItem.create(productId, price, quantity);

        // Then
        assertThatThrownBy(() -> supplier.get()).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void test_應該能夠取得總金額() {
        // Given
        int productId = 1;
        BigDecimal price = new BigDecimal(1);
        int quantity = 1;
        OrderItem orderItem = OrderItem.create(productId, price, quantity);

        // When
        BigDecimal totalPrice = orderItem.getTotalPrice();

        // Then
        assertThat(totalPrice).isEqualTo(new BigDecimal(1));
    }
}
