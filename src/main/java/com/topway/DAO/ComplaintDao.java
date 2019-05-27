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

    @Query("select c from Complaint c, User u " +
            "where u.fk572f5a34=?1 and u.fkdf1e945e=?2 and u.fk74dd6ddc=c.fk4b0f0ba1 " +
            "order by c.fk0b5c4bd1 desc")
    Page<Complaint> findJoinComplaintAndUser(@Param(value = "customerId") String customerId,
                                             @Param(value = "deviceNo") String deviceNo,
                                             Pageable pageable);


    @Query("select c from Complaint c, User u " +
            "where u.fk572f5a34=?1 and u.fkdf1e945e=?2 and u.fk74dd6ddc=c.fk4b0f0ba1 and c.id=?3 ")
    Complaint findJoinComplaintAndUserDetail(@Param(value = "customerId") String customerId,
                                             @Param(value = "deviceNo") String deviceNo,
                                             @Param(value = "id") String id);

}
