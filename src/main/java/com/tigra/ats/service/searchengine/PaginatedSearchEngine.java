package com.tigra.ats.service.searchengine;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaginatedSearchEngine extends SearchEngine {
    Page search(Pageable pageable, SearchFilter filter);
}
