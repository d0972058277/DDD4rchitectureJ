package porridge.my.way.dddarchitecturej.order.application.commands.createOrder;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import porridge.my.way.dddarchitecturej.architecture.shell.cqrs.ICommand;
import porridge.my.way.dddarchitecturej.order.domain.models.CustomerInfo;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class CreateOrderCommand implements ICommand<UUID> {
    private final CustomerInfo customerInfo;
}
