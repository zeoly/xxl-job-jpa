package com.xxl.job.admin.repo;

import com.xxl.job.admin.core.model.XxlJobInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface XxlJobInfoRepository extends JpaRepository<XxlJobInfo, Integer> {

    List<XxlJobInfo> findByJobGroup(int jobGroup);

    List<XxlJobInfo> findByTriggerStatusAndTriggerNextTimeLessEqualThan(int triggerStatus, long triggerNextTime, Pageable pageable);
}
