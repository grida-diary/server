package org.grida.domain.diary;

import lombok.RequiredArgsConstructor;
import org.grida.datetime.DateTimePicker;
import org.grida.domain.user.UserRepository;
import org.grida.transaction.TransactionHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;
    private final DiaryImageRepository diaryImageRepository;
    private final DateTimePicker dateTimePicker;
    private final TransactionHandler transactionHandler;

    public long append(String userEmail, DiaryContents contents) {
        return diaryRepository.save(
                userEmail,
                contents,
                userRepository.findRoleByEmail(userEmail).getDiaryImageRefreshChance(),
                dateTimePicker.now()
        );
    }

    public long modifyContents(long diaryId, DiaryContents contents) {
        return diaryRepository.modifyContents(diaryId, contents, dateTimePicker.now());
    }

    public void delete(long diaryId) {
        transactionHandler.execute(() -> {
            diaryImageRepository.deleteAllByDiaryId(diaryId);
            diaryRepository.delete(diaryId);
        });
    }
}
