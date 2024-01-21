package porridge.my.way.dddarchitecturej.order.application.queries.getOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderOutcome {
    private UUID id;
    private String address;
    private String name;
}
