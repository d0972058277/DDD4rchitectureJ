package porridge.my.way.dddarchitecturej.order.application.commands.addOrderItem;

import static org.mockito.Mockito.times;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import porridge.my.way.dddarchitecturej.architecture.shell.cqrs.IMediator;
import porridge.my.way.dddarchitecturej.order.application.command.addOrderItem.AddOrderItemCommand;
import porridge.my.way.dddarchitecturej.order.application.command.addOrderItem.AddOrderItemCommandHandler;
import porridge.my.way.dddarchitecturej.order.application.repositories.IOrderRepository;
import porridge.my.way.dddarchitecturej.order.domain.events.OrderAddedDomainEvent;
import porridge.my.way.dddarchitecturej.order.domain.models.CustomerInfo;
import porridge.my.way.dddarchitecturej.order.domain.models.Order;
import porridge.my.way.dddarchitecturej.order.domain.models.OrderItem;

@ExtendWith(MockitoExtension.class)
public class AddOrderItemCommandTests {
    @Mock
    IOrderRepository repository;
    @Mock
    IMediator mediator;

    @Test
    public void testAddOrderItemCommand() {
        // Given
        Order order = getOrder();
        OrderItem orderItem = OrderItem.create(1, new BigDecimal(1), 1);
        Mockito.when(repository.find(order.getId())).thenReturn(order);

        AddOrderItemCommand addOrderItemCommand = new AddOrderItemCommand(order.getId(), orderItem);
        AddOrderItemCommandHandler addOrderItemCommandHandler = new AddOrderItemCommandHandler(repository, mediator);

        // When
        addOrderItemCommandHandler.handle(addOrderItemCommand);

        // Then
        Mockito.verify(repository, times(1)).find(order.getId());
        Mockito.verify(repository, times(1)).save(order);
        Mockito.verify(mediator, times(1)).publish(List.of(new OrderAddedDomainEvent(order.getId())));
    }

    private Order getOrder() {
        Order order = Order.create(CustomerInfo.create("name", "address"));
        order.clearDomainEvents();
        return order;
    }
}
