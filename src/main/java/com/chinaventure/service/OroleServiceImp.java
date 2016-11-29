package com.chinaventure.service;

import com.chinaventure.dao.OroleRepository;
import com.chinaventure.entity.ORole;
import org.springframework.stereotype.Service;

/**
 * Created by lianlei on 2016/11/29.
 */
@Service
public class OroleServiceImp implements OroleService {
    private OroleRepository oroleRepository;

    public OroleServiceImp(OroleRepository oroleRepository) {
        this.oroleRepository = oroleRepository;
    }

    @Override
    public ORole saveRole(ORole role) {
        return oroleRepository.save(role);
    }
}
