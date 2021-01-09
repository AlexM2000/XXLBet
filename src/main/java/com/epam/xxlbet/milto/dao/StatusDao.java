package com.epam.xxlbet.milto.dao;

import com.epam.xxlbet.milto.domain.Status;

public interface StatusDao {
    Status getUserStatusByEmail(String email);
    Status getUserStatusById(Long statusId);
    Status getUserStatusByName(String name);
}
