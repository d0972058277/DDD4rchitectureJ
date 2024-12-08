package porridge.my.way.dddarchitecturej.order.domain.models;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderItemTests {
    @SneakyThrows
    private static OrderItem createOrderItem(int productId, Price price, Quantity quantity) {
        return OrderItem.create(productId, price, quantity);
    }

    @Test
    public void test_應該能夠成功建立() {
        // Given
        int productId = 1;
        Price price = Price.create(BigDecimal.ONE).get();
        Quantity quantity = Quantity.create(1).get();

        // When
        OrderItem orderItem = createOrderItem(productId, price, quantity);

        // Then
        assertThat(orderItem.getProductId()).isEqualTo(productId);
        assertThat(orderItem.getPrice()).isEqualTo(price);
        assertThat(orderItem.getQuantity()).isEqualTo(quantity);
    }

    @Test
    public void test_應該能夠取得總金額() {
        // Given
        int productId = 1;
        Price price = Price.create(BigDecimal.ONE).get();
        Quantity quantity = Quantity.create(1).get();
        OrderItem orderItem = createOrderItem(productId, price, quantity);

        // When
        BigDecimal totalPrice = orderItem.getTotalPrice();

        // Then
        assertThat(totalPrice).isEqualTo(new BigDecimal(1));
    }
}
