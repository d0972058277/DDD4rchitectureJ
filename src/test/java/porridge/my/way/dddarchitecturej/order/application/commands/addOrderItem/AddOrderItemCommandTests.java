package porridge.my.way.dddarchitecturej.order.application.commands.addOrderItem;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import porridge.my.way.dddarchitecturej.architecture.shell.cqrs.IMediator;
import porridge.my.way.dddarchitecturej.order.application.repositories.IOrderRepository;
import porridge.my.way.dddarchitecturej.order.domain.models.CustomerInfo;
import porridge.my.way.dddarchitecturej.order.domain.models.Order;
import porridge.my.way.dddarchitecturej.order.domain.models.OrderItem;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AddOrderItemCommandTests {
    @MockBean
    EntityManager entityManager;
    @Autowired
    IOrderRepository repository;
    @Autowired
    IMediator mediator;

    @Test
    public void testAddOrderItemCommand() {
        // Given
        UUID orderId = givenAnExistingOrder();
        OrderItem orderItem = createAnOrderItem();
        AddOrderItemCommand addOrderItemCommand = new AddOrderItemCommand(orderId, orderItem);

        // When
        mediator.send(addOrderItemCommand);

        // Then
        Order order = repository.find(orderId);
        assertThat(order.getOrderItems().contains(orderItem)).isTrue();
    }

    private OrderItem createAnOrderItem() {
        OrderItem orderItem = OrderItem.create(1, new BigDecimal(1), 1);
        return orderItem;
    }

    private UUID givenAnExistingOrder() {
        Order order = getOrder();
        Mockito.when(repository.find(order.getId())).thenReturn(order);
        return order.getId();
    }

    private Order getOrder() {
        Order order = Order.create(CustomerInfo.create("name", "address"));
        order.clearDomainEvents();
        return order;
    }
}
