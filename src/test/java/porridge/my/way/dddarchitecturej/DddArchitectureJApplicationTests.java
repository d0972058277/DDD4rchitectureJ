package porridge.my.way.dddarchitecturej;

import java.math.BigDecimal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import porridge.my.way.dddarchitecturej.order.domain.models.CustomerInfo;
import porridge.my.way.dddarchitecturej.order.domain.models.Order;
import porridge.my.way.dddarchitecturej.order.domain.models.OrderItem;
import porridge.my.way.dddarchitecturej.order.infrastructure.models.OrderDto;

@SpringBootTest
class DddArchitectureJApplicationTests {

	@Test
	void contextLoads() {
		Configuration configuration = new Configuration();
		configuration.configure();

		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		Order order = Order.create(CustomerInfo.create("name", "address"));
		order.add(OrderItem.create(1, new BigDecimal(1), 1));

		OrderDto orderDto = OrderDto.from(order);
		session.persist(orderDto);

		transaction.commit();
		session.close();
	}

}
