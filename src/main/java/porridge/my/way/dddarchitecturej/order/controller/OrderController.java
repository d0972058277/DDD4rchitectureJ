package porridge.my.way.dddarchitecturej.order.controller;

import an.awesome.pipelinr.Voidy;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import porridge.my.way.dddarchitecturej.architecture.shell.cqrs.ICommand;
import porridge.my.way.dddarchitecturej.architecture.shell.cqrs.IMediator;
import porridge.my.way.dddarchitecturej.order.controller.models.AddOrderItemRequest;
import porridge.my.way.dddarchitecturej.order.controller.models.CreateOrderRequest;
import porridge.my.way.dddarchitecturej.order.controller.models.CreateOrderResponse;

import java.util.UUID;

@RestController
public class OrderController {
    private final IMediator mediator;

    public OrderController(IMediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping("/order")
    public CreateOrderResponse create(@RequestBody CreateOrderRequest request) {
        ICommand<UUID> command = request.toCommand();
        UUID executed = mediator.send(command);
        return new CreateOrderResponse(executed);
    }

    @PostMapping("/order/{orderId}/item")
    public void addItem(@PathVariable UUID orderId, @RequestBody AddOrderItemRequest request) {
        ICommand<Voidy> command = request.toCommand(orderId);
        mediator.send(command);
    }
}
