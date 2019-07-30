package com.topway.DAO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by haizhi on 2019/6/5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RankListFaultDaoTest {

   @Autowired
    RankListFaultDao dao;

    private String date = "2019-06-04 00:00:00";

    @Test
    public void test1(){
        List<Object[]> stationPerson = dao.find24Person("南山分公司", null, "王奇龙", "2019-05-04 00:00:00", "2019-06-04 00:00:00");
        System.out.println("");
    }

}