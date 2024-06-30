package org.grida.transaction

import org.grida.exception.TransactionException
import org.grida.support.transaction.TransactionHandler
import org.grida.support.transaction.TransactionIsolationLevel
import org.grida.support.transaction.TransactionPropagationLevel
import org.springframework.transaction.support.TransactionTemplate

class TransactionTemplateHandler(
    private val transactionTemplate: TransactionTemplate
) : TransactionHandler {

    override fun <T> handle(
        action: () -> T,
        propagationLevel: TransactionPropagationLevel,
        isolationLevel: TransactionIsolationLevel
    ): T {
        transactionTemplate.setPropagationBehaviorName(propagationLevel.toString())
        transactionTemplate.setIsolationLevelName(isolationLevel.toString())

        return transactionTemplate.execute {
            action.invoke()
        } ?: throw TransactionException()
    }

    override fun handle(
        action: () -> Unit,
        propagationLevel: TransactionPropagationLevel,
        isolationLevel: TransactionIsolationLevel
    ) {
        transactionTemplate.setPropagationBehaviorName(propagationLevel.toString())
        transactionTemplate.setIsolationLevelName(isolationLevel.toString())

        transactionTemplate.executeWithoutResult { status ->
            action.invoke()
        }
    }
}
