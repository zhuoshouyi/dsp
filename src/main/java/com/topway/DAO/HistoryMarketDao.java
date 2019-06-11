package com.topway.DAO;

import com.topway.pojo.HistoryMarket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by haizhi on 2019/5/29.
 */
public interface HistoryMarketDao extends JpaRepository<HistoryMarket, Integer> {

    Page<HistoryMarket> findByAreaIdOrderByCreateTimeDesc(String areaId, Pageable pageable);
}
