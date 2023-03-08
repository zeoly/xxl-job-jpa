package com.xxl.job.admin.repo;

import com.xxl.job.admin.core.model.XxlJobUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface XxlJobUserRepository extends JpaRepository<XxlJobUser, Integer>, JpaSpecificationExecutor<XxlJobUser> {

    XxlJobUser findByUsername(String username);
}
