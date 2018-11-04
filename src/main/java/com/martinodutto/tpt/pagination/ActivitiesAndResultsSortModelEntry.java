package com.martinodutto.tpt.pagination;

import com.google.common.collect.ImmutableMap;

import javax.annotation.Nonnull;
import java.util.Map;

public final class ActivitiesAndResultsSortModelEntry extends SortModelEntry {

    private static final Map<String, String> ID_TO_COLUMN_MAP;

    static {
        ID_TO_COLUMN_MAP = new ImmutableMap.Builder<String, String>()
                .put("activityDate", "ACTIVITY_DATE")
                .put("activityType", "ACTIVITY_TYPE")
                .build();
    }

    @Nonnull
    @Override
    protected Map<String, String> getIdToDatabaseColumnMap() {
        return ID_TO_COLUMN_MAP;
    }
}
