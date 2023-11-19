package porridge.my.way.dddarchitecturej.order.domain.models;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Getter;
import porridge.my.way.dddarchitecturej.architecture.core.Entity;

public class OrderItem extends Entity<UUID> {
    @Getter
    private int productId;
    @Getter
    private BigDecimal price;
    @Getter
    private int quantity;

    private OrderItem(UUID id, int productId, BigDecimal price, int quantity) {
        super(id);
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    public static OrderItem create(int productId, BigDecimal price, int quantity) {
        if (price.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Price must be positive");
        if (quantity <= 0)
            throw new IllegalArgumentException("Quantity must be positive");

        UUID id = UUID.randomUUID();
        return new OrderItem(id, productId, price, quantity);
    }

    public BigDecimal getTotalPrice() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}
