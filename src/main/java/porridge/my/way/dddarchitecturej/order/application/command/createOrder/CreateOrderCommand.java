package porridge.my.way.dddarchitecturej.order.application.command.createOrder;

import java.util.UUID;

import lombok.Getter;
import porridge.my.way.dddarchitecturej.architecture.shell.cqrs.ICommand;
import porridge.my.way.dddarchitecturej.order.domain.models.CustomerInfo;

public class CreateOrderCommand implements ICommand<UUID> {
    @Getter
    private CustomerInfo customerInfo;

    public CreateOrderCommand(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }
}
