package com.xxl.job.admin.service.impl;

import com.xxl.job.admin.core.model.XxlJobLog;
import com.xxl.job.admin.repo.XxlJobLogRepository;
import com.xxl.job.admin.service.XxlJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class XxlJobLogServiceImpl implements XxlJobLogService {

    @Autowired
    private XxlJobLogRepository repository;

    @Override
    public List<XxlJobLog> pageList(int offset, int pagesize, int jobGroup, int jobId, Date triggerTimeStart, Date triggerTimeEnd, int logStatus) {
        return null;
    }

    @Override
    public int pageListCount(int offset, int pagesize, int jobGroup, int jobId, Date triggerTimeStart, Date triggerTimeEnd, int logStatus) {
        return 0;
    }

    @Override
    public XxlJobLog load(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public long save(XxlJobLog xxlJobLog) {
        repository.save(xxlJobLog);
        return xxlJobLog.getJobId();
    }

    @Override
    public int updateTriggerInfo(XxlJobLog xxlJobLog) {
        XxlJobLog db = repository.findById(xxlJobLog.getId()).orElse(null);
        if (db != null) {
            db.setTriggerTime(xxlJobLog.getTriggerTime());
            db.setTriggerCode(xxlJobLog.getTriggerCode());
            db.setTriggerMsg(xxlJobLog.getTriggerMsg());
            db.setExecutorAddress(xxlJobLog.getExecutorAddress());
            db.setExecutorHandler(xxlJobLog.getExecutorHandler());
            db.setExecutorParam(xxlJobLog.getExecutorParam());
            db.setExecutorShardingParam(xxlJobLog.getExecutorShardingParam());
            db.setExecutorFailRetryCount(xxlJobLog.getExecutorFailRetryCount());
            repository.save(db);
            return 1;
        }
        return 0;
    }

    @Override
    public int updateHandleInfo(XxlJobLog xxlJobLog) {
        return 0;
    }

    @Override
    public int delete(int jobId) {
        return repository.deleteByJobId(jobId);
    }

    @Override
    public Map<String, Object> findLogReport(Date from, Date to) {
        return null;
    }

    @Override
    public List<Long> findClearLogIds(int jobGroup, int jobId, Date clearBeforeTime, int clearBeforeNum, int pagesize) {
        return null;
    }

    @Override
    public int clearLog(List<Long> logIds) {
        repository.deleteAllById(logIds);
        return logIds.size();
    }

    @Override
    public List<Long> findFailJobLogIds(int pagesize) {
        Specification<XxlJobLog> spec = (root, query, cb) -> {
            Predicate predicate = cb.in(root.get("triggerCode")).value(Arrays.asList(0, 200));
            predicate = cb.and(cb.equal(root.get("handleCode"), 0), predicate);
            predicate = cb.or(predicate, cb.equal(root.get("handleCode"), 200)).not();
            predicate = cb.and(predicate, cb.equal(root.get("alarmStatus"), 0));
            return predicate;
        };
        return repository.findAll(spec).stream().map(XxlJobLog::getId).collect(Collectors.toList());
    }

    @Override
    public int updateAlarmStatus(long logId, int oldAlarmStatus, int newAlarmStatus) {
        XxlJobLog db = repository.findByIdAndAlarmStatus(logId, oldAlarmStatus);
        if (db != null) {
            db.setAlarmStatus(newAlarmStatus);
            repository.save(db);
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public List<Long> findLostJobIds(Date losedTime) {
        return null;
    }
}
