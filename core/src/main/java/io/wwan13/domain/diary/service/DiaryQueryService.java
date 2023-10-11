package io.wwan13.domain.diary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DiaryQueryService {
}
