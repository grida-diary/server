package org.grida.domain.diary

interface DiaryRepository {

    fun save(diary: Diary): Long
}