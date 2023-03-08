package com.xxl.job.admin.repo;

import com.xxl.job.admin.core.model.XxlJobLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface XxlJobLogRepository extends JpaRepository<XxlJobLog, Long> {

    int deleteByJobId(int jobId);

    XxlJobLog findByIdAndAlarmStatus(long logId, int alarmStatus);
}
