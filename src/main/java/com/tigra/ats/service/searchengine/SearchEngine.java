package com.tigra.ats.service.searchengine;

public interface SearchEngine {
    Iterable search();

    void setSearchFilter(SearchFilter parameter);
}
