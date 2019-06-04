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
 * Created byizhi on 2019/4/28.
 */
public interface UserDao extends JpaRepository<User, Integer> {

    /** 通过用户编码模糊查询 */
//    List<User> findByFk74dd6ddcLike(String userId);
    List<User> findByUserId(String userId);

    /** 通过资源号模糊查询 */
//    List<User> findByFkdf1e945eLike(String deviceNo);
    List<User> findByDeviceNo(String deviceNo);

    /** 通过客户编码和资源号查询 */
//    User findByFk572f5a34AndFkdf1e945e(String customerId, String deviceNo);
    User findByCustomerIdAndDeviceNo(String customerId, String deviceNo);

    /** 通过客户编码模糊查询 */
//    List<User> findByFk572f5a34Like(String customId);
    List<User> findByCustomerIdLike(String customId);

    /** 通过客户编码查询 */
//    List<User> findByFk572f5a34(String customId);
    List<User> findByCustomerId(String customId);


//    /** 关联 User 和 Customer 表,通过 deviceNo 模糊查询. */
//    @Query("select new com.topway.dto.CustomerDTO(c.customerName, c.customerId, c.phone, c.paperNo, u.address, c.businessType) " +
//            "from User u, Customer c where u.fkc1f5a1c1=c.customerId and u.fkce29e60a like concat('%', ?1, '%')")
//    Page<CustomerDTO> findByDeviceNoLike(@Param("deviceNo") String deviceNo, Pageable pageable);

//    @Query("select distinct c from User u, Customer c where u.fk572f5a34=c.customerId and u.fkdf1e945e like concat('%', ?1, '%') ")
//    Page<Customer> findByDeviceNoToCustomer(@Param("deviceNo") String deviceNo, Pageable pageable);

    @Query("select distinct c from User u, Customer c where u.customerId=c.customerId and u.deviceNo like concat('%', ?1, '%') ")
    Page<Customer> findByDeviceNoToCustomer(@Param("deviceNo") String deviceNo, Pageable pageable);
}
