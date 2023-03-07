package com.xxl.job.admin.repo;

import com.xxl.job.admin.core.model.XxlJobLogGlue;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface XxlJobLogGlueRepository extends JpaRepository<XxlJobLogGlue, Integer> {

    List<XxlJobLogGlue> findByJobIdOrderByIdDesc(int jobId);

    List<XxlJobLogGlue> findByJobId(int jobId, Pageable pageable);

    int deleteByJobIdAndUpdateTimeLessThan(int jobId, Date keepDate);

    int deleteByJobId(int jobId);
}
