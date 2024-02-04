package porridge.my.way.dddarchitecturej.order.infrastructure.repositories;

import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import porridge.my.way.dddarchitecturej.PorriDG3Inject;
import porridge.my.way.dddarchitecturej.PorriDG3Test;
import porridge.my.way.dddarchitecturej.order.domain.models.CustomerInfo;
import porridge.my.way.dddarchitecturej.order.domain.models.Order;
import porridge.my.way.dddarchitecturej.order.domain.models.OrderItem;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@PorriDG3Test
public class OrderRepositoryTests {
    @PorriDG3Inject
    private OrderRepository orderRepository;

    @SneakyThrows
    @Test
    @Transactional
    public void test_Hibernate() {
        Order order = Order.create(CustomerInfo.create("name", "address"));
        order.add(OrderItem.create(1, new BigDecimal(1), 1));
        orderRepository.add(order);
        Order orderSaved = orderRepository.find(order.getId());
        assertThat(orderSaved).isEqualTo(order);

//        orderSaved.add(OrderItem.create(1, new BigDecimal(1), 1));
//        orderRepository.save(orderSaved);
//        Order orderAdjusted = orderRepository.find(order.getId());
//        assertThat(orderAdjusted.getOrderItems().size()).isEqualTo(2);
//
//        orderRepository.remove(orderSaved);
//        Order orderDeleted = orderRepository.find(order.getId());
//        assertThat(orderDeleted).isNull();
    }
}
