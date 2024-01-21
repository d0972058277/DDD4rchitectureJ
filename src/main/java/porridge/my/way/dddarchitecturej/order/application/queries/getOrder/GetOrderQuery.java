package porridge.my.way.dddarchitecturej.order.application.queries.getOrder;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import porridge.my.way.dddarchitecturej.architecture.shell.cqrs.IQuery;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class GetOrderQuery implements IQuery<GetOrderOutcome> {
    private final UUID orderId;
}
