package com.topway.DAO;

import com.topway.pojo.Customer;
import com.topway.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by haizhi on 2019/5/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    UserDao dao;

    @Test
    public void findByFk2610e16bLike() throws Exception {

        List<User> userList= dao.findByFk2610e16bLike("%3150%");
        System.out.println("total:" + userList.size());
        for (User user : userList){
            System.out.println(user.toString());
        }
    }

    @Test
    public void findByFkce29e60aLike() throws Exception {

    }

    @Test
    public void findByFkc1f5a1c1Like() throws Exception {

    }

    @Test
    public void findByDeviceNoLike() throws Exception {
        Pageable pageable = new PageRequest(0,1);
//        Page<CustomerDTO> customerDTOPage = dao.findByDeviceNo("666", pageable);
        Page<Customer> customerPage = dao.findByDeviceNoToCustomer("666", pageable);
        System.out.println(customerPage.getContent());
    }

}