package porridge.my.way.dddarchitecturej.architecture.core;

import an.awesome.pipelinr.Notification;

public interface IDomainEventHandler<N extends DomainEvent> extends Notification.Handler<N> {
}
