package org.grida.transaction;

import java.util.function.Supplier;

public interface TransactionHandler {

    <T> T execute(Supplier<T> supplier);

    <T> T readOnly(Supplier<T> supplier);
}
