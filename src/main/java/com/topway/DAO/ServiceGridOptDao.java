package com.topway.DAO;

import com.topway.pojo.ServiceGridOpt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by haizhi on 2019/6/11.
 */
public interface ServiceGridOptDao extends JpaRepository<ServiceGridOpt, String> {

    List<ServiceGridOpt> findByOpId(String opId);
}
