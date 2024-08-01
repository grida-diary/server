package org.grida.domain.base

interface Ownable {

    fun isOwner(accessorId: Long): Boolean
}