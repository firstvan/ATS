package com.tigra.ats.service.entityhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public class PageCounter {
    private CrudRepository repository;

    @Autowired
    public PageCounter() {
        this.repository = repository;
    }
}
