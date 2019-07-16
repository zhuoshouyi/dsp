package com.topway.DAO;

import com.topway.pojo.RankListFault;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by haizhi on 2019/6/5.
 */
public interface RankListFaultDao extends JpaRepository<RankListFault, Integer> {

//    @Query(value = "select new com.topway.dto.RankListContentDTO(rank.followBackPeoName, (sum(rank.installSuccess24hour)/sum(rank.installNum))) " +
//            "from RankListFault rank " +
//            "where rank.date=?1 " +
//            "group by rank.followBackPeoName " +
//            "order by (sum(rank.installSuccess24hour)/sum(rank.installNum)) desc ")
//    List<RankListContentDTO> findOne(@Param("date") String date);


//    /** 24安装及时处理率(数字) */
//    @Query(value = "select rank.follow_back_peo_name, sum(rank.install_success24hour)/coalesce(sum(rank.install_num), 0) as sum_num " +
//            "from rank_list_fault rank " +
//            "where rank.back_time=?1 " +
//            "and rank.follow_back_depa_name in (?2) " +
//            "and rank.master_grid in (?3)" +
//            "group by rank.follow_back_peo_name " +
//            "order by sum_num desc " +
//            "limit 10", nativeQuery = true)
//    List<Object[]> find24(String date,
//                          List<String> station,
//                          List<String> grid);
//
//    /** 48安装及时处理率(数字) */
//    @Query(value = "select rank.follow_back_peo_name, sum(rank.install_success48hour)/coalesce(sum(rank.install_num), 0) as sum_num " +
//            "from rank_list_fault rank " +
//            "where rank.back_time=?1 " +
//            "and rank.follow_back_depa_name in (?2) " +
//            "and rank.master_grid in (?3)" +
//            "group by rank.follow_back_peo_name " +
//            "order by sum_num desc " +
//            "limit 10", nativeQuery = true)
//    List<Object[]> find48(String date,
//                          List<String> station,
//                          List<String> grid);
//
//    /** 故障及时处理成功率(数字) */
//    @Query(value = "select rank.follow_back_peo_name, sum(rank.watch_fault_success_num)/coalesce(sum(rank.watch_fault_num), 0) as sum_num " +
//            "from rank_list_fault rank " +
//            "where rank.back_time=?1 " +
//            "and rank.follow_back_depa_name in (?2) " +
//            "and rank.master_grid in (?3)" +
//            "group by rank.follow_back_peo_name " +
//            "order by sum_num desc " +
//            "limit 10", nativeQuery = true)
//    List<Object[]> findInTimeWatch(String date,
//                                   List<String> station,
//                                   List<String> grid);
//
//    /** 故障及时处理成功率(宽带) */
//    @Query(value = "select rank.follow_back_peo_name, sum(rank.network_fault_success_num)/coalesce(sum(rank.network_fault_num), 0) as sum_num " +
//            "from rank_list_fault rank " +
//            "where rank.back_time=?1 " +
//            "and rank.follow_back_depa_name in (?2) " +
//            "and rank.master_grid in (?3)" +
//            "group by rank.follow_back_peo_name " +
//            "order by sum_num desc " +
//            "limit 10", nativeQuery = true)
//    List<Object[]> findInTimeBroad(String date,
//                                   List<String> station,
//                                   List<String> grid);
//
//    /** 重复故障率 */
//    @Query(value = "select rank.follow_back_peo_name, sum(rank.repeat_num)/coalesce(sum(rank.install_num), 0) as sum_num " +
//            "from rank_list_fault rank " +
//            "where rank.back_time=?1 " +
//            "and rank.follow_back_depa_name in (?2) " +
//            "and rank.master_grid in (?3)" +
//            "group by rank.follow_back_peo_name " +
//            "order by sum_num desc " +
//            "limit 10", nativeQuery = true)
//    List<Object[]> findRepeat(String date,
//                              List<String> station,
//                              List<String> grid);


    /** 24安装及时处理率(数字)  维护站对比 */
    @Query(value = "select rank.follow_back_depa_name, sum(rank.install_success24hour)/coalesce(sum(rank.install_num), 0) as sum_num " +
            "from rank_list_fault rank " +
            "where (rank.master_spcode in (?1)  or (coalesce(?1, null) is null )) " +
            "and (rank.master_region in (?2)  or (coalesce(?2, null) is null )) " +
            "and (rank.master_grid_id in (?3) or (coalesce(?3, null) is null )) " +
            "and rank.back_time=?4 " +
            "group by rank.follow_back_peo_name " +
            "order by sum_num desc " +
            "limit 30 ", nativeQuery = true)
    List<Object[]> find24Station(List<String> spcode,
                                 List<String> branch,
                                 List<String> grid,
                                 String date);

    /** 24安装及时处理率(数字)  网格对比 */
    @Query(value = "select rank.master_grid, sum(rank.install_success24hour)/coalesce(sum(rank.install_num), 0) as sum_num " +
            "from rank_list_fault rank " +
            "where rank.follow_back_depa_name=?1 " +
            "and rank.back_time=?2 " +
            "group by rank.master_grid " +
            "order by sum_num desc ", nativeQuery = true)
    List<Object[]> find24Grid(String station, String date);



