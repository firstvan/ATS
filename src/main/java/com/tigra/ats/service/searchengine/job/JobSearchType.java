package com.tigra.ats.service.searchengine.job;

import com.tigra.ats.service.searchengine.SearchType;

public class JobSearchType implements SearchType {
    private JobSearchTypeEnum typeEnum;

    public JobSearchType(JobSearchTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    @Override
    public JobSearchTypeEnum getType() {
        return typeEnum;
    }
}
