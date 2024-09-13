package porridge.my.way.dddarchitecturej.order.controller;

import an.awesome.pipelinr.Voidy;
import io.vavr.control.Try;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import porridge.my.way.dddarchitecturej.architecture.exceptions.IllegalArgumentDomainException;
import porridge.my.way.dddarchitecturej.architecture.shell.cqrs.ICommand;
import porridge.my.way.dddarchitecturej.architecture.shell.cqrs.IMediator;
import porridge.my.way.dddarchitecturej.order.application.commands.createOrder.CreateOrderCommand;
import porridge.my.way.dddarchitecturej.order.application.queries.getOrder.GetOrderOutcome;
import porridge.my.way.dddarchitecturej.order.application.queries.getOrder.GetOrderQuery;
import porridge.my.way.dddarchitecturej.order.application.queries.listOrderItems.ListOrderItemsOutcome;
import porridge.my.way.dddarchitecturej.order.application.queries.listOrderItems.ListOrderItemsQuery;
import porridge.my.way.dddarchitecturej.order.controller.models.AddOrderItemRequest;
import porridge.my.way.dddarchitecturej.order.controller.models.CreateOrderRequest;
import porridge.my.way.dddarchitecturej.order.controller.models.CreateOrderResponse;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final IMediator mediator;

    public OrderController(IMediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public CreateOrderResponse create(@Valid @RequestBody CreateOrderRequest request) throws Throwable {
        Try<CreateOrderCommand> commandTry = request.toCommand();

        if (commandTry.isFailure()) {
            throw commandTry.getCause();
        }

        UUID executed = mediator.send(commandTry.get());
        return new CreateOrderResponse(executed);
    }

    @GetMapping("/{orderId}")
    public GetOrderOutcome get(@PathVariable UUID orderId) {
        GetOrderQuery query = new GetOrderQuery(orderId);
        return mediator.send(query);
    }

    @PostMapping("/{orderId}/item")
    public void addItem(@PathVariable UUID orderId, @RequestBody AddOrderItemRequest request) throws IllegalArgumentDomainException {
        ICommand<Voidy> command = request.toCommand(orderId);
        mediator.send(command);
    }

    @GetMapping("{orderId}/item")
    public List<ListOrderItemsOutcome> listItems(@PathVariable UUID orderId) {
        ListOrderItemsQuery query = new ListOrderItemsQuery(orderId);
        return mediator.send(query);
    }
}
