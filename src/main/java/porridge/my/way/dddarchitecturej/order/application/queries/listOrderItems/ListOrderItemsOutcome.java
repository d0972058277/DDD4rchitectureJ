package porridge.my.way.dddarchitecturej.order.application.queries.listOrderItems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListOrderItemsOutcome {
    private UUID id;
    private int productId;
    private float price;
    private int quantity;
}
