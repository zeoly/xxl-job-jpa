package com.xxl.job.admin.service.impl;

import com.xxl.job.admin.core.model.XxlJobLogReport;
import com.xxl.job.admin.repo.XxlJobLogReportRepository;
import com.xxl.job.admin.service.XxlJobLogReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class XxlJobLogReportServiceImpl implements XxlJobLogReportService {

    @Autowired
    private XxlJobLogReportRepository repository;

    @Override
    public int save(XxlJobLogReport xxlJobLogReport) {
        try {
            repository.save(xxlJobLogReport);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int update(XxlJobLogReport xxlJobLogReport) {
        XxlJobLogReport byTriggerDay = repository.findByTriggerDay(xxlJobLogReport.getTriggerDay());
        if (byTriggerDay != null) {
            byTriggerDay.setRunningCount(xxlJobLogReport.getRunningCount());
            byTriggerDay.setSucCount(xxlJobLogReport.getSucCount());
            byTriggerDay.setFailCount(xxlJobLogReport.getFailCount());
            repository.save(byTriggerDay);
            return 1;
        }
        return 0;
    }

    @Override
    public List<XxlJobLogReport> queryLogReport(Date triggerDayFrom, Date triggerDayTo) {
        return repository.findByTriggerDayBetweenOrderByTriggerDayAsc(triggerDayFrom, triggerDayTo);
    }

    @Override
    public XxlJobLogReport queryLogReportTotal() {
        return repository.queryTotal();
    }
}
