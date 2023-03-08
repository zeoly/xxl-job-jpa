package com.xxl.job.admin.repo;

import com.xxl.job.admin.core.model.XxlJobInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface XxlJobInfoRepository extends JpaRepository<XxlJobInfo, Integer>, JpaSpecificationExecutor<XxlJobInfo> {

    List<XxlJobInfo> findByJobGroup(int jobGroup);

    List<XxlJobInfo> findByTriggerStatusAndTriggerNextTimeLessThanEqual(int triggerStatus, long triggerNextTime, Pageable pageable);
}
