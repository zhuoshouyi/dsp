package com.topway.DAO;

import com.topway.pojo.RankListLoss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by haizhi on 2019/6/5.
 */
public interface RankListLossDao extends JpaRepository<RankListLoss, Integer> {

//    /** 数字电视用户流失数/率 */
//    @Query(value = "select rank.branch_office, coalesce((sum(now_month_watch_num) - sum(last_month_watch_num)), 0) as sum_num, " +
//            "coalesce((sum(now_month_watch_num) - sum(last_month_watch_num)) / now_month_watch_num, 0) as per_num " +
//            "from rank_list_loss rank " +
//            "where rank.month=?1 " +
//            "and (rank.station in (?2) ) " +
//            "and (rank.grid in (?3) ) " +
//            "group by rank.branch_office " +
//            "order by sum_num desc " +
//            "limit 10", nativeQuery = true)
//    List<Object[]> findWatchLoss(String date,
//                                 List<String> station,
//                                 List<String> grid);
//
//
//    /** 20M宽带流失数/率 */
//    @Query(value = "select rank.branch_office, coalesce(sum(now_month20m_num) - sum(last_month20m_num),0) as sum_num, " +
//            "coalesce((sum(now_month20m_num) - sum(last_month20m_num)) / now_month20m_num , 0) as per_num " +
//            "from rank_list_loss rank " +
//            "where rank.month=?1 " +
//            "and (rank.station in (?2) ) " +
//            "and (rank.grid in (?3) ) " +
//            "group by rank.branch_office " +
//            "order by sum_num desc " +
//            "limit 10", nativeQuery = true)
//    List<Object[]> find20MLoss(String date,
//                               List<String> station,
//                               List<String> grid);
//
//    /** 100M宽带流失数/率 */
//    @Query(value = "select rank.branch_office, coalesce((sum(now_month100m_num) - sum(last_month100m_num)), 0) as sum_num, " +
//            "coalesce((sum(now_month100m_num) - sum(last_month100m_num)) / now_month100m_num, 0) as per_num " +
//            "from rank_list_loss rank " +
//            "where rank.month=?1 " +
//            "and (rank.station in (?2) ) " +
//            "and (rank.grid in (?3) ) " +
//            "group by rank.branch_office " +
//            "order by sum_num desc " +
//            "limit 10", nativeQuery = true)
//    List<Object[]> find100MLoss(String date,
//                                List<String> station,
//                                List<String> grid);


    // 2019-07-10 更新
//    /** 数字电视用户流失数/率  维护站对比 */
//    @Query(value = "select rank.station, coalesce((sum(now_month_watch_num) - sum(last_month_watch_num)), 0) as sum_num, " +
//            "coalesce((sum(now_month_watch_num) - sum(last_month_watch_num)) / now_month_watch_num, 0) as per_num " +
//            "from rank_list_loss rank " +
//            "where (rank.spcode in (?1)  or (coalesce(?1, null) is null )) " +
//            "and (rank.branch_office in (?2)  or (coalesce(?2, null) is null )) " +
//            "and (rank.grid_id in (?3) or (coalesce(?3, null) is null )) " +
//            "and rank.month=?4 " +
//            "group by rank.station " +
//            "order by sum_num desc " +
//            "limit 30 ", nativeQuery = true)
//    List<Object[]> findWatchLossStation(List<String> spcode,
//                                        List<String> branch,
//                                        List<String> grid,
//                                        String month);
//
//    /** 数字电视用户流失数/率  网格对比 */
//    @Query(value = "select rank.grid_name, coalesce((sum(now_month_watch_num) - sum(last_month_watch_num)), 0) as sum_num, " +
//            "coalesce((sum(now_month_watch_num) - sum(last_month_watch_num)) / now_month_watch_num, 0) as per_num " +
//            "from rank_list_loss rank " +
//            "where rank.station=?1 " +
//            "and rank.month=?2 " +
//            "group by rank.grid_name " +
//            "order by sum_num desc ", nativeQuery = true)
//    List<Object[]> findWatchLossGrid(String station, String date);



//    /** 20M宽带流失数/率  维护站对比 */
//    @Query(value = "select rank.station, coalesce(sum(now_month20m_num) - sum(last_month20m_num),0) as sum_num, " +
//            "coalesce((sum(now_month20m_num) - sum(last_month20m_num)) / now_month20m_num , 0) as per_num " +
//            "from rank_list_loss rank " +
//            "where (rank.spcode in (?1)  or (coalesce(?1, null) is null )) " +
//            "and (rank.branch_office in (?2)  or (coalesce(?2, null) is null )) " +
//            "and (rank.grid_id in (?3) or (coalesce(?3, null) is null )) " +
//            "and rank.month=?4 " +
//            "group by rank.station " +
//            "order by sum_num desc " +
//            "limit 30 ", nativeQuery = true)
//    List<Object[]> find20MLossStation(List<String> spcode,
//                                      List<String> branch,
//                                      List<String> grid,
//                                      String month);
//
//    /** 20M宽带流失数/率  网格对比 */
//    @Query(value = "select rank.grid_name, coalesce(sum(now_month20m_num) - sum(last_month20m_num),0) as sum_num, " +
//            "coalesce((sum(now_month20m_num) - sum(last_month20m_num)) / now_month20m_num , 0) as per_num " +
//            "from rank_list_loss rank " +
//            "where rank.station=?1 " +
//            "and rank.month=?2 " +
//            "group by rank.grid_name " +
//            "order by sum_num desc ", nativeQuery = true)
//    List<Object[]> find20MLossGrid(String station, String date);





//    /** 100M宽带流失数/率  维护站对比 */
//    @Query(value = "select rank.station, coalesce((sum(now_month100m_num) - sum(last_month100m_num)), 0) as sum_num, " +
//            "coalesce((sum(now_month100m_num) - sum(last_month100m_num)) / now_month100m_num, 0) as per_num " +
//            "from rank_list_loss rank " +
//            "where (rank.spcode in (?1)  or (coalesce(?1, null) is null )) " +
//            "and (rank.branch_office in (?2)  or (coalesce(?2, null) is null )) " +
//            "and (rank.grid_id in (?3) or (coalesce(?3, null) is null )) " +
//            "and rank.month=?4 " +
//            "group by rank.station " +
//            "order by sum_num desc " +
//            "limit 30 ", nativeQuery = true)
//    List<Object[]> find100MLossStation(List<String> spcode,
//                                       List<String> branch,
//                                       List<String> grid,
//                                       String month);
//
//    /** 100M宽带流失数/率  网格对比 */
//    @Query(value = "select rank.grid_name, coalesce((sum(now_month100m_num) - sum(last_month100m_num)), 0) as sum_num, " +
//            "coalesce((sum(now_month100m_num) - sum(last_month100m_num)) / now_month100m_num, 0) as per_num " +
//            "from rank_list_loss rank " +
//            "where rank.station=?1 " +
//            "and rank.month=?2 " +
//            "group by rank.grid_name " +
//            "order by sum_num desc ", nativeQuery = true)
//    List<Object[]> find100MLossGrid(String station, String date);




