package com.xxl.job.admin.repo;

import com.xxl.job.admin.core.model.XxlJobGroup;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface XxlJobGroupRepository extends JpaRepository<XxlJobGroup, Integer> {

    List<XxlJobGroup> findByAddressType(int addressType, Sort sort);

}
