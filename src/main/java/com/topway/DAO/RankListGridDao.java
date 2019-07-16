package com.topway.DAO;

import com.topway.pojo.RankListGrid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by haizhi on 2019/6/5.
 */
public interface RankListGridDao extends JpaRepository<RankListGrid, Integer> {


    /** 网格报障数  维护站对比 */
    @Query(value = "select rank.station, sum(fault_num) as sum_num " +
            "from rank_list_grid rank " +
            "where (rank.spcode in (?1)  or (coalesce(?1, null) is null )) " +
            "and (rank.branch_office in (?2)  or (coalesce(?2, null) is null )) " +
            "and (rank.grid in (?3) or (coalesce(?3, null) is null )) " +
            "and rank.month=?4 " +
            "group by rank.station " +
            "order by sum_num desc " +
            "limit 30 ", nativeQuery = true)
    List<Object[]> findGridStation(List<String> spcode,
                                   List<String> branch,
                                   List<String> grid,
                                   String month);

    /** 网格报障数  网格对比 */
    @Query(value = "select rank.grid, sum(fault_num) as sum_num " +
            "from rank_list_grid rank " +
            "where rank.station=?1 " +
            "and rank.month=?2 " +
            "group by rank.grid " +
            "order by sum_num desc ", nativeQuery = true)
    List<Object[]> findGridGrid(String station, String month);
}
