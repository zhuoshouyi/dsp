package com.topway.DAO;

import com.topway.pojo.UserLabel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by haizhi on 2019/5/29.
 */
public interface UserLabelDao extends JpaRepository<UserLabel, Integer> {


    List<UserLabel> findByCustomerIdOrderByCreateTimeDesc(String customerId);
}
