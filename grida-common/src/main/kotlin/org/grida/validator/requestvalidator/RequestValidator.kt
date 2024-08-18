package org.grida.validator.requestvalidator

import org.grida.error.ErrorType
import org.grida.error.GridaException

object RequestValidator {

    fun validate(
        errorType: ErrorType,
        condition: () -> Boolean
    ) {
        if (!condition.invoke()) {
            throw GridaException(errorType)
        }
    }

    fun validate(
        errorCode: String,
        errorMessage: String,
        condition: () -> Boolean
    ) {
        if (!condition.invoke()) {
            val errorType = InvalidInputValue(errorCode, errorMessage)
            throw GridaException(errorType)
        }
    }

    fun validate(condition: () -> Boolean) {
        if (!condition.invoke()) {
            throw GridaException(DefaultInvalidInputValue)
        }
    }
}
