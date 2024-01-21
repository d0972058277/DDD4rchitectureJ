package porridge.my.way.dddarchitecturej.order.application.queries.listOrderItems;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import porridge.my.way.dddarchitecturej.architecture.shell.cqrs.IQuery;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class ListOrderItemsQuery implements IQuery<List<ListOrderItemsOutcome>> {
    public final UUID orderId;
}
