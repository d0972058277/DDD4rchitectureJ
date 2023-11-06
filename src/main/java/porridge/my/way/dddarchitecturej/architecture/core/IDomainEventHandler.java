package porridge.my.way.dddarchitecturej.architecture.core;

import an.awesome.pipelinr.Notification;

public interface IDomainEventHandler<N extends IDomainEvent> extends Notification.Handler<N> {
}
