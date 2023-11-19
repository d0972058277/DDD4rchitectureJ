package porridge.my.way.dddarchitecturej.order.application.commands.createOrder;

import static org.mockito.Mockito.times;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import porridge.my.way.dddarchitecturej.architecture.shell.cqrs.IMediator;
import porridge.my.way.dddarchitecturej.order.application.command.createOrder.CreateOrderCommand;
import porridge.my.way.dddarchitecturej.order.application.command.createOrder.CreateOrderCommandHandler;
import porridge.my.way.dddarchitecturej.order.application.repositories.IOrderRepository;
import porridge.my.way.dddarchitecturej.order.domain.events.OrderCreatedDomainEvent;
import porridge.my.way.dddarchitecturej.order.domain.models.CustomerInfo;
import porridge.my.way.dddarchitecturej.order.domain.models.Order;

public class CreateOrderCommandTests {
    @Test
    public void testCreateOrderCommand() {
        // Given
        IOrderRepository repository = Mockito.mock(IOrderRepository.class);
        IMediator mediator = Mockito.mock(IMediator.class);

        CreateOrderCommand createOrderCommand = new CreateOrderCommand(CustomerInfo.create("name", "address"));
        CreateOrderCommandHandler createOrderCommandHandler = new CreateOrderCommandHandler(repository, mediator);

        // When
        var orderId = createOrderCommandHandler.handle(createOrderCommand);

        // Then
        Mockito.verify(repository, times(1)).add(Mockito.any(Order.class));
        Mockito.verify(mediator, times(1)).publish(List.of(new OrderCreatedDomainEvent(orderId)));
    }
}
