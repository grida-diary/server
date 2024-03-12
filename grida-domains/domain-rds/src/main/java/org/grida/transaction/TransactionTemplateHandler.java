package org.grida.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class TransactionTemplateHandler implements TransactionHandler {

    private final TransactionTemplate transactionTemplate;

    @Override
    public <T> T execute(Supplier<T> supplier) throws TransactionException {
        transactionTemplate.setReadOnly(false);
        return transactionTemplate.execute(status -> supplier.get());
    }

    @Override
    public <T> T executeAsRequiresNew(Supplier<T> supplier) throws TransactionException {
        transactionTemplate.setReadOnly(false);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        return transactionTemplate.execute(status -> supplier.get());
    }

    @Override
    public void execute(Runnable runnable) throws TransactionException {
        transactionTemplate.setReadOnly(false);
        transactionTemplate.execute(status -> {
            runnable.run();
            return null;
        });
    }

    @Override
    public void executeAsRequiresNew(Runnable runnable) throws TransactionException {
        transactionTemplate.setReadOnly(false);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        transactionTemplate.execute(status -> {
            runnable.run();
            return null;
        });
    }
}
