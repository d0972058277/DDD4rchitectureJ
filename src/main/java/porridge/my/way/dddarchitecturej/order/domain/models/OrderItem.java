package porridge.my.way.dddarchitecturej.order.domain.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import porridge.my.way.dddarchitecturej.architecture.SequentialUUID;
import porridge.my.way.dddarchitecturej.architecture.core.Entity;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem extends Entity<UUID> {
    private int productId;
    private Price price;
    private Quantity quantity;

    private OrderItem(UUID id, int productId, Price price, Quantity quantity) {
        super(id);
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    public static OrderItem create(int productId, Price price, Quantity quantity) {
        UUID id = SequentialUUID.generateUUID();
        return new OrderItem(id, productId, price, quantity);
    }

    public BigDecimal getTotalPrice() {
        return price.getValue().multiply(BigDecimal.valueOf(quantity.getValue()));
    }
}
