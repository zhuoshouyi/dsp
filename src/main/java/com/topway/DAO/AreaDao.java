package com.topway.DAO;

import com.topway.pojo.Area;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    Area findByAreaIdAndPaymentTypeAndDate(String areaId, String paymentType, String yesterday);


    /**
     * 通过areaId查找唯一的小区
     *
     * fka9350c89 小区编码
     * fk560a959b 缴费类型
     * fkfceb956f 统计日期
     * fk89cc3f4d 网格编码
     * fkeef0a02b 运营商
     * fk93e2d7ec 区域分公司
     */
    @Query(value = "select a from Area a " +
            "where a.areaId=?1 and a.paymentType=?2 and a.date=?3 " +
            "and (a.gridId in ?4 or (coalesce(?4, null) is null) )" +
            "and (a.spcode in ?5 or (coalesce(?5, null) is null) )" +
            "and (a.branch in ?6 or (coalesce(?6, null) is null) )")
    Area findByAreaId(String areaId,
                           String free,
                           String yesterday,
                           List<String> grid,
                           List<String> spcodeId,
                           List<String> businessOfficeId);


    /**
     * 通过areaId查找唯一的小区
     *
     * fka9350c89 小区编码
     */
    Area findByAreaId(String areaId);

    /**
     * 通过小区名称查找一批小区信息
     *
     * fk999cd340 小区名称
     */
    @Query("select a from Area a " +
            "where a.areaName like CONCAT('%',?1,'%') and a.date=?2 and a.paymentType='收费'" +
            "and (a.gridId in ?3 or (coalesce(?3, null) is null) )" +
            "and (a.spcode in ?4 or (coalesce(?4, null) is null) )" +
            "and (a.branch in ?5 or (coalesce(?5, null) is null) )")
    Page<Area> findByAreaNameLike(String areaName,
                                         String date,
                                         List<String> grid,
                                         List<String> spcodeId,
                                         List<String> businessOfficeId,
                                         Pageable pageable);



}
