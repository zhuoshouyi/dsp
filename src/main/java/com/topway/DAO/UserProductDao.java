package com.topway.DAO;

import com.topway.pojo.UserProduct;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by haizhi on 2019/5/27.
 */
public interface UserProductDao extends JpaRepository<UserProduct, Integer>{

    @Query(value = "select * from user_product up where up.fk451a778a in (" +
            "select user_id from user where customer_id=?1 and device_no=?2) " +
            "order by up.fk918097a6 desc", nativeQuery = true)
    Page<UserProduct> findJoinUserAndUserProduct(@Param(value = "customerId") String customerId,
                                                 @Param(value = "deviceNo") String deviceNo,
                                                 Pageable pageable);


    @Query(value = "select * from user_product up where up.fk451a778a in (" +
            "select user_id from user where customer_id=?1 and device_no=?2) " +
            "and up.id=?3", nativeQuery = true)
    UserProduct findJoinUserAndUserProductDetail(@Param(value = "customerId") String customerId,
                                                 @Param(value = "deviceNo") String deviceNo,
                                                 @Param(value = "id") String id);
}
