package com.lagou.service;

import com.lagou.domain.Test;

import java.util.List;

public interface TestService {

    /**
     * 对表进行查询
     */
    public List<Test> findAll();
}
