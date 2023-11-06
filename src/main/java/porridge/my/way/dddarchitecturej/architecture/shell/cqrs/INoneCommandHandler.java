package porridge.my.way.dddarchitecturej.architecture.shell.cqrs;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Voidy;

public interface INoneCommandHandler<C extends INoneCommand> extends Command.Handler<C, Voidy> {

}
