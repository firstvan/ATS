package com.tigra.ats.service.searchengine;

import org.springframework.data.domain.Pageable;

public interface Paginated {
    void setActualPage(Pageable pageable);
}
