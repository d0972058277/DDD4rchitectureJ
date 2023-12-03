package porridge.my.way.dddarchitecturej.order.infrastructure.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import jakarta.transaction.Transactional;
import porridge.my.way.dddarchitecturej.order.application.repositories.IOrderRepository;
import porridge.my.way.dddarchitecturej.order.domain.models.CustomerInfo;
import porridge.my.way.dddarchitecturej.order.domain.models.Order;
import porridge.my.way.dddarchitecturej.order.domain.models.OrderItem;

@SpringBootTest
public class OrderRepositoryTests {
    @Autowired
    private IOrderRepository orderRepository;

    @Test
    @Transactional
    @Commit
    public void test_Hibernate() {
        Order order = Order.create(CustomerInfo.create("name", "address"));
        order.add(OrderItem.create(1, new BigDecimal(1), 1));

        orderRepository.add(order);

        Order orderSaved = orderRepository.find(order.getId());
        assertThat(orderSaved).isEqualTo(order);

        orderSaved.add(OrderItem.create(1, new BigDecimal(1), 1));
        orderRepository.save(orderSaved);

        Order orderAdjusted = orderRepository.find(order.getId());
        assertThat(orderAdjusted.getOrderItems().size()).isEqualTo(2);
    }
}
