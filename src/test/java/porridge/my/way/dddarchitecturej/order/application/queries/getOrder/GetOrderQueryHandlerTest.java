package porridge.my.way.dddarchitecturej.order.application.queries.getOrder;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import porridge.my.way.dddarchitecturej.architecture.shell.cqrs.IMediator;
import porridge.my.way.dddarchitecturej.order.application.commands.createOrder.CreateOrderCommand;
import porridge.my.way.dddarchitecturej.order.domain.models.CustomerInfo;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GetOrderQueryHandlerTest {
    @Autowired
    IMediator mediator;

    @SneakyThrows
    @Test
    void testGetOrderQuery() {
        String name = "name";
        String address = "address";

        CreateOrderCommand createOrderCommand = new CreateOrderCommand(CustomerInfo.create(name, address));
        UUID orderId = mediator.send(createOrderCommand);

        GetOrderQuery getOrderQuery = new GetOrderQuery(orderId);
        GetOrderOutcome outcomes = mediator.send(getOrderQuery);

        assertThat(outcomes.getId()).isEqualTo(orderId);
        assertThat(outcomes.getName()).isEqualTo(name);
        assertThat(outcomes.getAddress()).isEqualTo(address);
    }
}