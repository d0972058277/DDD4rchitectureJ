package porridge.my.way.dddarchitecturej.order.controller.models;

import an.awesome.pipelinr.Voidy;
import io.vavr.control.Try;
import lombok.Data;
import porridge.my.way.dddarchitecturej.architecture.exceptions.IllegalArgumentDomainException;
import porridge.my.way.dddarchitecturej.architecture.shell.cqrs.ICommand;
import porridge.my.way.dddarchitecturej.order.application.commands.addOrderItem.AddOrderItemCommand;
import porridge.my.way.dddarchitecturej.order.domain.models.OrderItem;
import porridge.my.way.dddarchitecturej.order.domain.models.Price;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AddOrderItemRequestConstraint
public class AddOrderItemRequest {
    private final int productId;
    private final BigDecimal price;
    private final int quantity;

    public ICommand<Voidy> toCommand(UUID orderId) throws IllegalArgumentDomainException {
        Try<Price> priceTry = Price.create(price);
        return new AddOrderItemCommand(orderId, OrderItem.create(productId, priceTry.get(), quantity));
    }

}
