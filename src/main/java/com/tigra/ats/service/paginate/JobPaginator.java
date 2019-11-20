package com.tigra.ats.service.paginate;

import com.tigra.ats.domain.Job;
import com.tigra.ats.service.exception.NotFoundSearchParameterException;
import com.tigra.ats.service.searchengine.SearchEngine;
import org.springframework.data.domain.Page;

public class JobPaginator implements Paginator {
    private SearchEngine searchEngine;

    public JobPaginator(SearchEngine engine) {
        this.searchEngine = engine;
    }

    @Override
    public Page<Job> getPage() {
        Page<Job> actualPage = null;
        try {
            actualPage = (Page<Job>) searchEngine.search();
        } catch (NotFoundSearchParameterException ex) {
            ex.printStackTrace();
        }
        return actualPage;
    }
}
