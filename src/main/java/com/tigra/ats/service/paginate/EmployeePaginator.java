package com.tigra.ats.service.paginate;

import com.tigra.ats.domain.Employee;
import com.tigra.ats.service.exception.NotFoundSearchParameterException;
import com.tigra.ats.service.searchengine.SearchEngine;
import org.springframework.data.domain.Page;

public class EmployeePaginator implements Paginator {
    private SearchEngine searchEngine;

    public EmployeePaginator(SearchEngine searchEngine) {
        this.searchEngine = searchEngine;
    }

    @Override
    public Page<Employee> getPage() {
        Page<Employee> actualPage = null;
        try {
            actualPage = (Page<Employee>) searchEngine.search();
        } catch (NotFoundSearchParameterException ex) {
            ex.printStackTrace();
        }
        return actualPage;
    }
}