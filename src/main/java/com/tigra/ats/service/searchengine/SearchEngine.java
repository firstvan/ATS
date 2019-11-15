package com.tigra.ats.service.searchengine;

import com.tigra.ats.service.exception.NotFoundSearchParameterException;
import com.tigra.ats.service.searchengine.parameter.SearchParameter;

public interface SearchEngine {
    Iterable search() throws NotFoundSearchParameterException;

    void setSearchParameter(SearchParameter parameter);
}
