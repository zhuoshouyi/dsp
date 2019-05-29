package com.topway.DAO;

import com.topway.pojo.AreaLabel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by haizhi on 2019/5/29.
 */
public interface AreaLabelDao extends JpaRepository<AreaLabel, Integer> {

    List<AreaLabel> findByAreaIdOrderByCreateTimeDesc(String areaId);
}
