package porridge.my.way.dddarchitecturej.order.application.commands.addOrderItem;

import static org.mockito.Mockito.times;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import porridge.my.way.dddarchitecturej.architecture.shell.cqrs.IMediator;
import porridge.my.way.dddarchitecturej.order.application.command.addOrderItem.AddOrderItemCommand;
import porridge.my.way.dddarchitecturej.order.application.repositories.IOrderRepository;
import porridge.my.way.dddarchitecturej.order.domain.models.CustomerInfo;
import porridge.my.way.dddarchitecturej.order.domain.models.Order;
import porridge.my.way.dddarchitecturej.order.domain.models.OrderItem;

@SpringBootTest
public class AddOrderItemCommandTests {
    @MockBean
    IOrderRepository repository;
    @Autowired
    IMediator mediator;

    @Test
    public void testAddOrderItemCommand() {
        // Given
        Order order = getOrder();
        OrderItem orderItem = OrderItem.create(1, new BigDecimal(1), 1);
        Mockito.when(repository.find(order.getId())).thenReturn(order);

        AddOrderItemCommand addOrderItemCommand = new AddOrderItemCommand(order.getId(), orderItem);

        // When
        mediator.execute(addOrderItemCommand);

        // Then
        Mockito.verify(repository, times(1)).find(order.getId());
        Mockito.verify(repository, times(1)).save(order);
    }

    private Order getOrder() {
        Order order = Order.create(CustomerInfo.create("name", "address"));
        order.clearDomainEvents();
        return order;
    }
}
