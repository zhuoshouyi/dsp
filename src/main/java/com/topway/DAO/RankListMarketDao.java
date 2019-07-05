package com.topway.DAO;

import com.topway.dto.RankListContentDTO;
import com.topway.pojo.RankListMarket;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by haizhi on 2019/6/5.
 */
public interface RankListMarketDao extends JpaRepository<RankListMarket, Integer> {


    @Query(value = "select rank.develop_people, sum(rank.value) as sum_value " +
            "from rank_list_market rank " +
            "where rank.date=?1 " +
            "and (rank.station in (?2) ) " +
            "and (rank.grid_name in (?3) ) " +
            "group by rank.develop_people " +
            "order by sum(rank.value) desc " +
            "limit 10 ", nativeQuery = true)
    List<Object[]> findTop10(String date,
                             List<String> station,
                             List<String> grid);
}
