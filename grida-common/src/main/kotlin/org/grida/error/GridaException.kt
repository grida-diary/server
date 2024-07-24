package org.grida.error

class GridaException(
    val errorType: ErrorType
) : RuntimeException(errorType.message) {

}
