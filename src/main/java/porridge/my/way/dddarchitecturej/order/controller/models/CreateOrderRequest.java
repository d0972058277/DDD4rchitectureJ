package porridge.my.way.dddarchitecturej.order.controller.models;

import lombok.Data;
import porridge.my.way.dddarchitecturej.architecture.exceptions.IllegalArgumentDomainException;
import porridge.my.way.dddarchitecturej.architecture.shell.cqrs.ICommand;
import porridge.my.way.dddarchitecturej.order.application.commands.createOrder.CreateOrderCommand;
import porridge.my.way.dddarchitecturej.order.domain.models.CustomerInfo;

import java.util.UUID;

@Data
@CreateOrderRequestConstraint
public class CreateOrderRequest {
    public final String name;
    public final String address;

    public ICommand<UUID> toCommand() throws IllegalArgumentDomainException {
        return new CreateOrderCommand(CustomerInfo.create(name, address));
    }
}
