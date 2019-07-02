package com.topway.DAO;

import com.topway.pojo.WarningService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by haizhi on 2019/6/25.
 */
public interface WarningServiceDao extends JpaRepository<WarningService, Integer> {


    // 24小时安装处理成功率
    @Query(value = "select sum(install_success24h_num)/sum(install_num) as install24 " +
            "from warning_service " +
            "where master_grid_id in (?1)", nativeQuery = true)
    Object[] findBy24(List<String> gridId);


    // 片区故障平均处理时长
    @Query(value = "select sum(region_fault_duration)/sum(region_fault_num) as region_average " +
            "from warning_service " +
            "where back_time=?1 " +
            "and master_grid_id in (?2)", nativeQuery = true)
    Object[] findByRegionAverage(String date,
                                 List<String> gridId);


    // 故障处理成功率
    @Query(value = "select (sum(watch_fault_timely_success_num)+sum(wb_fault_timely_success_num))/(sum(watch_fault_num)+sum(wb_fault_num)) " +
            "from warning_service " +
            "where back_time=?1 " +
            "and master_grid_id in (?2)", nativeQuery = true)
    Object[] findByFaultSuccess(String date,
                                List<String> gridId);



}
