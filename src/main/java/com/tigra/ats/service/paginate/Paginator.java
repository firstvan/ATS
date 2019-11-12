package com.tigra.ats.service.paginate;

import org.springframework.data.domain.Page;

public interface Paginator {
    void setMaxItemInOnePage(int numberOfItems);

    Page getPage(int index);

    void setFilter(Filter filter);
}
