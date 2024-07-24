package org.grida.error

interface ErrorType {
    val httpStatusCode: Int
    val errorCode: String
    val message: String
    val logLevel: LogLevel
}