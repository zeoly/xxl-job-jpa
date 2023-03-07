package com.xxl.job.admin.service;

import com.xxl.job.admin.core.model.XxlJobInfo;

import java.util.List;

public interface XxlJobInfoService {

    List<XxlJobInfo> pageList(int offset, int pagesize, int jobGroup, int triggerStatus, String jobDesc, String executorHandler, String author);

    int pageListCount(int offset, int pagesize, int jobGroup, int triggerStatus, String jobDesc, String executorHandler, String author);

    int save(XxlJobInfo info);

    XxlJobInfo loadById(int id);

    int update(XxlJobInfo xxlJobInfo);

    int delete(int id);

    List<XxlJobInfo> getJobsByGroup(int jobGroup);

    int findAllCount();

    List<XxlJobInfo> scheduleJobQuery(long maxNextTime, int pagesize);

    int scheduleUpdate(XxlJobInfo xxlJobInfo);
}
