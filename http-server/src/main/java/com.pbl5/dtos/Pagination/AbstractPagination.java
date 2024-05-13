package com.pbl5.dtos.Pagination;

public abstract class AbstractPagination {
    Integer page = 1;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

}
