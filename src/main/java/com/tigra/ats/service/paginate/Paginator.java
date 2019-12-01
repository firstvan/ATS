package com.tigra.ats.service.paginate;

import com.tigra.ats.service.searchengine.SearchFilter;
import org.springframework.data.domain.Page;

public interface Paginator {
    boolean isValidPage();

    Page getActualPage();

    int getNumberOfPages();

    boolean hasNextPage();

    boolean hasPrevPage();

    void setNumberOfItemsOnOnePage(int size);

    void setPageRequest(int pageNumber, SearchFilter filter);
}
