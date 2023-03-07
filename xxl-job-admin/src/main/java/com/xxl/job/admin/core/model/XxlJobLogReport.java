package com.xxl.job.admin.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "xxl_job_log_report")
public class XxlJobLogReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "trigger_day")
    private Date triggerDay;

    @Column(name = "running_count")
    private int runningCount;

    @Column(name = "suc_count")
    private int sucCount;

    @Column(name = "fail_count")
    private int failCount;

    public XxlJobLogReport() {
    }

    public XxlJobLogReport(Long runningCount, Long sucCount, Long failCount) {
        this.runningCount = runningCount.intValue();
        this.sucCount = sucCount.intValue();
        this.failCount = failCount.intValue();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTriggerDay() {
        return triggerDay;
    }

    public void setTriggerDay(Date triggerDay) {
        this.triggerDay = triggerDay;
    }

    public int getRunningCount() {
        return runningCount;
    }

    public void setRunningCount(int runningCount) {
        this.runningCount = runningCount;
    }

    public int getSucCount() {
        return sucCount;
    }

    public void setSucCount(int sucCount) {
        this.sucCount = sucCount;
    }

    public int getFailCount() {
        return failCount;
    }

    public void setFailCount(int failCount) {
        this.failCount = failCount;
    }
}
