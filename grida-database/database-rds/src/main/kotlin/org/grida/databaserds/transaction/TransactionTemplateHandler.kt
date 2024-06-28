package org.grida.databaserds.transaction

import org.grida.coreapi.domain.support.TransactionHandler
import org.grida.coreapi.domain.support.TransactionIsolationLevel
import org.grida.coreapi.domain.support.TransactionPropagationLevel
import org.grida.coreapi.exception.TransactionException
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