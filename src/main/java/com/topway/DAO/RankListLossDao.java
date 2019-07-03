package com.topway.DAO;

import com.topway.pojo.RankListLoss;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by haizhi on 2019/6/5.
 */
public interface RankListLossDao extends JpaRepository<RankListLoss, Integer> {

    /** 数字电视用户流失数/率 */
    @Query(value = "select rank.branch_office, coalesce((sum(now_month_watch_num) - sum(last_month_watch_num)), 0) as sum_num, " +
            "coalesce((sum(now_month_watch_num) - sum(last_month_watch_num)) / now_month_watch_num, 0) as per_num " +
            "from rank_list_loss rank " +
            "where rank.month=?1 " +
            "and (rank.station in (?2) ) " +
            "and (rank.grid in (?3) ) " +
            "group by rank.branch_office " +
            "order by sum_num desc " +
            "limit 10", nativeQuery = true)
    List<Object[]> findWatchLoss(String date,
                                 List<String> station,
                                 List<String> grid);


    /** 20M宽带流失数/率 */
    @Query(value = "select rank.branch_office, coalesce(sum(now_month20m_num) - sum(last_month20m_num),0) as sum_num, " +
            "coalesce((sum(now_month20m_num) - sum(last_month20m_num)) / now_month20m_num , 0) as per_num " +
            "from rank_list_loss rank " +
            "where rank.month=?1 " +
            "and (rank.station in (?2) ) " +
            "and (rank.grid in (?3) ) " +
            "group by rank.branch_office " +
            "order by sum_num desc " +
            "limit 10", nativeQuery = true)
    List<Object[]> find20MLoss(String date,
                               List<String> station,
                               List<String> grid);

    /** 100M宽带流失数/率 */
    @Query(value = "select rank.branch_office, coalesce((sum(now_month100m_num) - sum(last_month100m_num)), 0) as sum_num, " +
            "coalesce((sum(now_month100m_num) - sum(last_month100m_num)) / now_month100m_num, 0) as per_num " +
            "from rank_list_loss rank " +
            "where rank.month=?1 " +
            "and (rank.station in (?2) ) " +
            "and (rank.grid in (?3) ) " +
            "group by rank.branch_office " +
            "order by sum_num desc " +
            "limit 10", nativeQuery = true)
    List<Object[]> find100MLoss(String date,
                                List<String> station,
                                List<String> grid);

}
