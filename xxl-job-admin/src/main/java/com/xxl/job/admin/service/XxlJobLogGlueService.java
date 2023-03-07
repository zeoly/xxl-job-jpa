package com.xxl.job.admin.service;

import com.xxl.job.admin.core.model.XxlJobLogGlue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XxlJobLogGlueService {

    int save(XxlJobLogGlue xxlJobLogGlue);

    List<XxlJobLogGlue> findByJobId(@Param("jobId") int jobId);

    int removeOld(@Param("jobId") int jobId, @Param("limit") int limit);

    int deleteByJobId(@Param("jobId") int jobId);
}
