package com.tigra.ats.service.searchengine.employee;

import com.tigra.ats.service.searchengine.SearchType;

public class EmployeeSearchType implements SearchType {
    private EmployeeSearchTypeEnum type;

    public EmployeeSearchType(EmployeeSearchTypeEnum type) {
        this.type = type;
    }

    @Override
    public EmployeeSearchTypeEnum getType() {
        return type;
    }
}
