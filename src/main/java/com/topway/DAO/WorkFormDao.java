package com.topway.DAO;

import com.topway.pojo.WorkForm;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by haizhi on 2019/5/27.
 */
public interface WorkFormDao extends JpaRepository<WorkForm, Integer> {


    /**
     * 根据 customId 和 deviceNo 查询工单信息,按照派单时间倒序排序
     * @param customerId
     * @param deviceNo
     * @param pageable
     * @return
     *
     * wf
     *  fk5ede7403 用户编码
     *  fk368c3e9d 创建时间
     */
    @Query(value = "select * from work_form where user_id=(" +
            "select user_id from user u where u.customer_id=?1 and u.device_no=?2) " +
            "order by master_create_time desc", nativeQuery = true)
    Page<WorkForm> findJoinWorkFormAndUser(@Param(value = "customerId") String customerId,
                                           @Param(value = "deviceNo") String deviceNo,
                                           Pageable pageable);


    @Query(value = "select * from work_form wf where wf.user_id=(" +
            "select u.user_id from user u where u.customer_id=?1 and u.device_no=?2) " +
            "and wf.id=?3", nativeQuery = true)
    WorkForm findJoinWorkFormAndUserDetail(@Param(value = "customerId") String customerId,
                                                @Param(value = "deviceNo") String deviceNo,
                                                @Param(value = "id") String id);

}
