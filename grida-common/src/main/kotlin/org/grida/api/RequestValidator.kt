package org.grida.api

import org.grida.exception.GridaException
import org.grida.http.BAD_REQUEST

object RequestValidator {

    fun <T> validate(
        field: T,
        condition: (T) -> Boolean
    ) {
        if (!condition.invoke(field))
            throw GridaException(BAD_REQUEST, "HTTP_400", "$field 를 확인해 주세요.")
    }
}
