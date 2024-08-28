package org.grida.auth

interface AuthProcessor {
    fun process(code: String): AuthToken
}
