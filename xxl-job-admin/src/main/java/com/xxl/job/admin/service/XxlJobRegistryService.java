package com.xxl.job.admin.service;

import com.xxl.job.admin.core.model.XxlJobRegistry;

import java.util.Date;
import java.util.List;

public interface XxlJobRegistryService {

    List<Integer> findDead(int timeout, Date nowTime);

    int removeDead(List<Integer> ids);

    List<XxlJobRegistry> findAll(int timeout, Date nowTime);

    int registryUpdate(String registryGroup, String registryKey, String registryValue, Date updateTime);

    int registrySave(String registryGroup, String registryKey, String registryValue, Date updateTime);

    int registryDelete(String registryGroup, String registryKey, String registryValue);
}
