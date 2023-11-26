package porridge.my.way.dddarchitecturej.order.infrastructure.models;

import java.math.BigDecimal;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import porridge.my.way.dddarchitecturej.order.domain.models.OrderItem;

@Entity
@Table(name = "OrderItems")
@Data
@Setter(AccessLevel.PRIVATE)
public class OrderItemDto {
    @Id
    @Column(columnDefinition = "CHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;
    private int productId;
    private BigDecimal price;
    private int quantity;

    protected OrderItemDto() {
    }

    public static OrderItemDto from(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setProductId(orderItem.getProductId());
        orderItemDto.setPrice(orderItem.getPrice());
        orderItemDto.setQuantity(orderItem.getQuantity());
        return orderItemDto;
    }
}
