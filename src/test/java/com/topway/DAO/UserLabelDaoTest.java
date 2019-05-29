package com.topway.DAO;

import com.topway.pojo.UserLabel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by haizhi on 2019/5/29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserLabelDaoTest {

    @Autowired
    UserLabelDao dao;

    @Test
    public void findByCustomerIdOrderByCreateTime() throws Exception {
        List<UserLabel> userLabelList = dao.findByCustomerIdOrderByCreateTimeDesc("123456");
        System.out.println("");
    }

}