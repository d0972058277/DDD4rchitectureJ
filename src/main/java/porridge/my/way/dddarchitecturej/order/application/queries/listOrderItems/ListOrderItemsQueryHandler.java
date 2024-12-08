package porridge.my.way.dddarchitecturej.order.application.queries.listOrderItems;

import jakarta.persistence.EntityManager;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import porridge.my.way.dddarchitecturej.architecture.shell.cqrs.IQueryHandler;
import porridge.my.way.dddarchitecturej.order.domain.models.Order;

import java.util.List;

@Component
public class ListOrderItemsQueryHandler implements IQueryHandler<ListOrderItemsQuery, List<ListOrderItemsOutcome>> {
    private final JdbcTemplate jdbcTemplate;
    private final EntityManager entityManager;

    public ListOrderItemsQueryHandler(JdbcTemplate jdbcTemplate, EntityManager entityManager) {
        this.jdbcTemplate = jdbcTemplate;
        this.entityManager = entityManager;
    }

    @Override
    public List<ListOrderItemsOutcome> handle(ListOrderItemsQuery query) {
        Order order = entityManager.find(Order.class, query.getOrderId());
        String sql = "SELECT * FROM order_items WHERE order_items.order_id = ?;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ListOrderItemsOutcome.class), query.getOrderId().toString());
    }
}
