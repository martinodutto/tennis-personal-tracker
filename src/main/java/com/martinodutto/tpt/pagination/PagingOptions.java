package com.martinodutto.tpt.pagination;

public class PagingOptions {

    private int limit;

    private int offset;

    private String sortBy;

    private boolean sortAsc;

    public PagingOptions(int limit, int offset) {
        this(limit, offset, null, false);
    }

    public PagingOptions(int limit, int offset, String sortBy, boolean sortAsc) {
        this.limit = limit;
        this.offset = offset;
        this.sortBy = sortBy;
        this.sortAsc = sortAsc;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public boolean isSortAsc() {
        return sortAsc;
    }

    public void setSortAsc(boolean sortAsc) {
        this.sortAsc = sortAsc;
    }

    @Override
    public String toString() {
        return "PagingOptions{" +
                "limit=" + limit +
                ", offset=" + offset +
                ", sortBy='" + sortBy + '\'' +
                ", sortAsc=" + sortAsc +
                '}';
    }
}
