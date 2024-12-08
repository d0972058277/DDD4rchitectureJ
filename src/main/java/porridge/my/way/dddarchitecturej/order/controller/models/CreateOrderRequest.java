package porridge.my.way.dddarchitecturej.order.controller.models;

import io.vavr.control.Try;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import porridge.my.way.dddarchitecturej.order.application.commands.createOrder.CreateOrderCommand;
import porridge.my.way.dddarchitecturej.order.domain.models.CustomerInfo;

@Data
@FieldNameConstants
@CreateOrderRequestConstraint
public class CreateOrderRequest {
    public final String name;
    public final String address;

    public Try<CreateOrderCommand> toCommand() {
        Try<CustomerInfo> customerInfoTry = CustomerInfo.create(name, address);
        return customerInfoTry.isSuccess() ?
                Try.success(new CreateOrderCommand(customerInfoTry.get())) :
                Try.failure(customerInfoTry.getCause());
    }
}
