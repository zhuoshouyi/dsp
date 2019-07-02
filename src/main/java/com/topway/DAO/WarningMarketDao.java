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
            "where date=?1 " +
            "and grid_id in (?2)", nativeQuery = true)
    Object[] findByGridId(String date,
                          List<String> gridId);

}
