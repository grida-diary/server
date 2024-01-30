package org.grida.client.image.dto;

import lombok.Builder;

@Builder
public record ImageRequestDto(
        String model,
        String prompt,
        int n,
        String size
) {
}
