package com.xxl.job.admin.service;

import com.xxl.job.admin.core.model.XxlJobUser;

import java.util.List;

public interface XxlJobUserService {

    List<XxlJobUser> pageList(int offset, int pagesize, String username, int role);

    int pageListCount(int offset, int pagesize, String username, int role);

    XxlJobUser loadByUserName(String username);

    int save(XxlJobUser xxlJobUser);

    int update(XxlJobUser xxlJobUser);

    int delete(int id);
}
