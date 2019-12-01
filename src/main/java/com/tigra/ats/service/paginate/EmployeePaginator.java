package com.tigra.ats.service.paginate;

import com.tigra.ats.domain.Employee;
import com.tigra.ats.service.searchengine.PaginatedSearchEngine;
import com.tigra.ats.service.searchengine.SearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component("EmployeePaginator")
@RequestScope
public class EmployeePaginator implements Paginator {
    private PaginatedSearchEngine searchEngine;
    private Page<Employee> page;
    private int employeesOnOnePage;
    private int actualPageNumber;
    private int numberOfPages;

    @Autowired
    public EmployeePaginator(@Qualifier("EmployeeSearchEngine") PaginatedSearchEngine searchEngine) {
        this.searchEngine = searchEngine;
        this.employeesOnOnePage = 10;
    }

    @Override
    public void setPageRequest(int pageNumber, SearchFilter filter) {
        Pageable pageable = PageRequest.of(pageNumber - 1, employeesOnOnePage);
        this.actualPageNumber = pageNumber;
        this.page = searchEngine.search(pageable, filter);
        this.numberOfPages = PageNumberCalculator.calcNumberOfPages(this.page.getTotalPages());
    }

    @Override
    public boolean isValidPage() {
        return PageValidator.isValidPage(actualPageNumber, getNumberOfPages());
    }

    @Override
    public Page<Employee> getActualPage() {
        return this.page;
    }

    @Override
    public int getNumberOfPages() {
        return this.numberOfPages;
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
        this.employeesOnOnePage = size;
    }
}
