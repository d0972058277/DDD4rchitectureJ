package porridge.my.way.dddarchitecturej.order.domain.models;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import porridge.my.way.dddarchitecturej.architecture.exceptions.IllegalArgumentDomainException;

import java.math.BigDecimal;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OrderItemTests {
    @SneakyThrows
    private static OrderItem createOrderItem(int productId, BigDecimal price, int quantity) {
        return OrderItem.create(productId, price, quantity);
    }

    @Test
    public void test_應該能夠成功建立() {
        // Given
        int productId = 1;
        BigDecimal price = new BigDecimal(1);
        int quantity = 1;

        // When
        OrderItem orderItem = createOrderItem(productId, price, quantity);

        // Then
        assertThat(orderItem.getProductId()).isEqualTo(productId);
        assertThat(orderItem.getPrice()).isEqualTo(price);
        assertThat(orderItem.getQuantity()).isEqualTo(quantity);
    }

    @Test
    public void test_price為0_建立時應該拋出例外() {
        // Given
        int productId = 1;
        BigDecimal price = new BigDecimal(0);
        int quantity = 1;

        // When
        Supplier<OrderItem> supplier = () -> createOrderItem(productId, price, quantity);

        // Then
        assertThatThrownBy(() -> supplier.get()).isInstanceOf(IllegalArgumentDomainException.class);
    }

    @Test
    public void test_quantity為0_建立時應該拋出例外() {
        // Given
        int productId = 1;
        BigDecimal price = new BigDecimal(1);
        int quantity = 0;

        // When
        Supplier<OrderItem> supplier = () -> createOrderItem(productId, price, quantity);

        // Then
        assertThatThrownBy(() -> supplier.get()).isInstanceOf(IllegalArgumentDomainException.class);
    }

    @Test
    public void test_應該能夠取得總金額() {
        // Given
        int productId = 1;
        BigDecimal price = new BigDecimal(1);
        int quantity = 1;
        OrderItem orderItem = createOrderItem(productId, price, quantity);

        // When
        BigDecimal totalPrice = orderItem.getTotalPrice();

        // Then
        assertThat(totalPrice).isEqualTo(new BigDecimal(1));
    }
}
