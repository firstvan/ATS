/*
package com.tigra.ats;

import com.tigra.ats.domain.Employee;
import com.tigra.ats.repository.EmployeeRepository;
import com.tigra.ats.service.exception.NotFoundSearchParameterException;
import com.tigra.ats.service.searchengine.employee.EmployeeSearchEngine;
import com.tigra.ats.service.searchengine.parameter.EmployeeSearchParameter;
import com.tigra.ats.service.searchengine.employee.EmployeeSearchType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Pageable;

public class EmployeeSearchEngineTest {
    private EmployeeSearchEngine searchEngine;
    private EmployeeRepository employeeRepository;
    private Pageable pageable;

    @Before
    public void setUp() {
        employeeRepository = Mockito.mock(EmployeeRepository.class);
        searchEngine = new EmployeeSearchEngine(employeeRepository);
        pageable = Mockito.mock(Pageable.class);
    }

    @Test(expected = NotFoundSearchParameterException.class)
    public void cannotSearchWhenHasNotSearchParameter() throws NotFoundSearchParameterException {
        searchEngine.search();
    }

    @Test
    public void whenSearchParameterIsDefaultThenFindAll() throws NotFoundSearchParameterException {
        EmployeeSearchParameter searchParameter = new EmployeeSearchParameter();
        searchEngine.setSearchParameter(searchParameter);
        searchEngine.setActualPage(pageable);
        searchEngine.search();
        Mockito.verify(employeeRepository).findAll(pageable);
    }

    @Test
    public void whenSearchParameterIsByNameThenFindByFirstNameIsContaining() throws NotFoundSearchParameterException {
        Employee filterEmployee = new Employee();
        filterEmployee.setFirstName("Ol");
        EmployeeSearchParameter searchParameter = new EmployeeSearchParameter();
        searchParameter.setSearchType(EmployeeSearchType.BY_FIRST_NAME);
        searchParameter.setEmployee(filterEmployee);
        searchEngine.setSearchParameter(searchParameter);
        searchEngine.setActualPage(pageable);
        searchEngine.search();
        Mockito.verify(employeeRepository).findByFirstNameIsContaining(pageable, filterEmployee.getFirstName());
    }
}*/
