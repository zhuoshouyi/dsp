package com.topway.DAO;

import com.topway.pojo.WarningLoss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by haizhi on 2019/6/25.
 */
public interface WarningLossDao extends JpaRepository<WarningLoss, Integer> {

    @Query(value = "select (sum(wl.last_month_end_watch_num)-sum(wl.now_month_end_watch_num)) as watch_loss_num, " +
            "(sum(wl.last_month_end_wb_num)-sum(wl.now_month_end_wb_num)) as wb_loss_num " +
            "from warning_loss wl " +
            "where wl.grid_id in (?1) " +
            "and wl.date=?2 ", nativeQuery = true)
    Object[] findByGridIdAndDate(List<String> grid_id,
                                 String month);


    @Query(value = "select (sum(wl.last_month_end_watch_num)-sum(wl.now_month_end_watch_num)) as watch_loss_num, " +
            "(sum(wl.last_month_end_wb_num)-sum(wl.now_month_end_wb_num)) as wb_loss_num " +
            "from warning_loss wl " +
            "where wl.spcode in (?1) " +
            "and wl.branch in (?2) " +
            "and wl.date=?3 ", nativeQuery = true)
    Object[] findBySpcodeAndBranchAndDate(List<String> spcode,
                                          List<String> branch,
                                          String month);


}
