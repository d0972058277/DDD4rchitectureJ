package porridge.my.way.dddarchitecturej.order.infrastructure.repositories;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import porridge.my.way.dddarchitecturej.order.application.repositories.IOrderRepository;
import porridge.my.way.dddarchitecturej.order.domain.models.Order;

@Repository
public class OrderRepository implements IOrderRepository {
    EntityManager entityManager;

    public OrderRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Order find(UUID orderId) {
        return entityManager.find(Order.class, orderId);
    }

    @Override
    public void add(Order order) {
        entityManager.persist(order);
    }

    @Override
    public void save(Order order) {
        entityManager.merge(order);
    }

    @Override
    public void remove(Order order) {
        entityManager.remove(order);
    }

}
