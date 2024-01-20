package porridge.my.way.dddarchitecturej.order.controller.models;

import an.awesome.pipelinr.Voidy;
import lombok.Data;
import porridge.my.way.dddarchitecturej.architecture.shell.cqrs.ICommand;
import porridge.my.way.dddarchitecturej.order.application.commands.addOrderItem.AddOrderItemCommand;
import porridge.my.way.dddarchitecturej.order.domain.models.OrderItem;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class AddOrderItemRequest {
    private final int productId;
    private final BigDecimal price;
    private final int quantity;

    public ICommand<Voidy> toCommand(UUID orderId) {
        return new AddOrderItemCommand(orderId, OrderItem.create(productId, price, quantity));
    }
}
