package porridge.my.way.dddarchitecturej.architecture.shell.cqrs;

import an.awesome.pipelinr.Command;

public interface ICommandHandler<C extends ICommand<R>, R> extends Command.Handler<C, R> {

}
