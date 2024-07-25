package org.grida.api

import org.grida.error.GridaException
import org.grida.error.InvalidRequestField

object RequestValidator {

    fun <T> validate(
        field: T,
        condition: (T) -> Boolean
    ) {
        if (!condition.invoke(field)) {
            throw GridaException(InvalidRequestField(field.toString()))
        }
    }
}
