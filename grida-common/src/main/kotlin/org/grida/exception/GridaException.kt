package org.grida.exception

open class GridaException(
    val httpStatusCode: Int,
    val errorCode: String,
    override val message: String,
) : RuntimeException()
