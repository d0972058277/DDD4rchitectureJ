package porridge.my.way.dddarchitecturej.order.infrastructure.repositories;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import porridge.my.way.dddarchitecturej.order.application.repositories.IOrderRepository;
import porridge.my.way.dddarchitecturej.order.domain.models.Order;
import porridge.my.way.dddarchitecturej.order.infrastructure.models.OrderDto;

@Repository
public class OrderRepository implements IOrderRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Order find(UUID orderId) {
        OrderDto orderDto = entityManager.find(OrderDto.class, orderId);
        return orderDto.toEntity();
    }

    @Override
    public void add(Order order) {
        entityManager.persist(OrderDto.from(order));
    }

    @Override
    public void save(Order order) {
        entityManager.merge(OrderDto.from(order));
    }

}
