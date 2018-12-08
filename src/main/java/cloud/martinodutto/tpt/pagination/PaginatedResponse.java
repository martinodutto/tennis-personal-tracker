package cloud.martinodutto.tpt.pagination;

import java.util.List;

public class PaginatedResponse<T> {

    private List<T> data;

    private long totalCount;

    public PaginatedResponse(List<T> data, long totalCount) {
        this.data = data;
        this.totalCount = totalCount;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "PaginatedResponse{" +
                "data=" + data +
                ", totalCount=" + totalCount +
                '}';
    }
}
