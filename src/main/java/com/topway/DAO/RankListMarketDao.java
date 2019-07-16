package com.topway.DAO;

import com.topway.pojo.RankListMarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by haizhi on 2019/6/5.
 */
public interface RankListMarketDao extends JpaRepository<RankListMarket, Integer> {


//    @Query(value = "select rank.develop_people, sum(rank.value) as sum_value " +
//            "from rank_list_market rank " +
//            "where rank.date=?1 " +
//            "and (rank.station in (?2) ) " +
//            "and (rank.grid_name in (?3) ) " +
//            "group by rank.develop_people " +
//            "order by sum(rank.value) desc " +
//            "limit 10 ", nativeQuery = true)
//    List<Object[]> findTop10(String date,
//                             List<String> station,
//                             List<String> grid);


    @Query(value = "select rank.station, sum(rank.value) as sum_value " +
            "from rank_list_market rank " +
            "where (rank.spcode in (?1)  or (coalesce(?1, null) is null )) " +
            "and (rank.branch_office in (?2)  or (coalesce(?2, null) is null )) " +
            "and (rank.grid_id in (?3) or (coalesce(?3, null) is null )) " +
            "and rank.date=?4 " +
            "group by rank.station " +
            "order by sum_value desc " +
            "limit 30 ", nativeQuery = true)
    List<Object[]> findStationsBySpcodeAndBranchAndGrid(List<String> spcode,
                                                        List<String> branch,
                                                        List<String> grid,
                                                        String date);


    @Query(value = "select rank.develop_people, sum(rank.value) as sum_value " +
            "from rank_list_market rank " +
            "where rank.station=?1 " +
            "and rank.date=?2 " +
            "group by rank.develop_people " +
            "order by sum_value desc ", nativeQuery = true)
    List<Object[]> findGridByStation(String station, String date);

}
