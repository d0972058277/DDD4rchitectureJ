package porridge.my.way.dddarchitecturej.order.application.queries.listOrderItems;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import porridge.my.way.dddarchitecturej.architecture.shell.cqrs.IMediator;
import porridge.my.way.dddarchitecturej.order.application.commands.addOrderItem.AddOrderItemCommand;
import porridge.my.way.dddarchitecturej.order.application.commands.createOrder.CreateOrderCommand;
import porridge.my.way.dddarchitecturej.order.domain.models.CustomerInfo;
import porridge.my.way.dddarchitecturej.order.domain.models.OrderItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ListOrderItemsQueryHandlerTest {
    @Autowired
    IMediator mediator;

    @SneakyThrows
    @Test
    void testListOrderItems() {
        CreateOrderCommand createOrderCommand = new CreateOrderCommand(CustomerInfo.create("name", "address"));
        UUID orderId = mediator.send(createOrderCommand);

        AddOrderItemCommand addOrderItemCommand = new AddOrderItemCommand(orderId, OrderItem.create(1, BigDecimal.valueOf(1), 1));
        mediator.send(addOrderItemCommand);

        ListOrderItemsQuery listOrderItemsQuery = new ListOrderItemsQuery(orderId);
        List<ListOrderItemsOutcome> outcomes = mediator.send(listOrderItemsQuery);

        assertThat(outcomes)
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
                .contains(new ListOrderItemsOutcome(null, 1, 1, 1));
    }
}