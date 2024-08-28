package org.grida.api.dto

data class ListResponse<T>(
    val count: Int,
    val list: List<T>
) {

    companion object {
        fun <T> from(
            list: List<T>,
            mapAction: () -> T
        ): ListResponse<T> {
            return ListResponse(
                list.size,
                list.map { mapAction.invoke() }
            )
        }
    }
}
