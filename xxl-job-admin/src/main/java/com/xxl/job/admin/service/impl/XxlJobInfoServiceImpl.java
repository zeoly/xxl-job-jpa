package com.xxl.job.admin.service.impl;

import com.xxl.job.admin.core.model.XxlJobInfo;
import com.xxl.job.admin.repo.XxlJobInfoRepository;
import com.xxl.job.admin.service.XxlJobInfoService;
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
public class XxlJobInfoServiceImpl implements XxlJobInfoService {

    @Autowired
    private XxlJobInfoRepository repository;

    @Override
    public List<XxlJobInfo> pageList(int offset, int pagesize, int jobGroup, int triggerStatus, String jobDesc, String executorHandler, String author) {
        return repository.findAll(buildSpec(jobGroup, triggerStatus, jobDesc, executorHandler, author), PageRequest.of(offset, pagesize, Sort.by("id").descending())).getContent();
    }

    @Override
    public int pageListCount(int offset, int pagesize, int jobGroup, int triggerStatus, String jobDesc, String executorHandler, String author) {
        return Long.valueOf(repository.count(buildSpec(jobGroup, triggerStatus, jobDesc, executorHandler, author))).intValue();
    }

    private Specification<XxlJobInfo> buildSpec(int jobGroup, int triggerStatus, String jobDesc, String executorHandler, String author) {
        Specification<XxlJobInfo> spec = (root, query, criteriaBuilder) -> {
            Predicate predicate = null;
            if (jobGroup > 0) {
                predicate = criteriaBuilder.equal(root.get("jobGroup"), jobGroup);
            }
            if (triggerStatus >= 0) {
                Predicate status = criteriaBuilder.equal(root.get("triggerStatus"), triggerStatus);
                predicate = predicate == null ? status : criteriaBuilder.and(predicate, status);
            }
            if (StringUtils.hasText(jobDesc)) {
                Predicate desc = criteriaBuilder.like(root.get("jobDesc"), "%" + jobDesc + "%");
                predicate = predicate == null ? desc : criteriaBuilder.and(predicate, desc);
            }
            if (StringUtils.hasText(executorHandler)) {
                Predicate handler = criteriaBuilder.like(root.get("executorHandler"), "%" + executorHandler + "%");
                predicate = predicate == null ? handler : criteriaBuilder.and(predicate, handler);
            }
            if (StringUtils.hasText(author)) {
                Predicate authorPredicate = criteriaBuilder.like(root.get("author"), "%" + author + "%");
                predicate = predicate == null ? authorPredicate : criteriaBuilder.and(predicate, authorPredicate);
            }
            return predicate;
        };
        return spec;
    }

    @Override
    public int save(XxlJobInfo info) {
        try {
            repository.save(info);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public XxlJobInfo loadById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public int update(XxlJobInfo xxlJobInfo) {
        Optional<XxlJobInfo> byId = repository.findById(xxlJobInfo.getId());
        XxlJobInfo jobInfo = byId.orElse(null);
        if (jobInfo != null) {
            xxlJobInfo.setAddTime(jobInfo.getAddTime());
            repository.save(xxlJobInfo);
            return 1;
        }
        return 0;
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

    @Override
    public List<XxlJobInfo> getJobsByGroup(int jobGroup) {
        return repository.findByJobGroup(jobGroup);
    }

    @Override
    public int findAllCount() {
        return Long.valueOf(repository.count()).intValue();
    }

    @Override
    public List<XxlJobInfo> scheduleJobQuery(long maxNextTime, int pagesize) {
        return repository.findByTriggerStatusAndTriggerNextTimeLessThanEqual(1, maxNextTime, PageRequest.of(1, pagesize, Sort.by("id")));
    }

    @Override
    public int scheduleUpdate(XxlJobInfo xxlJobInfo) {
        Optional<XxlJobInfo> byId = repository.findById(xxlJobInfo.getId());
        XxlJobInfo jobInfo = byId.orElse(null);
        if (jobInfo != null) {
            jobInfo.setTriggerLastTime(xxlJobInfo.getTriggerLastTime());
            jobInfo.setTriggerNextTime(xxlJobInfo.getTriggerNextTime());
            jobInfo.setTriggerStatus(xxlJobInfo.getTriggerStatus());
            repository.save(jobInfo);
            return 1;
        } else {
            return 0;
        }
    }
}
