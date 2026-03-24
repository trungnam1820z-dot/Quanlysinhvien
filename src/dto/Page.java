package dto;

import java.util.List;

public class Page<T> {
    private final List<T> data;
    private final int page;
    private final int pageSize;
    private final int totalItems;
    private final int totalPages;

    public Page(List<T> data, int page, int pageSize, int totalItems) {
        this.data = data;
        this.page = page;
        this.pageSize = pageSize;
        this.totalItems = totalItems;
        this.totalPages = (int) Math.ceil((double) totalItems / pageSize);
    }

    public List<T> getData() {
        return data;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
