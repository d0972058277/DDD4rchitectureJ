package porridge.my.way.dddarchitecturej.order.infrastructure.repositories;

import java.math.BigDecimal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

import porridge.my.way.dddarchitecturej.UUIDCharConverter;
import porridge.my.way.dddarchitecturej.order.domain.models.CustomerInfo;
import porridge.my.way.dddarchitecturej.order.domain.models.Order;
import porridge.my.way.dddarchitecturej.order.domain.models.OrderItem;

public class OrderRepositoryTests {
    @Test
    public void test_Hibernate() {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAttributeConverter(UUIDCharConverter.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Order order = Order.create(CustomerInfo.create("name", "address"));
        order.add(OrderItem.create(1, new BigDecimal(1), 1));

        session.persist(order);

        transaction.commit();
        session.close();
    }
}
