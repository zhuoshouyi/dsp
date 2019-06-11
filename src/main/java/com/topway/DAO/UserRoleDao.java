package com.topway.DAO;

import com.topway.pojo.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by haizhi on 2019/6/11.
 */
public interface UserRoleDao extends JpaRepository<UserRole, Integer> {

    UserRole findByUserId(String userId);

}
