package com.xxl.job.admin.service.impl;

import com.xxl.job.admin.core.model.XxlJobUser;
import com.xxl.job.admin.repo.XxlJobUserRepository;
import com.xxl.job.admin.service.XxlJobUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Optional;

@Service
public class XxlJobUserServiceImpl implements XxlJobUserService {

    @Autowired
    private XxlJobUserRepository repository;

    @Override
    public List<XxlJobUser> pageList(int offset, int pagesize, String username, int role) {
        return repository.findAll(buildSpec(username, role), PageRequest.of(offset, pagesize, Sort.by("username"))).getContent();
    }

    @Override
    public int pageListCount(int offset, int pagesize, String username, int role) {
        return Long.valueOf(repository.count(buildSpec(username, role))).intValue();
    }

    private Specification<XxlJobUser> buildSpec(String username, int role) {
        Specification<XxlJobUser> spec = (root, query, criteriaBuilder) -> {
            Predicate predicate = null;
            if (StringUtils.hasText(username)) {
                predicate = criteriaBuilder.like(root.get("username"), "%" + username + "%");
            }
            if (role > -1) {
                Predicate rolePredicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("role"), role));
                predicate = predicate == null ? rolePredicate : criteriaBuilder.and(predicate, rolePredicate);
            }
            return predicate;
        };
        return spec;
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
