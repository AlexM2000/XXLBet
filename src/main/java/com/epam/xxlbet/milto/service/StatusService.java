package com.epam.xxlbet.milto.service;

import com.epam.xxlbet.milto.domain.Status;

public interface StatusService {
    Status getStatusById(Long statusId);
    Status getUserStatusByEmail(String email);
    Status getUserStatusByName(String name);
}
