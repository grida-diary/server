package org.grida.domain.base

import org.grida.error.GridaException
import org.grida.error.InvalidEnumValue

interface BaseEnum<T : Enum<T>> {
    val value: String

    companion object {
        inline fun <reified T : Enum<T>> resolve(value: String): T {
            val entries = enumValues<T>()
            return entries
                .firstOrNull { (it as BaseEnum<*>).value == value }
                ?: throw GridaException(InvalidEnumValue(enumValues<T>()))
        }
    }
}
