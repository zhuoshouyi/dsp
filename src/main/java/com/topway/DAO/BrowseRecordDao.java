package com.topway.DAO;

import com.topway.pojo.BrowseRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by haizhi on 2019/7/11.
 */
public interface BrowseRecordDao extends JpaRepository<BrowseRecord, Integer> {

    @Query(value = "select * " +
            "from browse_record b " +
            "where b.user_id=?1 " +
            "and b.type=?2 " +
            "order by create_time desc " +
            "limit 10 ", nativeQuery = true)
    List<BrowseRecord> findByUserIdAndTypeOrderByCreateTimeDesc(String userId, String type);

    BrowseRecord findByUserIdAndTypeAndValueId(String userId, String type, String valueId);


}
