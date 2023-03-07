package com.xxl.job.admin.repo;

import com.xxl.job.admin.core.model.XxlJobLogReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface XxlJobLogReportRepository extends JpaRepository<XxlJobLogReport, Integer> {

    XxlJobLogReport findByTriggerDay(Date triggerDay);

    List<XxlJobLogReport> findByTriggerDayBetweenOrderByTriggerDayAsc(Date from, Date to);

    @Query("Select new com.xxl.job.admin.core.model.XxlJobLogReport(sum(x.runningCount) as runningCount, sum(x.sucCount) as sucCount, sum(x.failCount) as failCount) from XxlJobLogReport as x")
    XxlJobLogReport queryTotal();
}