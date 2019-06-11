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

    @Query(value = "select develop_people, sum(value) " +
            "from rank_list_market " +
            "where station=?1 and grid=?2 and date=?3 " +
            "group by develop_people " +
            "order by sum(value) desc ", nativeQuery = true)
    List<RankListContentDTO> findByStationAndGrid(@Param("station") String station,
                                                  @Param("grid") String grid,
                                                  @Param("date") String date);

    @Query(value = "select develop_people, sum(value) " +
            "from rank_list_market " +
            "where grid=?2 and date=?3 " +
            "group by develop_people " +
            "order by sum(value) desc ", nativeQuery = true)
    List<RankListContentDTO> findByGrid(@Param("grid") String grid,
                                                  @Param("date") String date);

    @Query(value = "select new com.topway.dto.RankListContentDTO(rank.developPeople, sum(rank.value)) " +
            "from RankListMarket rank " +
            "where rank.date=?1 " +
            "group by rank.developPeople " +
            "order by sum(rank.value) desc ")
    List<RankListContentDTO> findTop10(@Param("date") String date);
}
