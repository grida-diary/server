package io.wwan13.domain.diary.entity.wrap;

import io.wwan13.domain.diary.exception.FutureDiaryException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
@Embeddable
public class DiaryDate {

    private LocalDate date;

    public DiaryDate(LocalDate date) {
        validate(date);
        this.date = date;
    }

    private void validate(LocalDate date) {
        if (isFuture(date)) {
            throw new FutureDiaryException();
        }
    }

    private boolean isFuture(LocalDate date) {
        LocalDate today = LocalDate.now();
        return date.isAfter(date);
    }
}
