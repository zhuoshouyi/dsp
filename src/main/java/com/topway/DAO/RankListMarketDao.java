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


//    @Query(value = "select rank.station, sum(rank.value) as sum_value " +
//            "from rank_list_market rank " +
//            "where (rank.spcode in (?1)  or (coalesce(?1, null) is null )) " +
//            "and (rank.branch_office in (?2)  or (coalesce(?2, null) is null )) " +
//            "and (rank.grid_id in (?3) or (coalesce(?3, null) is null )) " +
//            "and rank.date=?4 " +
//            "group by rank.station " +
//            "order by sum_value desc " +
//            "limit 30 ", nativeQuery = true)
//    List<Object[]> findStationsBySpcodeAndBranchAndGrid(List<String> spcode,
//                                                        List<String> branch,
//                                                        List<String> grid,
//                                                        String date);

    @Query(value = "select * " +
            "from " +
            "(select @curRank\\:=@curRank +1 as id ,a.* from " +
            "   (select " +
            "       rank.develop_people, " +
            "       sum(rank.value) as sum_value " +
            "       from rank_list_market rank " +
            "       where rank.branch_office=coalesce(?1, rank.branch_office) " +
            "       and rank.station=coalesce(?2, rank.station) " +
            "       and rank.develop_people is not null " +
            "       and rank.develop_people<>'' " +
            "       and rank.date between ?4 and ?5 " +
            "       group by rank.develop_people " +
            "       order by sum_value desc) a , (select @curRank\\:=0) q) b " +
            "where b.id < 11 or b.develop_people=?3 ", nativeQuery = true)
    List<Object[]> findPersonByBranchAndStation(String branch,
                                                String station,
                                                String userName,
                                                String StartDate,
                                                String EndDate);


    @Query(value = "select " +
            "   rank.grid_name," +
            "   rank.grid_id, " +
            "   sum(rank.value) as sum_value " +
            "from rank_list_market rank " +
            "where rank.branch_office=coalesce(?1, rank.branch_office) " +
            "and rank.station=coalesce(?2, rank.station) " +
            "and rank.grid_name is not null " +
            "and rank.grid_name<>'' " +
            "and rank.date between ?3 and ?4 " +
            "group by rank.grid_name " +
            "order by sum_value desc " +
            " ", nativeQuery = true)
    List<Object[]> findGridByBranchAndStation(String branch,
                                              String station,
                                              String StartDate,
                                              String EndDate);

//    @Query(value = "select rank.develop_people, sum(rank.value) as sum_value " +
//            "from rank_list_market rank " +
//            "where rank.station=?1 " +
//            "and rank.date=?2 " +
//            "group by rank.develop_people " +
//            "order by sum_value desc ", nativeQuery = true)
//    List<Object[]> findGridByStation(String station,
//                                     String date);

}
