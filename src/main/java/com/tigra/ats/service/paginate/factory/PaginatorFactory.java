package com.tigra.ats.service.paginate.factory;

import com.tigra.ats.service.searchengine.PaginatedSearchEngine;
import com.tigra.ats.service.searchengine.SearchFilter;
import com.tigra.ats.service.searchengine.SearchParameter;
import com.tigra.ats.service.searchengine.SearchType;
import org.springframework.data.domain.Pageable;

public interface PaginatorFactory {
    SearchParameter createSearchParameter(SearchType type, SearchFilter filter);

    PaginatedSearchEngine createSearchEngine(SearchParameter parameter, Pageable pageable);
}