    /** 48安装及时处理率(数字)  维护站对比 */
    @Query(value = "select rank.follow_back_depa_name, sum(rank.install_success48hour)/coalesce(sum(rank.install_num), 0) as sum_num " +
            "from rank_list_fault rank " +
            "where (rank.master_spcode in (?1)  or (coalesce(?1, null) is null )) " +
            "and (rank.master_region in (?2)  or (coalesce(?2, null) is null )) " +
            "and (rank.master_grid_id in (?3) or (coalesce(?3, null) is null )) " +
            "and rank.back_time=?4 " +
            "group by rank.follow_back_depa_name " +
            "order by sum_num desc " +
            "limit 30 ", nativeQuery = true)
    List<Object[]> find48Station(List<String> spcode,
                                 List<String> branch,
                                 List<String> grid,
                                 String date);

    /** 48安装及时处理率(数字)  网格对比 */
    @Query(value = "select rank.master_grid, sum(rank.install_success48hour)/coalesce(sum(rank.install_num), 0) as sum_num " +
            "from rank_list_fault rank " +
            "where rank.follow_back_depa_name=?1 " +
            "and rank.back_time=?2 " +
            "group by rank.master_grid " +
            "order by sum_num desc ", nativeQuery = true)
    List<Object[]> find48Grid(String station, String date);




    /** 故障及时处理成功率(数字) 维护站对比 */
    @Query(value = "select rank.follow_back_depa_name, sum(rank.watch_fault_success_num)/coalesce(sum(rank.watch_fault_num), 0) as sum_num " +
            "from rank_list_fault rank " +
            "where (rank.master_spcode in (?1)  or (coalesce(?1, null) is null )) " +
            "and (rank.master_region in (?2)  or (coalesce(?2, null) is null )) " +
            "and (rank.master_grid_id in (?3) or (coalesce(?3, null) is null )) " +
            "and rank.back_time=?4 " +
            "group by rank.follow_back_depa_name " +
            "order by sum_num desc " +
            "limit 30 ", nativeQuery = true)
    List<Object[]> findInTimeWatchStation(List<String> spcode,
                                          List<String> branch,
                                          List<String> grid,
                                          String date);

    /** 故障及时处理成功率(数字)  网格对比 */
    @Query(value = "select rank.master_grid, sum(rank.watch_fault_success_num)/coalesce(sum(rank.watch_fault_num), 0) as sum_num " +
            "from rank_list_fault rank " +
            "where rank.follow_back_depa_name=?1 " +
            "and rank.back_time=?2 " +
            "group by rank.master_grid " +
            "order by sum_num desc ", nativeQuery = true)
    List<Object[]> findInTimeWatchGrid(String station, String date);




    /** 故障及时处理成功率(宽带)  维护站对比 */
    @Query(value = "select rank.follow_back_depa_name, sum(rank.network_fault_success_num)/coalesce(sum(rank.network_fault_num), 0) as sum_num " +
            "from rank_list_fault rank " +
            "where (rank.master_spcode in (?1)  or (coalesce(?1, null) is null )) " +
            "and (rank.master_region in (?2)  or (coalesce(?2, null) is null )) " +
            "and (rank.master_grid_id in (?3) or (coalesce(?3, null) is null )) " +
            "and rank.back_time=?4 " +
            "group by rank.follow_back_depa_name " +
            "order by sum_num desc " +
            "limit 30 ", nativeQuery = true)
    List<Object[]> findInTimeBroadStation(List<String> spcode,
                                          List<String> branch,
                                          List<String> grid,
                                          String date);

    /** 故障及时处理成功率(宽带)  网格对比 */
    @Query(value = "select rank.master_grid, sum(rank.network_fault_success_num)/coalesce(sum(rank.network_fault_num), 0) as sum_num " +
            "from rank_list_fault rank " +
            "where rank.follow_back_depa_name=?1 " +
            "and rank.back_time=?2 " +
            "group by rank.master_grid " +
            "order by sum_num desc ", nativeQuery = true)
    List<Object[]> findInTimeBroadGrid(String station, String date);



    /** 重复故障率  维护站对比 */
    @Query(value = "select rank.follow_back_depa_name, sum(rank.repeat_num)/coalesce(sum(rank.install_num), 0) as sum_num " +
            "from rank_list_fault rank " +
            "where (rank.master_spcode in (?1)  or (coalesce(?1, null) is null )) " +
            "and (rank.master_region in (?2)  or (coalesce(?2, null) is null )) " +
            "and (rank.master_grid_id in (?3) or (coalesce(?3, null) is null )) " +
            "and rank.back_time=?4 " +
            "group by rank.follow_back_depa_name " +
            "order by sum_num desc " +
            "limit 30 ", nativeQuery = true)
    List<Object[]> findRepeatStation(List<String> spcode,
                                     List<String> branch,
                                     List<String> grid,
                                     String date);

    /** 重复故障率  网格对比 */
    @Query(value = "select rank.master_grid, sum(rank.repeat_num)/coalesce(sum(rank.install_num), 0) as sum_num " +
            "from rank_list_fault rank " +
            "where rank.follow_back_depa_name=?1 " +
            "and rank.back_time=?2 " +
            "group by rank.master_grid " +
            "order by sum_num desc ", nativeQuery = true)
    List<Object[]> findRepeatGrid(String station, String date);
}
