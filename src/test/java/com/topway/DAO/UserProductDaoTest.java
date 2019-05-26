package com.topway.DAO;

import com.topway.pojo.UserProduct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by haizhi on 2019/5/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserProductDaoTest {

    @Autowired
    UserProductDao dao;

    @Test
    public void findJoin() throws Exception {

        PageRequest pageRequest = new PageRequest(0, 5);
        Page<UserProduct> userProductPage = dao.findJoinUserAndUserProduct("12744888", "68719498946", pageRequest);
        System.out.println("");
    }

}