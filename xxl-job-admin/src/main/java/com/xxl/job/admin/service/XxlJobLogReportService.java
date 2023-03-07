package com.xxl.job.admin.service;

import com.xxl.job.admin.core.model.XxlJobLogReport;

import java.util.Date;
import java.util.List;

public interface XxlJobLogReportService {

    int save(XxlJobLogReport xxlJobLogReport);

    int update(XxlJobLogReport xxlJobLogReport);

    List<XxlJobLogReport> queryLogReport(Date triggerDayFrom, Date triggerDayTo);

    XxlJobLogReport queryLogReportTotal();
}
