package porridge.my.way.dddarchitecturej.architecture.shell;

import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

@Component
public class UnitOfWork {
    private final PlatformTransactionManager platformTransactionManager;
    private final TransactionDefinition transactionDefinition;

    // 使用 ThreadLocal 來保存每個線程的 TransactionStatus
    private final ThreadLocal<TransactionStatus> transactionStatus = new ThreadLocal<>();

    public UnitOfWork(PlatformTransactionManager platformTransactionManager, TransactionDefinition transactionDefinition) {
        this.platformTransactionManager = platformTransactionManager;
        this.transactionDefinition = transactionDefinition;
    }

    public boolean hasActiveTransaction() {
        return transactionStatus.get() != null;
    }

    public void beginTransaction() {
        transactionStatus.set(platformTransactionManager.getTransaction(transactionDefinition));
    }

    public void commit() {
        platformTransactionManager.commit(transactionStatus.get());
        transactionStatus.remove();
    }

    public void rollback() {
        platformTransactionManager.rollback(transactionStatus.get());
        transactionStatus.remove();
    }
}
