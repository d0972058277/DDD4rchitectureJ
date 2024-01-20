package porridge.my.way.dddarchitecturej.order.application.commands.createOrder;

import org.springframework.stereotype.Component;
import porridge.my.way.dddarchitecturej.architecture.shell.cqrs.ICommandHandler;
import porridge.my.way.dddarchitecturej.architecture.shell.cqrs.IMediator;
import porridge.my.way.dddarchitecturej.order.application.repositories.IOrderRepository;
import porridge.my.way.dddarchitecturej.order.domain.models.Order;

import java.util.UUID;

@Component
public class CreateOrderCommandHandler implements ICommandHandler<CreateOrderCommand, UUID> {
    private final IOrderRepository repository;
    private final IMediator mediator;

    public CreateOrderCommandHandler(IOrderRepository repository, IMediator mediator) {
        this.repository = repository;
        this.mediator = mediator;
    }

    @Override
    public UUID handle(CreateOrderCommand command) {
        var order = Order.create(command.getCustomerInfo());
        repository.add(order);
        mediator.publish(order.popDomainEvents());
        return order.getId();
    }
}
