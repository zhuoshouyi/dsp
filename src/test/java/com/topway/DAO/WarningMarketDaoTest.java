package com.topway.DAO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haizhi on 2019/6/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WarningMarketDaoTest {

    @Autowired
    WarningMarketDao dao;

    @Test
    public void findByGridId() throws Exception {

        List<String> stringList = new ArrayList<>();
        stringList.add("128");


        Object[] daoByGridId = dao.findByGridId(stringList, "2019-05-21 00:00:00", "2019-06-20 00:00:00");
        System.out.println("");
    }

}