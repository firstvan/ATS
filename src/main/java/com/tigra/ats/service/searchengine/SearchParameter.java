package com.tigra.ats.service.searchengine;

import com.tigra.ats.service.searchengine.SearchFilter;
import com.tigra.ats.service.searchengine.SearchType;

public interface SearchParameter {
    SearchType getSearchType();

    SearchFilter getFilter();
}
