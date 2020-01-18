package com.task.telna.repository;

import com.task.telna.entity.Usage;
import com.task.telna.entity.User;
import com.task.telna.enums.UsageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UsageRepository extends JpaRepository<Usage, Long> {

    List<Usage> findByUsageTypeAndUserAndStartDateBetween(UsageType usageType, User user, Date startDate,
                                                          Date endDate);
}
