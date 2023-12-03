package porridge.my.way.dddarchitecturej.order.infrastructure.repositories;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import porridge.my.way.dddarchitecturej.order.domain.models.CustomerInfo;
import porridge.my.way.dddarchitecturej.order.domain.models.Order;
import porridge.my.way.dddarchitecturej.order.domain.models.OrderItem;

public class OrderRepositoryTests {
    @Test
    public void test_Hibernate() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Order order = Order.create(CustomerInfo.create("name", "address"));
        order.add(OrderItem.create(1, new BigDecimal(1), 1));

        entityManager.persist(order);

        transaction.commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
