package porridge.my.way.dddarchitecturej.order.application.queries.getOrder;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import porridge.my.way.dddarchitecturej.architecture.shell.cqrs.IQueryHandler;

@Component
public class GetOrderQueryHandler implements IQueryHandler<GetOrderQuery, GetOrderOutcome> {
    private final JdbcTemplate jdbcTemplate;

    public GetOrderQueryHandler(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public GetOrderOutcome handle(GetOrderQuery query) {
        String sql = "SELECT * FROM DddArchitectureJ.orders WHERE orders.id = ?;";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(GetOrderOutcome.class), query.getOrderId().toString());
    }
}
