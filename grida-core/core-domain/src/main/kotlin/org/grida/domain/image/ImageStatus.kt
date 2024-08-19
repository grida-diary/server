package org.grida.domain.image

import org.grida.domain.base.ValueEnum

enum class ImageStatus(
    override val value: String
) : ValueEnum<ImageStatus> {
    ACTIVATE("활성화"),
    DEACTIVATE("비활성화");
}
