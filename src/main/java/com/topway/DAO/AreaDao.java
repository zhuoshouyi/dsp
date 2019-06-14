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
    Area findByFka9350c89AndFk560a959bAndFkfceb956f(String areaId, String fk560a959b, String yesterday);


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
            "where a.fka9350c89=?1 and a.fk560a959b=?2 and a.fkfceb956f=?3 " +
            "and a.fk89cc3f4d in (coalesce(?4, a.fk89cc3f4d)) " +
            "and a.fkeef0a02b in (coalesce(?5, a.fkeef0a02b)) " +
            "and a.fk93e2d7ec in (coalesce(?6, a.fk93e2d7ec))")
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
    Area findByFka9350c89(String areaId);

    /**
     * 通过小区名称查找一批小区信息
     *
     * fk999cd340 小区名称
     */
    @Query("select a from Area a " +
            "where a.fk999cd340 like CONCAT('%',?1,'%') and a.fkfceb956f=?2 and a.fk560a959b='收费'" +
            "and a.fk89cc3f4d in (coalesce(?3, a.fk89cc3f4d)) " +
            "and a.fkeef0a02b in (coalesce(?4, a.fkeef0a02b)) " +
            "and a.fk93e2d7ec in (coalesce(?5, a.fk93e2d7ec))")
    Page<Area> findByFk999cd340Like(String areaName,
                                    String date,
                                    List<String> grid,
                                    List<String> spcodeId,
                                    List<String> businessOfficeId,
                                    Pageable pageable);


    // "and a.fk89cc3f4d in (coalesce(?3, a.fk89cc3f4d)) " +

}
