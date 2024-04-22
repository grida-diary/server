package org.grida.client.image.dto;

import lombok.Builder;

@Builder
public record ImageGenerateRequest(
        String model,
        String prompt,
        int n,
        String size
) {
}
