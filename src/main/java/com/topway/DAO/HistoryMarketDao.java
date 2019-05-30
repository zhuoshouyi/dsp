package com.topway.DAO;

import com.topway.pojo.HistoryMarket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by haizhi on 2019/5/29.
 */
public interface HistoryMarketDao extends JpaRepository<HistoryMarket, Integer> {

    List<HistoryMarket> findByAreaIdOrderByCreateTimeDesc(String areaId);
}
