package org.grida.domain.base

import org.grida.error.GridaException
import org.grida.error.InvalidEnumValue

interface ValueEnum<T : Enum<T>> {
    val value: String

    companion object {
        inline fun <reified T : Enum<T>> resolve(value: String): T {
            val entries = enumValues<T>()
            return entries
                .firstOrNull { (it as ValueEnum<*>).value == value }
                ?: throw GridaException(InvalidEnumValue(entries.map { (it as ValueEnum<*>).value }))
        }
    }
}
