package io.wwan13.domain.member.entity.wrap;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DrawingType {

    VAN_GOGH(
            "van gogh",
            "https://www.dallelist.com/keywords/Artists/Van%20Gogh.jpg"
    ),
    LEONARDO_DA_VINCI(
            "Leonardo da Vinci",
            "https://www.dallelist.com/keywords/Artists/Leonardo%20da%20Vinci.jpg"
    ),
    RENE_MAGRITTE(
            "Rene Magritte",
            "https://www.dallelist.com/keywords/Artists/Rene%20Magritte.jpg"
    ),
    VLADIMIR_KUSH(
            "Vladimir Kush",
            "https://www.dallelist.com/keywords/Artists/Vladimir%20Kush.jpg"
    ),
    HENRI_MATTISE(
            "Henri Mattise",
            "https://www.dallelist.com/keywords/Artists/Henri%20Mattise.jpg"
    ),
    PABLO_PICASSO(
            "Pablo Picasso",
            "https://www.dallelist.com/keywords/Artists/Pablo%20Picasso.jpg"
    ),
    ;

    private final String painterName;
    private final String drawingExample;
}
