package porridge.my.way.dddarchitecturej.order.domain.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import porridge.my.way.dddarchitecturej.architecture.SequentialUUID;
import porridge.my.way.dddarchitecturej.architecture.core.Entity;
import porridge.my.way.dddarchitecturej.architecture.exceptions.IllegalArgumentDomainException;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem extends Entity<UUID> {
    private int productId;
    private BigDecimal price;
    private int quantity;

    private OrderItem(UUID id, int productId, BigDecimal price, int quantity) {
        super(id);
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    public static OrderItem create(int productId, BigDecimal price, int quantity) throws IllegalArgumentDomainException {
        if (price.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentDomainException("Price must be positive");
        if (quantity <= 0)
            throw new IllegalArgumentDomainException("Quantity must be positive");

        UUID id = SequentialUUID.generateUUID();
        return new OrderItem(id, productId, price, quantity);
    }

    public BigDecimal getTotalPrice() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}
