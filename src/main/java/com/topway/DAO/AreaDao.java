package com.topway.DAO;

import com.topway.pojo.Area;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by haizhi on 2019/5/20.
 */
@Repository
public interface AreaDao extends JpaRepository<Area, Integer> {

    /**
     * 通过areaId查找唯一的小区
     *
     * fka9350c89 小区编码
     * fk560a959b 缴费类型
     * fkfceb956f 统计日期
     */
    Area findByFka9350c89AndFk560a959bAndFkfceb956f(String areaId, String fk560a959b, String yesterday);


    /**
     * 通过areaId查找唯一的小区
     *
     * fka9350c89 小区编码
     */
    Area findByFka9350c89(String areaId);

    /**
     * 通过小区名称查找一批小区信息
     *
     * fk999cd340 小区名称
     */
    @Query("select a from Area a where a.fk999cd340 like CONCAT('%',?1,'%')")
    Page<Area> findByFk999cd340Like(@Param("fk999cd340") String areaName, Pageable pageable);


//    @Query(value = " select fka9350c89, fk560a959b, fkfceb956f from area ")
//    List<Object[]> findByUniqId();

}
