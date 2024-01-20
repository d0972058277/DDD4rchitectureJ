package porridge.my.way.dddarchitecturej.order.application.commands.addOrderItem;

import an.awesome.pipelinr.Voidy;
import org.springframework.stereotype.Component;
import porridge.my.way.dddarchitecturej.architecture.shell.cqrs.ICommandHandler;
import porridge.my.way.dddarchitecturej.architecture.shell.cqrs.IMediator;
import porridge.my.way.dddarchitecturej.order.application.repositories.IOrderRepository;
import porridge.my.way.dddarchitecturej.order.domain.models.Order;

@Component
public class AddOrderItemCommandHandler implements ICommandHandler<AddOrderItemCommand, Voidy> {
    private final IOrderRepository repository;
    private final IMediator mediator;

    public AddOrderItemCommandHandler(IOrderRepository repository, IMediator mediator) {
        this.repository = repository;
        this.mediator = mediator;
    }

    @Override
    public Voidy handle(AddOrderItemCommand command) {
        Order order = repository.find(command.getOrderId());
        order.add(command.getOrderItem());
        repository.save(order);
        mediator.publish(order.popDomainEvents());
        return new Voidy();
    }
}
