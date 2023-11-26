package porridge.my.way.dddarchitecturej.order.infrastructure.models;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import porridge.my.way.dddarchitecturej.order.domain.models.Order;

@Entity
@Table(name = "Orders")
@Data
@Setter(AccessLevel.PRIVATE)
public class OrderDto {
    @Id
    @Column(columnDefinition = "CHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;
    private String name;
    private String address;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "order_id", nullable = false)
    private List<OrderItemDto> orderItems;

    protected OrderDto() {
    }

    public static OrderDto from(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setName(order.getCustomerInfo().getName());
        orderDto.setAddress(order.getCustomerInfo().getAddress());
        orderDto.setOrderItems(order.getOrderItems().stream()
                .map(orderItem -> OrderItemDto.from(orderItem)).toList());
        return orderDto;
    }
}
