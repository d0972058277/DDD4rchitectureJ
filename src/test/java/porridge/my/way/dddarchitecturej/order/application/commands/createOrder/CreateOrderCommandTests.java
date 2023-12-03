package porridge.my.way.dddarchitecturej.order.application.commands.createOrder;

import static org.mockito.Mockito.times;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import porridge.my.way.dddarchitecturej.architecture.shell.cqrs.IMediator;
import porridge.my.way.dddarchitecturej.order.application.command.createOrder.CreateOrderCommand;
import porridge.my.way.dddarchitecturej.order.application.command.createOrder.CreateOrderCommandHandler;
import porridge.my.way.dddarchitecturej.order.application.repositories.IOrderRepository;
import porridge.my.way.dddarchitecturej.order.domain.events.OrderCreatedDomainEvent;
import porridge.my.way.dddarchitecturej.order.domain.models.CustomerInfo;
import porridge.my.way.dddarchitecturej.order.domain.models.Order;

// todo: command 應該能夠使用整合測試
@ExtendWith(MockitoExtension.class)
public class CreateOrderCommandTests {
    @Mock
    private IOrderRepository repository;
    @Mock
    private IMediator mediator;

    @Test
    public void testCreateOrderCommand() {
        CreateOrderCommand createOrderCommand = new CreateOrderCommand(CustomerInfo.create("name", "address"));
        CreateOrderCommandHandler createOrderCommandHandler = new CreateOrderCommandHandler(repository, mediator);

        // When
        var orderId = createOrderCommandHandler.handle(createOrderCommand);

        // Then
        Mockito.verify(repository, times(1)).add(Mockito.any(Order.class));
        Mockito.verify(mediator, times(1)).publish(List.of(new OrderCreatedDomainEvent(orderId)));
    }
}
