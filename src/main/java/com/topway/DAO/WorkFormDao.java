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
     */
    @Query("select wf from WorkForm wf, User u " +
            "where u.fk572f5a34=?1 and u.fkdf1e945e=?2 and u.fk74dd6ddc=wf.fk496397ec " +
            "order by wf.fkc7646a53 desc")
    Page<WorkForm> findJoinWorkFormAndUser(@Param(value = "customerId") String customerId,
                                           @Param(value = "deviceNo") String deviceNo,
                                           Pageable pageable);


    @Query("select wf from WorkForm wf, User u " +
            "where u.fk572f5a34=?1 and u.fkdf1e945e=?2 and u.fk74dd6ddc=wf.fk496397ec and wf.id=?3")
    WorkForm findJoinWorkFormAndUserDetail(@Param(value = "customerId") String customerId,
                                           @Param(value = "deviceNo") String deviceNo,
                                           @Param(value = "id") String id);

}
