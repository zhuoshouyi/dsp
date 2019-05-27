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

    @Query("select up from UserProduct up, User u " +
            "where u.fk572f5a34=?1 and u.fkdf1e945e=?2 and u.fk74dd6ddc=up.fk451a778a " +
            "order by up.fk918097a6 desc")
    Page<UserProduct> findJoinUserAndUserProduct(@Param(value = "customerId") String customerId,
                                                 @Param(value = "deviceNo") String deviceNo,
                                                 Pageable pageable);


    @Query("select up from UserProduct up, User u " +
            "where u.fk572f5a34=?1 and u.fkdf1e945e=?2 and u.fk74dd6ddc=up.fk451a778a and up.id=?3 ")
    UserProduct findJoinUserAndUserProductDetail(@Param(value = "customerId") String customerId,
                                                 @Param(value = "deviceNo") String deviceNo,
                                                 @Param(value = "id") String id);
}
