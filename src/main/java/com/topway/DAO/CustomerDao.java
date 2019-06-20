package com.topway.DAO;

import com.topway.pojo.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by haizhi on 2019/5/23.
 */
public interface CustomerDao extends JpaRepository<Customer, String> {

    /** 通过客户手机号查询 */
    Customer findByPhone(String phone);

    /** 通过客户编码查询 */
    Customer findByCustomerId(String customerId);



    /** 通过客户名称模糊查询 */
    @Query("select distinct c " +
            "from Customer c " +
            "where c.customerName like concat('%', ?1 ,'%') " +
            "and c.customerId in (" +
            "   select u.customerId from User u " +
            "   where (u.gridId in ?2 or (coalesce(?2, null) is null) )" +
            "   and (u.spcode in ?3 or (coalesce(?3, null) is null) )" +
            "   and (u.branch in ?4 or (coalesce(?4, null) is null) )" +
            ") ")
    Page<Customer> findByCustomerNameLike(String customerName,
                                          List<String> gridId,
                                          List<String> spcode,
                                          List<String> branch,
                                          Pageable pageable);



    /** 通过客户手机号模糊查询 */
    @Query("select distinct c " +
            "from Customer c " +
            "where c.phone like concat('%', ?1 ,'%') " +
            "and c.customerId in (" +
            "   select u.customerId from User u " +
            "   where (u.gridId in ?2 or (coalesce(?2, null) is null) )" +
            "   and (u.spcode in ?3 or (coalesce(?3, null) is null) )" +
            "   and (u.branch in ?4 or (coalesce(?4, null) is null) )" +
            ") ")
    Page<Customer> findByPhoneLike(String phone,
                                   List<String> gridId,
                                   List<String> spcode,
                                   List<String> branch,
                                   Pageable pageable);



    /** 通过客户编码模糊查询 */
    @Query("select distinct c " +
            "from Customer c " +
            "where c.customerId like concat('%', ?1 ,'%') " +
            "and c.customerId in (" +
            "   select u.customerId from User u " +
            "   where (u.gridId in ?2 or (coalesce(?2, null) is null) )" +
            "   and (u.spcode in ?3 or (coalesce(?3, null) is null) )" +
            "   and (u.branch in ?4 or (coalesce(?4, null) is null) )" +
            ") ")
    Page<Customer> findByCustomerIdLike(String customerId,
                                        List<String> gridId,
                                        List<String> spcode,
                                        List<String> branch,
                                        Pageable pageable);







}
