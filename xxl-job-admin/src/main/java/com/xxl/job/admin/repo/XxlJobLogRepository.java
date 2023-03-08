package com.xxl.job.admin.repo;

import com.xxl.job.admin.core.model.XxlJobLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface XxlJobLogRepository extends JpaRepository<XxlJobLog, Long>, JpaSpecificationExecutor<XxlJobLog> {

    int deleteByJobId(int jobId);

    XxlJobLog findByIdAndAlarmStatus(long logId, int alarmStatus);
}
