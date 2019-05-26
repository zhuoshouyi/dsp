package com.topway.DAO;

import com.topway.pojo.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by haizhi on 2019/5/23.
 */
public interface CustomerDao extends JpaRepository<Customer, String> {

    /** 通过客户手机号查询 */
    Customer findByPhone(String phone);

    /** 通过客户编码查询 */
    Customer findByCustomerId(String customerId);



    /** 通过客户名称模糊查询 */
    Page<Customer> findByCustomerNameLike(String customerName, Pageable pageable);

    /** 通过客户手机号模糊查询 */
    Page<Customer> findByPhoneLike(String phone, Pageable pageable);

    /** 通过客户编码模糊查询 */
    Page<Customer> findByCustomerIdLike(String customerId, Pageable pageable);







}
