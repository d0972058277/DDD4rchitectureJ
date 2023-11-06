package porridge.my.way.dddarchitecturej.architecture.shell.cqrs;

import an.awesome.pipelinr.Command;

public interface IQueryHandler<Q extends IQuery<R>, R> extends Command.Handler<Q, R> {
}
