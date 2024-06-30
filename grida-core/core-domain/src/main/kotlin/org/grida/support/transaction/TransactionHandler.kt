package org.grida.support.transaction

interface TransactionHandler {

    fun <T> handle(
        action: () -> T,
        propagationLevel: TransactionPropagationLevel = TransactionPropagationLevel.PROPAGATION_REQUIRED,
        isolationLevel: TransactionIsolationLevel = TransactionIsolationLevel.ISOLATION_DEFAULT
    ): T

    fun handle(
        action: () -> Unit,
        propagationLevel: TransactionPropagationLevel = TransactionPropagationLevel.PROPAGATION_REQUIRED,
        isolationLevel: TransactionIsolationLevel = TransactionIsolationLevel.ISOLATION_DEFAULT
    )
}
