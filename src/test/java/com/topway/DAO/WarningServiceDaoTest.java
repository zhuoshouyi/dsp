package com.topway.DAO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haizhi on 2019/6/25.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WarningServiceDaoTest {

    @Autowired
    WarningServiceDao dao;

    @Test
    public void findBy24() throws Exception {

        List<String> stringList = new ArrayList<>();
        stringList.add("621");

        Object[] daoBy24 = dao.findBy24(stringList);
        System.out.println("");
    }

    @Test
    public void findTest(){

        List<String> stringList = new ArrayList<>();
        stringList.add("1270");

        Object[] daoBy24 = dao.findByRegionAverage("2019-06-23 00:00:00", stringList);
        System.out.println("");
    }

}