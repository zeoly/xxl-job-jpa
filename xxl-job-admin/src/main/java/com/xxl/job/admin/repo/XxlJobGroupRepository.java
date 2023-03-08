package com.xxl.job.admin.repo;

import com.xxl.job.admin.core.model.XxlJobGroup;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface XxlJobGroupRepository extends JpaRepository<XxlJobGroup, Integer>, JpaSpecificationExecutor<XxlJobGroup> {

    List<XxlJobGroup> findByAddressType(int addressType, Sort sort);

}
