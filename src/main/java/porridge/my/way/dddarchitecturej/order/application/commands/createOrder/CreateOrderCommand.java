package porridge.my.way.dddarchitecturej.order.application.commands.createOrder;

import lombok.Getter;
import porridge.my.way.dddarchitecturej.architecture.shell.cqrs.ICommand;
import porridge.my.way.dddarchitecturej.order.domain.models.CustomerInfo;

import java.util.UUID;

@Getter
public class CreateOrderCommand implements ICommand<UUID> {
    private final CustomerInfo customerInfo;

    public CreateOrderCommand(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }
}
