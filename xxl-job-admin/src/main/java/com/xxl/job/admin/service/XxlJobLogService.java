package com.xxl.job.admin.service;

import com.xxl.job.admin.core.model.XxlJobLog;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface XxlJobLogService {

    List<XxlJobLog> pageList(int offset, int pagesize, int jobGroup, int jobId, Date triggerTimeStart, Date triggerTimeEnd, int logStatus);

    int pageListCount(int offset, int pagesize, int jobGroup, int jobId, Date triggerTimeStart, Date triggerTimeEnd, int logStatus);

    XxlJobLog load(long id);

    long save(XxlJobLog xxlJobLog);

    int updateTriggerInfo(XxlJobLog xxlJobLog);

    int updateHandleInfo(XxlJobLog xxlJobLog);

    int delete(int jobId);

    Map<String, Object> findLogReport(Date from, Date to);

    List<Long> findClearLogIds(int jobGroup, int jobId, Date clearBeforeTime, int clearBeforeNum, int pagesize);

    int clearLog(List<Long> logIds);

    List<Long> findFailJobLogIds(int pagesize);

    int updateAlarmStatus(long logId, int oldAlarmStatus, int newAlarmStatus);

    List<Long> findLostJobIds(Date losedTime);

}
