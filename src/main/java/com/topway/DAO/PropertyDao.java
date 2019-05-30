package com.topway.DAO;

import com.topway.pojo.Property;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by haizhi on 2019/5/30.
 */
public interface PropertyDao extends JpaRepository<Property, Integer> {

    Property findByAreaId(String areaId);

}
