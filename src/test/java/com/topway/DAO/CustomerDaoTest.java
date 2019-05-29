package com.topway.DAO;

import com.topway.pojo.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by haizhi on 2019/5/29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerDaoTest {

    @Autowired
    CustomerDao dao;

    @Test
    public void findByCustomerId() throws Exception {
        Customer customer = dao.findByCustomerId("123456");
        System.out.println("");
    }

}