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
    public <T> T readOnly(Supplier<T> supplier) {
        transactionTemplate.setReadOnly(true);
        return transactionTemplate.execute(status -> supplier.get());
    }
}
