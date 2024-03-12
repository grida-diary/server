package org.grida.transaction;

import java.util.function.Supplier;

public interface TransactionHandler {

    <T> T execute(Supplier<T> supplier);
    <T> T executeAsRequiresNew(Supplier<T> supplier);
    void execute(Runnable runnable);
    void executeAsRequiresNew(Runnable runnable);
}
