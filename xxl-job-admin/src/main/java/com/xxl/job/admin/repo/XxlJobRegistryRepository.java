package com.xxl.job.admin.repo;

import com.xxl.job.admin.core.model.XxlJobRegistry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface XxlJobRegistryRepository extends JpaRepository<XxlJobRegistry, Integer> {

    List<XxlJobRegistry> findByUpdateTimeLessThan(Date expireTime);

    List<XxlJobRegistry> findByUpdateTimeGreaterThan(Date expireTime);

    XxlJobRegistry findByRegistryGroupAndRegistryKeyAndRegistryValue(String registryGroup, String registryKey, String registryValue);

    int deleteByRegistryGroupAndRegistryKeyAndRegistryValue(String registryGroup, String registryKey, String registryValue);
}
