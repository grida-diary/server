package org.grida.domain.base

import org.grida.error.AccessFailed
import org.grida.error.GridaException
import org.springframework.stereotype.Component

@Component
class AccessManager {

    fun ownerOnly(
        target: Ownable,
        accessorId: Long
    ) {
        if (!target.isOwner(accessorId)) {
            throw GridaException(AccessFailed)
        }
    }
}