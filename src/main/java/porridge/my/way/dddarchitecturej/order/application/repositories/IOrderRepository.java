package porridge.my.way.dddarchitecturej.order.application.repositories;

import porridge.my.way.dddarchitecturej.architecture.shell.IRepository;
import porridge.my.way.dddarchitecturej.order.domain.models.Order;

public interface IOrderRepository extends IRepository {
    void add(Order any);
}