    // 2019-07-23 更新
    /** 数字电视用户流失数/率  网格对比 */
    @Query(value = "select " +
            "   rank.grid, " +
            "   coalesce((sum(now_month_watch_num) - sum(last_month_watch_num)), 0) as sum_num," +
            "   coalesce((sum(now_month_watch_num) - sum(last_month_watch_num)) / now_month_watch_num, 0) as per_num " +
            "from rank_list_loss rank " +
            "where rank.branch_office=coalesce(?1, rank.branch_office) " +
            "and rank.station=coalesce(?2, rank.station) " +
            "and rank.grid is not null " +
            "and rank.grid<>'' " +
            "and rank.month=?3 " +
            "group by rank.grid " +
            "order by sum_num desc " +
            " ", nativeQuery = true)
    List<Object[]> findWatchLossGrid(String branch,
                                     String station,
                                     String month);


    /** 20M宽带流失数/率  网格对比 */
    @Query(value = "select " +
            "   rank.grid, " +
            "   coalesce(sum(now_month20m_num) - sum(last_month20m_num),0) as sum_num, " +
            "   coalesce((sum(now_month20m_num) - sum(last_month20m_num)) / now_month20m_num , 0) as per_num " +
            "from rank_list_loss rank " +
            "where rank.branch_office=coalesce(?1, rank.branch_office) " +
            "and rank.station=coalesce(?2, rank.station) " +
            "and rank.grid is not null " +
            "and rank.grid<>'' " +
            "and rank.month=?3 " +
            "group by rank.grid " +
            "order by sum_num desc " +
            " ", nativeQuery = true)
    List<Object[]> find20MLossGrid(String branch,
                                   String station,
                                   String month);


    /** 100M宽带流失数/率  网格对比 */
    @Query(value = "select " +
            "   rank.grid, " +
            "   coalesce((sum(now_month100m_num) - sum(last_month100m_num)), 0) as sum_num, " +
            "   coalesce((sum(now_month100m_num) - sum(last_month100m_num)) / now_month100m_num, 0) as per_num " +
            "from rank_list_loss rank " +
            "where rank.branch_office=coalesce(?1, rank.branch_office) " +
            "and rank.station=coalesce(?2, rank.station) " +
            "and rank.grid is not null " +
            "and rank.grid<>'' " +
            "and rank.month=?3 " +
            "group by rank.grid " +
            "order by sum_num desc " +
            " ", nativeQuery = true)
    List<Object[]> find100MLossGrid(String branch,
                                    String station,
                                    String month);



}
