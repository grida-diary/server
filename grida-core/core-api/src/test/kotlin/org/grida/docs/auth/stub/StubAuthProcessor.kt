package org.grida.docs.auth.stub

import org.grida.auth.AuthProcessor
import org.grida.auth.AuthToken

class StubAuthProcessor : AuthProcessor {
    override fun process(code: String): AuthToken {
        return AuthToken("access token", "refresh token")
    }
}
