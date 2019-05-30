package com.topway.DAO;

import com.topway.pojo.HistoryMarket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by haizhi on 2019/5/30.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HistoryMarketDaoTest {

    @Autowired
    HistoryMarketDao dao;

    @Test
    public void findByAreaIdOrderByCreateTimeDesc() throws Exception {
        List<HistoryMarket> historyMarketList = dao.findByAreaIdOrderByCreateTimeDesc("1110");
        System.out.println("");
    }

}