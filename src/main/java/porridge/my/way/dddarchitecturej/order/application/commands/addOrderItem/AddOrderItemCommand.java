package porridge.my.way.dddarchitecturej.order.application.commands.addOrderItem;

import java.util.UUID;

import an.awesome.pipelinr.Voidy;
import lombok.Getter;
import porridge.my.way.dddarchitecturej.architecture.shell.cqrs.ICommand;
import porridge.my.way.dddarchitecturej.order.domain.models.OrderItem;

public class AddOrderItemCommand implements ICommand<Voidy> {
    @Getter
    private UUID orderId;

    @Getter
    private OrderItem orderItem;

    public AddOrderItemCommand(UUID orderId, OrderItem orderItem) {
        this.orderId = orderId;
        this.orderItem = orderItem;
    }
}
