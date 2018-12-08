package cloud.martinodutto.tpt.pagination;

import com.google.common.annotations.VisibleForTesting;

import java.util.Collections;
import java.util.Set;

public final class PagingOptions<S extends SortModelEntry> {

    private int startRow;
    private int endRow;
    private Set<S> sortModel = Collections.emptySet();

    // needed for deserialization
    @SuppressWarnings("unused")
    public PagingOptions() {
    }

    @VisibleForTesting
    public PagingOptions(int startRow, int endRow) {
        this.startRow = startRow;
        this.endRow = endRow;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public Set<S> getSortModel() {
        return sortModel;
    }

    public void setSortModel(Set<S> sortModel) {
        this.sortModel = sortModel;
    }

    @Override
    public String toString() {
        return "PagingOptions{" +
                "startRow=" + startRow +
                ", endRow=" + endRow +
                ", sortModel=" + sortModel +
                '}';
    }
}
