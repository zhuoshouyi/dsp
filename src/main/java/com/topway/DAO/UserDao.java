package com.topway.DAO;

import com.topway.pojo.Customer;
import com.topway.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created byizhi on 2019/4/28.
 */
public interface UserDao extends JpaRepository<User, Integer> {

    /** 通过用户编码模糊查询 */
    List<User> findByUserId(String userId);

    /** 通过资源号模糊查询 */
    List<User> findByDeviceNoLike(String deviceNo);

    /** 通过客户编码和资源号查询 */
    User findByCustomerIdAndDeviceNo(String customerId, String deviceNo);

    /** 通过客户编码模糊查询 */
    List<User> findByCustomerIdLike(String customId);

    /** 通过客户编码查询 */
    List<User> findByCustomerId(String customId);


    /** 通过资源号模糊查询 */
    @Query("select distinct c " +
            "from Customer c " +
            "where c.customerId in (select u.customerId from User u " +
                "where u.deviceNo like concat('%', ?1, '%')  " +
                "and (u.gridId in ?2 or (coalesce(?2, null) is null) )" +
                "and (u.spcode in ?3 or (coalesce(?3, null) is null) )" +
                "and (u.branch in ?4 or (coalesce(?4, null) is null) )" +
            ") " )
    Page<Customer> findByDeviceNoToCustomer(String deviceNo,
                                            List<String> gridId,
                                            List<String> spcodeId,
                                            List<String> businessOfficeId,
                                            Pageable pageable);

    /** 通过网格查询维护站 */
    @Query(value = "select distinct u.station_name from user u " +
            "where grid_id in (?1) " +
            "and u.station_name is not null", nativeQuery = true)
    List<String> findByGridId(List<String> gridId);

//    /** 通过分公司查询维护站 */
//    @Query(value = "", nativeQuery = true)
//    List<String> findByBranch(List<String> branch);


    /** 公司领导通过运营商和区域分公司查询维护站 */
    @Query(value = "select distinct u.station_name from user u " +
            "where (u.spcode in ?1 or (coalesce(?1, null) is null) )" +
            "and (u.branch in ?2 or (coalesce(?2, null) is null) ) " +
            "and u.station_name is not null", nativeQuery = true)
    List<String> findStationBySpcodeAndBranch(List<String> spcode,
                                              List<String> branch);


    /** 通过维护站查询网格 */
    @Query(value = "select distinct u.grid from user u " +
            "where u.station_name=?1 " +
            "and u.grid is not null", nativeQuery = true)
    List<String> findByStation(String station);

}
