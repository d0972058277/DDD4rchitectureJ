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

	}
}
