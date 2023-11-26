package porridge.my.way.dddarchitecturej.order.infrastructure.models;

import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import porridge.my.way.dddarchitecturej.order.domain.models.Order;

@Entity
@Table(name = "`Order`")
@Getter
@Setter(AccessLevel.PRIVATE)
public class OrderDto {
    @Id
    @Column(columnDefinition = "CHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;
    private String name;
    private String address;

    protected OrderDto() {
    }

    public static OrderDto from(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setName(order.getCustomerInfo().getName());
        orderDto.setAddress(order.getCustomerInfo().getAddress());
        return orderDto;
    }
}
