package com.tigra.ats.service.paginate;

import com.tigra.ats.domain.Job;
import com.tigra.ats.service.searchengine.PaginatedSearchEngine;
import com.tigra.ats.service.searchengine.SearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component("JobPaginator")
@RequestScope
public class JobPaginator implements Paginator {
    private PaginatedSearchEngine searchEngine;
    private Page<Job> page;
    private int actualPageNumber;
    private int jobsOnOnePage;
    private int numberOfPages;

    @Autowired
    public JobPaginator(@Qualifier("JobSearchEngine") PaginatedSearchEngine searchEngine) {
        this.searchEngine = searchEngine;
        this.jobsOnOnePage = 10;
    }

    @Override
    public boolean isValidPage() {
        return PageValidator.isValidPage(actualPageNumber, getNumberOfPages());
    }

    @Override
    public Page getActualPage() {
        return this.page;
    }

    @Override
    public int getNumberOfPages() {
        return numberOfPages;
    }

    @Override
    public boolean hasNextPage() {
        return PageValidator.hasNextPage(actualPageNumber, getNumberOfPages());
    }

    @Override
    public boolean hasPrevPage() {
        return PageValidator.hasPrevPage(actualPageNumber);
    }

    @Override
    public void setNumberOfItemsOnOnePage(int size) {
        this.jobsOnOnePage = size;
    }

    @Override
    public void setPageRequest(int pageNumber, SearchFilter filter) {
        Pageable pageable = PageRequest.of(pageNumber - 1, jobsOnOnePage);
        this.actualPageNumber = pageNumber;
        this.page = searchEngine.search(pageable, filter);
        this.numberOfPages = PageNumberCalculator.calcNumberOfPages(this.page.getTotalPages());
    }
}
