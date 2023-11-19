package porridge.my.way.dddarchitecturej.order.application.repositories;

import java.util.UUID;

import porridge.my.way.dddarchitecturej.architecture.shell.IRepository;
import porridge.my.way.dddarchitecturej.order.domain.models.Order;

public interface IOrderRepository extends IRepository {
    Order find(UUID orderId);

    void add(Order any);

    void save(Order order);
}