package com.topway.DAO;

import com.topway.pojo.WatchAction;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by haizhi on 2019/5/27.
 */
public interface WatchActionDao extends JpaRepository<WatchAction, Integer> {

    @Query("select wa from WatchAction wa, User u " +
            "where u.customerId=?1 and u.deviceNo=?2 and u.deviceNo=wa.deviceNo")
    List<WatchAction> findJoinWatchActionAndUser(@Param(value = "customerId") String customerId,
                                                 @Param(value = "deviceNo") String deviceNo);


}
