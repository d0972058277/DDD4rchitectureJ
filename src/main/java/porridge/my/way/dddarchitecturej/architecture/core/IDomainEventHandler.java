package porridge.my.way.dddarchitecturej.architecture.core;

import an.awesome.pipelinr.Notification;

public interface IDomainEventHandler<TNotification extends IDomainEvent> extends Notification.Handler<TNotification> {
}
