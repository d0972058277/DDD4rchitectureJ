package porridge.my.way.dddarchitecturej.order.controller.models;

import io.vavr.control.Try;
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

    public Try<ICommand<UUID>> toCommand() throws IllegalArgumentDomainException {
        Try<CustomerInfo> customerInfoTry = CustomerInfo.create(name, address);
        if (customerInfoTry.isFailure()) {
            return Try.failure(customerInfoTry.getCause());
        }

        return Try.success(new CreateOrderCommand(customerInfoTry.get()));
    }
}
