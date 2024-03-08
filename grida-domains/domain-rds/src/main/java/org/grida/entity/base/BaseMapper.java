package org.grida.entity.base;

import org.grida.domain.core.DefaultDateTime;

public class BaseMapper {

    private BaseMapper() {
    }

    public static DefaultDateTime toDefaultDateTime(BaseEntity entity) {
        return new DefaultDateTime(entity.createdAt, entity.lastModifiedAt);
    }
}
