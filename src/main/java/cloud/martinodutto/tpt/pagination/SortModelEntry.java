package cloud.martinodutto.tpt.pagination;

import javax.annotation.Nonnull;
import java.util.Map;

public abstract class SortModelEntry {

    /**
     * Column to sort for.
     */
    private String colId;
    /**
     * Direction for which to sort ("asc" or "desc").
     */
    private String sort;

    public String getColId() {
        return colId;
    }

    public void setColId(String colId) {
        this.colId = colId;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getDatabaseCol() {
        return getIdToDatabaseColumnMap().getOrDefault(colId, colId);
    }

    @Nonnull
    protected abstract Map<String, String> getIdToDatabaseColumnMap();
}
