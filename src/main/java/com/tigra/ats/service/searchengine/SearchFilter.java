package com.tigra.ats.service.searchengine;

public class SearchFilter<T> {
    private T parameter;

    public SearchFilter(T parameter) {
        this.parameter = parameter;
    }

    public T getParameter() {
        return parameter;
    }

    public void setParameter(T parameter) {
        this.parameter = parameter;
    }
}
