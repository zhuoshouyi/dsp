package com.topway.DAO;

import com.topway.pojo.Customer;
import com.topway.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by haizhi on 2019/4/28.
 */
public interface UserDao extends JpaRepository<User, String> {

    /** 通过用户编码模糊查询 */
    List<User> findByFk2610e16bLike(String userId);

    /** 通过资源号模糊查询 */
    List<User> findByFkce29e60aLike(String deviceNo);

    /** 通过资源号查询 */
    User findByFkc1f5a1c1AndFkce29e60a(String customerId, String deviceNo);

    /** 通过客户编码模糊查询 */
    List<User> findByFkc1f5a1c1Like(String customId);

    /** 通过客户编码查询 */
    List<User> findByFkc1f5a1c1(String customId);


//    /** 关联 User 和 Customer 表,通过 deviceNo 模糊查询. */
//    @Query("select new com.topway.dto.CustomerDTO(c.customerName, c.customerId, c.phone, c.paperNo, u.address, c.businessType) " +
//            "from User u, Customer c where u.fkc1f5a1c1=c.customerId and u.fkce29e60a like concat('%', ?1, '%')")
//    Page<CustomerDTO> findByDeviceNoLike(@Param("deviceNo") String deviceNo, Pageable pageable);

    @Query("select distinct c from User u, Customer c where u.fkc1f5a1c1=c.customerId and u.fkce29e60a like concat('%', ?1, '%') ")
    Page<Customer> findByDeviceNoToCustomer(@Param("deviceNo") String deviceNo, Pageable pageable);

}
