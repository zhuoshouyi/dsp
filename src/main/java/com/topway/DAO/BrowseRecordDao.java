package com.topway.DAO;

import com.topway.pojo.BrowseRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by haizhi on 2019/7/11.
 */
public interface BrowseRecordDao extends JpaRepository<BrowseRecord, Integer> {

    List<BrowseRecord> findByUserIdAndTypeOrderByCreateTimeDesc(String userId, String type);

    BrowseRecord findByUserIdAndTypeAndValueId(String userId, String type, String valueId);


}
