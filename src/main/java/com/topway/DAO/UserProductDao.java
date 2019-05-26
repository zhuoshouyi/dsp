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
            "where u.fkc1f5a1c1=?1 and u.fkce29e60a=?2 and u.fk2610e16b=up.fk451a778a " +
            "order by up.fk918097a6 desc")
    Page<UserProduct> findJoinUserAndUserProduct(@Param(value = "customerId") String customerId,
                                                 @Param(value = "deviceNo") String deviceNo,
                                                 Pageable pageable);
}
