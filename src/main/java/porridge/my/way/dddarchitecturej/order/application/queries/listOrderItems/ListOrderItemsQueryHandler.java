package porridge.my.way.dddarchitecturej.order.application.queries.listOrderItems;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import porridge.my.way.dddarchitecturej.architecture.shell.cqrs.IQueryHandler;

import java.util.List;

@Component
public class ListOrderItemsQueryHandler implements IQueryHandler<ListOrderItemsQuery, List<ListOrderItemsOutcome>> {
    private final JdbcTemplate jdbcTemplate;

    public ListOrderItemsQueryHandler(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ListOrderItemsOutcome> handle(ListOrderItemsQuery query) {
        String sql = "SELECT * FROM order_items WHERE order_items.order_id = ?;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ListOrderItemsOutcome.class), query.getOrderId().toString());
    }
}
