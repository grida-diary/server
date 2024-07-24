package org.grida.error

sealed class LogLevel

data object TRACE : LogLevel()
data object DEBUG : LogLevel()
data object INFO : LogLevel()
data object WARN : LogLevel()
data object ERROR : LogLevel()
