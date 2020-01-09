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
            "where ((coalesce(?1, null) is null) or (master_grid_id in (?1) ) ) " +
            "and ( ((coalesce(?2, null) is null) or (master_spcode in (?2) ) ) " +
            "   or ( (coalesce(?3, null) is null) or (master_branch in (?3) ) ) " +
            ") " +
            "and back_time>=?4 " +
            "and back_time<=?5 ", nativeQuery = true)
    Object[] findBy24(List<String> gridId,
                      List<String> operator,
                      List<String> branch,
                      String timeStart,
                      String timeEnd);


    // 片区故障平均处理时长
    @Query(value = "select sum(region_fault_duration)/sum(region_fault_num) as region_average " +
            "from warning_service " +
            "where ((coalesce(?1, null) is null) or (master_grid_id in (?1) ) ) " +
            "and ( ((coalesce(?2, null) is null) or (master_spcode in (?2) ) ) " +
            "   or ( (coalesce(?3, null) is null) or (master_branch in (?3) ) ) " +
            ") " +
            "and back_time>=?4 " +
            "and back_time<=?5 ", nativeQuery = true)
    Object[] findByRegionAverage(List<String> gridId,
                                 List<String> operator,
                                 List<String> branch,
                                 String dateStart,
                                 String dateEnd);


    // 故障处理成功率
    @Query(value = "select (sum(watch_fault_timely_success_num)+sum(wb_fault_timely_success_num))/(sum(watch_fault_num)+sum(wb_fault_num)) " +
            "from warning_service " +
            "where ((coalesce(?1, null) is null) or (master_grid_id in (?1) ) ) " +
            "and ( ((coalesce(?2, null) is null) or (master_spcode in (?2) ) ) " +
            "   or ( (coalesce(?3, null) is null) or (master_branch in (?3) ) ) " +
            ") " +
            "and back_time>=?4 " +
            "and back_time<=?5 ", nativeQuery = true)
    Object[] findByFaultSuccess(List<String> gridId,
                                List<String> operator,
                                List<String> branch,
                                String dateStart,
                                String dateEnd);



}
