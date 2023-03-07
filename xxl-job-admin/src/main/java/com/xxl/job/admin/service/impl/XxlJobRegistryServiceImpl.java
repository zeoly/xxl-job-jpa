package com.xxl.job.admin.service.impl;

import com.xxl.job.admin.core.model.XxlJobRegistry;
import com.xxl.job.admin.repo.XxlJobRegistryRepository;
import com.xxl.job.admin.service.XxlJobRegistryService;
import com.xxl.job.core.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class XxlJobRegistryServiceImpl implements XxlJobRegistryService {

    @Autowired
    private XxlJobRegistryRepository repository;

    @Override
    public List<Integer> findDead(int timeout, Date nowTime) {
        Date expireTime = DateUtil.addSeconds(nowTime, -timeout);
        List<XxlJobRegistry> result = repository.findByUpdateTimeLessThan(expireTime);
        return result.stream().map(XxlJobRegistry::getId).collect(Collectors.toList());
    }

    @Override
    public int removeDead(List<Integer> ids) {
        repository.deleteAllByIdInBatch(ids);
        return 1;
    }

    @Override
    public List<XxlJobRegistry> findAll(int timeout, Date nowTime) {
        Date expireTime = DateUtil.addSeconds(nowTime, -timeout);
        return repository.findByUpdateTimeGreaterThan(expireTime);
    }

    @Override
    public int registryUpdate(String registryGroup, String registryKey, String registryValue, Date updateTime) {
        XxlJobRegistry registry = repository.findByRegistryGroupAndRegistryKeyAndRegistryValue(registryGroup, registryKey, registryValue);
        if (registry != null) {
            registry.setUpdateTime(updateTime);
            return 1;
        }
        return 0;
    }

    @Override
    public int registrySave(String registryGroup, String registryKey, String registryValue, Date updateTime) {
        XxlJobRegistry registry = new XxlJobRegistry();
        registry.setRegistryGroup(registryGroup);
        registry.setRegistryKey(registryKey);
        registry.setRegistryValue(registryValue);
        registry.setUpdateTime(updateTime);
        repository.save(registry);
        return 1;
    }

    @Override
    public int registryDelete(String registryGroup, String registryKey, String registryValue) {
        return repository.deleteByRegistryGroupAndRegistryKeyAndRegistryValue(registryGroup, registryKey, registryValue);
    }
}
