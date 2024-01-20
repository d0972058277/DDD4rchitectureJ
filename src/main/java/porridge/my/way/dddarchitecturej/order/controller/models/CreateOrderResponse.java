package porridge.my.way.dddarchitecturej.order.controller.models;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateOrderResponse {
    public final UUID orderId;
}
