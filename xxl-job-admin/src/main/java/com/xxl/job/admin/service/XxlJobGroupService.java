package com.xxl.job.admin.service;

import com.xxl.job.admin.core.model.XxlJobGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface XxlJobGroupService {

    List<XxlJobGroup> findAll();

    List<XxlJobGroup> findByAddressType(int addressType);

    int save(XxlJobGroup xxlJobGroup);

    int update(XxlJobGroup xxlJobGroup);

    int remove(@Param("id") int id);

    XxlJobGroup load(@Param("id") int id);

    List<XxlJobGroup> pageList(@Param("offset") int offset,
                               @Param("pagesize") int pagesize,
                               @Param("appname") String appname,
                               @Param("title") String title);

    int pageListCount(@Param("offset") int offset,
                      @Param("pagesize") int pagesize,
                      @Param("appname") String appname,
                      @Param("title") String title);

}
