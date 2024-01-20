package porridge.my.way.dddarchitecturej.architecture.shell.cqrs;

import an.awesome.pipelinr.Command;
import org.springframework.stereotype.Component;
import porridge.my.way.dddarchitecturej.architecture.shell.UnitOfWork;

@Component
public class UnitOfWorkMiddleware implements Command.Middleware {
    private final UnitOfWork unitOfWork;

    public UnitOfWorkMiddleware(UnitOfWork unitOfWork) {
        this.unitOfWork = unitOfWork;
    }

    @Override
    public <R, C extends Command<R>> R invoke(C command, Next<R> next) {
        if (command instanceof ICommand<?>)
            return handleCommand(command, next);
        else
            return next.invoke();
    }

    private <R, C extends Command<R>> R handleCommand(C command, Next<R> next) {
        if (unitOfWork.hasActiveTransaction())
            return ContinueTransaction(command, next);
        else
            return BeginTransaction(command, next);
    }

    public <R, C extends Command<R>> R BeginTransaction(C command, Next<R> next) {
        try {
            unitOfWork.beginTransaction();
            R response = next.invoke();
            unitOfWork.commit();
            return response;
        } catch (Exception e) {
            unitOfWork.rollback();
            throw e;
        }
    }

    public <R, C extends Command<R>> R ContinueTransaction(C command, Next<R> next) {
        return next.invoke();
    }
}
