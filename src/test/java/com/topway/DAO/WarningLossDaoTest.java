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
public class WarningLossDaoTest {

    @Autowired
    WarningLossDao dao;

    @Test
    public void findByGridIdAndDate() throws Exception {

        List<String> stringList = new ArrayList<>();
        stringList.add("644");
        stringList.add("4198");

//        List<WarningLoss> warningLossList = dao.findByDate("2019-05");
        Object[] objects = dao.findByGridIdAndDate(stringList, "2019-05");
        Object[] object0 = (Object[])objects[0];
        System.out.println("objects:" + object0[0]);
    }

}