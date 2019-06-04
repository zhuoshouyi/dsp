package com.topway.DAO;

import com.topway.pojo.Complaint;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by haizhi on 2019/5/27.
 */
public interface ComplaintDao extends JpaRepository<Complaint, Integer> {

    @Query(value = "select * from complaint c where c.user_id in (" +
            "select u.user_id from user u where u.customer_id=?1 and u.device_no=?2) " +
            "order by c.accept_time", nativeQuery = true)
    Page<Complaint> findJoinComplaintAndUser(@Param(value = "customerId") String customerId,
                                                  @Param(value = "deviceNo") String deviceNo,
                                                  Pageable pageable);


    @Query(value = "select * from complaint c where c.user_id in (" +
            "select u.user_id from user u where u.customer_id=?1 and u.device_no=?2) " +
            "and c.id=?3", nativeQuery = true)
    Complaint findJoinComplaintAndUserDetail(@Param(value = "customerId") String customerId,
                                                  @Param(value = "deviceNo") String deviceNo,
                                                  @Param(value = "id") String id);

}
