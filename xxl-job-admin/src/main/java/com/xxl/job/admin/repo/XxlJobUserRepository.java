package com.xxl.job.admin.repo;

import com.xxl.job.admin.core.model.XxlJobUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface XxlJobUserRepository extends JpaRepository<XxlJobUser, Integer> {

    XxlJobUser findByUsername(String username);
}
