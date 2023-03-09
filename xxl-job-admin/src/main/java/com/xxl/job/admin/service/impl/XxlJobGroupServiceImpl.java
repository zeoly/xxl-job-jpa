package com.xxl.job.admin.service.impl;

import com.xxl.job.admin.core.model.XxlJobGroup;
import com.xxl.job.admin.repo.XxlJobGroupRepository;
import com.xxl.job.admin.service.XxlJobGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class XxlJobGroupServiceImpl implements XxlJobGroupService {

    @Autowired
    private XxlJobGroupRepository repository;

    @Override
    public List<XxlJobGroup> findAll() {
        return repository.findAll(buildSort());
    }

    @Override
    public List<XxlJobGroup> findByAddressType(int addressType) {
        return repository.findByAddressType(addressType, buildSort());
    }

    @Override
    public int save(XxlJobGroup xxlJobGroup) {
        try {
            repository.save(xxlJobGroup);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int update(XxlJobGroup xxlJobGroup) {
        Optional<XxlJobGroup> byId = repository.findById(xxlJobGroup.getId());
        XxlJobGroup db = byId.orElse(null);
        if (db == null) {
            return 0;
        } else {
            db.setAppname(xxlJobGroup.getAppname());
            db.setTitle(xxlJobGroup.getTitle());
            db.setAddressType(xxlJobGroup.getAddressType());
            db.setAddressList(xxlJobGroup.getAddressList());
            db.setUpdateTime(new Date());
            repository.save(db);
            return 1;
        }
    }

    @Override
    public int remove(int id) {
        try {
            repository.deleteById(id);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public XxlJobGroup load(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<XxlJobGroup> pageList(int offset, int pagesize, String appname, String title) {
        return repository.findAll(buildSpec(appname, title), buildSort());
    }

    @Override
    public int pageListCount(int offset, int pagesize, String appname, String title) {
        return Long.valueOf(repository.count(buildSpec(appname, title))).intValue();
    }

    private Specification<XxlJobGroup> buildSpec(String appname, String title) {
        Specification<XxlJobGroup> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.hasText(appname)) {
                list.add(criteriaBuilder.like(root.get("appname"), "%" + appname + "%"));
            }
            if (StringUtils.hasText(title)) {
                list.add(criteriaBuilder.like(root.get("title"), "%" + title + "%"));
            }
            return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
        };
        return spec;
    }

    private Sort buildSort() {
        return Sort.by(Sort.Order.asc("appname"), Sort.Order.asc("title"), Sort.Order.asc("id"));
    }
}
