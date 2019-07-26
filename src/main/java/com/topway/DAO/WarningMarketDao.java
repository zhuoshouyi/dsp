package com.topway.DAO;

import com.topway.pojo.WarningMarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by haizhi on 2019/6/25.
 */
public interface WarningMarketDao extends JpaRepository<WarningMarket, Integer> {

    @Query(value = "select sum(market_effect_num) as market_effect_num " +
            "from warning_market " +
            "where grid_id in (?1) " +
            "and date>=?2 " +
            "and date<=?3 ", nativeQuery = true)
    Object[] findByGridId(List<String> gridId,
                          String timeStart,
                          String timeEnd);

}
