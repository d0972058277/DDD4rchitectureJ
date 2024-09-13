package porridge.my.way.dddarchitecturej.order.controller.models;

import an.awesome.pipelinr.Voidy;
import io.vavr.control.Try;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import porridge.my.way.dddarchitecturej.architecture.shell.cqrs.ICommand;
import porridge.my.way.dddarchitecturej.order.application.commands.addOrderItem.AddOrderItemCommand;
import porridge.my.way.dddarchitecturej.order.domain.models.OrderItem;
import porridge.my.way.dddarchitecturej.order.domain.models.Price;
import porridge.my.way.dddarchitecturej.order.domain.models.Quantity;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@FieldNameConstants
@AddOrderItemRequestConstraint
public class AddOrderItemRequest {
    private final int productId;
    private final BigDecimal price;
    private final int quantity;

    public Try<ICommand<Voidy>> toCommand(UUID orderId) {
        Try<Price> priceTry = Price.create(price);
        Try<Quantity> quantityTry = Quantity.create(quantity);

        if (priceTry.isFailure()) {
            return Try.failure(priceTry.getCause());
        }

        if (quantityTry.isFailure()) {
            return Try.failure(quantityTry.getCause());
        }

        return Try.success(new AddOrderItemCommand(
                        orderId,
                        OrderItem.create(
                                productId,
                                priceTry.get(),
                                quantityTry.get()
                        )
                )
        );
    }
}
