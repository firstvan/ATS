package com.tigra.ats.service.paginate;

import com.tigra.ats.service.searchengine.SearchFilter;

public abstract class AbstractPaginatorFactory {
    abstract Paginator getPaginator(int pageNumber, SearchFilter filter, PaginatorType paginatorType);
}
