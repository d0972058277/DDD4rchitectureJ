package porridge.my.way.dddarchitecturej.order.application.commands.addOrderItem;

import an.awesome.pipelinr.Voidy;
import lombok.Getter;
import porridge.my.way.dddarchitecturej.architecture.shell.cqrs.ICommand;
import porridge.my.way.dddarchitecturej.order.domain.models.OrderItem;

import java.util.UUID;

@Getter
public class AddOrderItemCommand implements ICommand<Voidy> {
    private final UUID orderId;

    private final OrderItem orderItem;

    public AddOrderItemCommand(UUID orderId, OrderItem orderItem) {
        this.orderId = orderId;
        this.orderItem = orderItem;
    }
}
