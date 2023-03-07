package com.xxl.job.admin.service.impl;

import com.xxl.job.admin.core.model.XxlJobLogGlue;
import com.xxl.job.admin.repo.XxlJobLogGlueRepository;
import com.xxl.job.admin.service.XxlJobLogGlueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XxlJobLogGlueServiceImpl implements XxlJobLogGlueService {

    @Autowired
    private XxlJobLogGlueRepository repository;

    @Override
    public int save(XxlJobLogGlue xxlJobLogGlue) {
        try {
            repository.save(xxlJobLogGlue);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public List<XxlJobLogGlue> findByJobId(int jobId) {
        return repository.findByJobIdOrderByIdDesc(jobId);
    }

    @Override
    public int removeOld(int jobId, int limit) {
        List<XxlJobLogGlue> result = repository.findByJobId(jobId, PageRequest.of(0, limit, Sort.by("updateTime").descending()));
        if (result != null & result.size() > 0) {
            XxlJobLogGlue last = result.get(result.size() - 1);
            return repository.deleteByJobIdAndUpdateTimeLessThan(jobId, last.getUpdateTime());
        } else {
            return 0;
        }
    }

    @Override
    public int deleteByJobId(int jobId) {
        return repository.deleteByJobId(jobId);
    }
}
