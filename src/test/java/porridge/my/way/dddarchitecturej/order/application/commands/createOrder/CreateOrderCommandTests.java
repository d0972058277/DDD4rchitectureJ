package porridge.my.way.dddarchitecturej.order.application.commands.createOrder;

import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import jakarta.persistence.EntityManager;
import porridge.my.way.dddarchitecturej.architecture.shell.cqrs.IMediator;
import porridge.my.way.dddarchitecturej.order.application.repositories.IOrderRepository;
import porridge.my.way.dddarchitecturej.order.domain.models.CustomerInfo;
import porridge.my.way.dddarchitecturej.order.domain.models.Order;

@SpringBootTest
public class CreateOrderCommandTests {
    @MockBean
    EntityManager entityManager;
    @Autowired
    IOrderRepository repository;
    @Autowired
    IMediator mediator;

    @Test
    public void testCreateOrderCommand() {
        // Given
        CreateOrderCommand createOrderCommand = new CreateOrderCommand(CustomerInfo.create("name", "address"));

        // When
        mediator.execute(createOrderCommand);

        // Then
        Mockito.verify(entityManager, times(1)).persist(Mockito.any(Order.class));
    }
}
