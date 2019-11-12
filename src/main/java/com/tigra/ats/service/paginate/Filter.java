package com.tigra.ats.service.paginate;

public class Filter<T> {
    private T filter;

    public Filter(T filter) {
        this.filter = filter;
    }

    public T getFilter() {
        return filter;
    }

    public void setFilter(T filter) {
        this.filter = filter;
    }

    public boolean isEmpty() {
        return filter == null;
    }
}
