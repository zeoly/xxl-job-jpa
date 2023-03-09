package com.xxl.job.admin.repo;

import com.xxl.job.admin.core.model.XxlJobLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface XxlJobLogRepository extends JpaRepository<XxlJobLog, Long>, JpaSpecificationExecutor<XxlJobLog> {

    int deleteByJobId(int jobId);

    XxlJobLog findByIdAndAlarmStatus(long logId, int alarmStatus);

    @Query("select l from XxlJobLog l left join XxlJobRegistry r on l.executorAddress = r.registryValue where l.triggerCode = 200" +
            "and l.handleCode = 0 and l.triggerTime <= :losedTime and r.id is null")
    List<XxlJobLog> findLostJobIds(@Param("losedTime") Date losedTime);

    int countByTriggerTimeBetween(Date from, Date to);

    int countByTriggerCodeInAndHandleCode(List<Integer> triggerCode, int handleCode);

    int countByHandleCode(int handleCode);
}
