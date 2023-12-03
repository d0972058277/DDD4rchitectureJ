package porridge.my.way.dddarchitecturej.order.infrastructure.repositories;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import porridge.my.way.dddarchitecturej.order.application.repositories.IOrderRepository;
import porridge.my.way.dddarchitecturej.order.domain.models.Order;

@Repository
public class OrderRepository implements IOrderRepository {

    @Override
    public Order find(UUID orderId) {
        // todo: Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }

    @Override
    public void add(Order order) {
        // todo: Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public void save(Order order) {
        // todo: Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

}
