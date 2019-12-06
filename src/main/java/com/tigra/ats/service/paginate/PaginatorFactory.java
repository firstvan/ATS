package com.tigra.ats.service.paginate;

import com.tigra.ats.service.searchengine.SearchFilter;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaginatorFactory extends AbstractPaginatorFactory {
    @Autowired
    private ObjectFactory<EmployeePaginator> employeePaginatorFactory;
    @Autowired
    private ObjectFactory<JobPaginator> jobPaginatorFactory;

    @Override
    public Paginator getPaginator(int pageNumber, SearchFilter filter, PaginatorType paginatorType) {
        Paginator paginator;
        switch (paginatorType) {
            case EMPLOYEE:
                paginator = employeePaginatorFactory.getObject();
                paginator.setPageRequest(pageNumber, filter);
                break;
            case JOB:
                paginator = jobPaginatorFactory.getObject();
                paginator.setPageRequest(pageNumber, filter);
                break;
            default:
                throw new IllegalArgumentException();
        }
        return paginator;
    }
}
