package com.xxl.job.admin.service.impl;

import com.xxl.job.admin.core.model.XxlJobUser;
import com.xxl.job.admin.repo.XxlJobUserRepository;
import com.xxl.job.admin.service.XxlJobUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class XxlJobUserServiceImpl implements XxlJobUserService {

    @Autowired
    private XxlJobUserRepository repository;

    @Override
    public List<XxlJobUser> pageList(int offset, int pagesize, String username, int role) {
        return null;
    }

    @Override
    public int pageListCount(int offset, int pagesize, String username, int role) {
        return 0;
    }

    @Override
    public XxlJobUser loadByUserName(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public int save(XxlJobUser xxlJobUser) {
        try {
            repository.save(xxlJobUser);
            return xxlJobUser.getId();
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int update(XxlJobUser xxlJobUser) {
        Optional<XxlJobUser> byId = repository.findById(xxlJobUser.getId());
        XxlJobUser db = byId.orElse(null);
        if (db != null) {
            db.setRole(xxlJobUser.getRole());
            db.setPermission(xxlJobUser.getPermission());
            if (StringUtils.hasText(xxlJobUser.getPassword())) {
                db.setPassword(xxlJobUser.getPassword());
            }
            repository.save(db);
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int delete(int id) {
        try {
            repository.deleteById(id);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
}
