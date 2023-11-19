package porridge.my.way.dddarchitecturej.order.application.command.addOrderItem;

import an.awesome.pipelinr.Voidy;
import porridge.my.way.dddarchitecturej.architecture.shell.cqrs.ICommandHandler;
import porridge.my.way.dddarchitecturej.architecture.shell.cqrs.IMediator;
import porridge.my.way.dddarchitecturej.order.application.repositories.IOrderRepository;
import porridge.my.way.dddarchitecturej.order.domain.models.Order;

public class AddOrderItemCommandHandler implements ICommandHandler<AddOrderItemCommand, Voidy> {
    private IOrderRepository repository;
    private IMediator mediator;

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
