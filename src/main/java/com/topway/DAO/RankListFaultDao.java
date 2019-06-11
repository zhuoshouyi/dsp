package com.topway.DAO;

import com.topway.pojo.RankListFault;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by haizhi on 2019/6/5.
 */
public interface RankListFaultDao extends JpaRepository<RankListFault, Integer> {

//    @Query(value = "select new com.topway.dto.RankListContentDTO(rank.followBackPeoName, (sum(rank.installSuccess24hour)/sum(rank.installNum))) " +
//            "from RankListFault rank " +
//            "where rank.date=?1 " +
//            "group by rank.followBackPeoName " +
//            "order by (sum(rank.installSuccess24hour)/sum(rank.installNum)) desc ")
//    List<RankListContentDTO> findOne(@Param("date") String date);


    /** 24安装及时处理率(数字) */
    @Query(value = "select rank.follow_back_peo_name, sum(rank.install_success24hour)/coalesce(sum(rank.install_num), 0) as sum_num " +
            "from rank_list_fault rank " +
            "where rank.back_time=?1 " +
            "group by rank.follow_back_peo_name " +
            "order by sum_num desc " +
            "limit 10", nativeQuery = true)
    List<Object[]> find24(@Param("date") String date);

    /** 48安装及时处理率(数字) */
    @Query(value = "select rank.follow_back_peo_name, sum(rank.install_success48hour)/coalesce(sum(rank.install_num), 0) as sum_num " +
            "from rank_list_fault rank " +
            "where rank.back_time=?1 " +
            "group by rank.follow_back_peo_name " +
            "order by sum_num desc " +
            "limit 10", nativeQuery = true)
    List<Object[]> find48(@Param("date") String date);

    /** 故障及时处理成功率(数字) */
    @Query(value = "select rank.follow_back_peo_name, sum(rank.watch_fault_success_num)/coalesce(sum(rank.watch_fault_num), 0) as sum_num " +
            "from rank_list_fault rank " +
            "where rank.back_time=?1 " +
            "group by rank.follow_back_peo_name " +
            "order by sum_num desc " +
            "limit 10", nativeQuery = true)
    List<Object[]> findInTimeWatch(@Param("date") String date);

    /** 故障及时处理成功率(宽带) */
    @Query(value = "select rank.follow_back_peo_name, sum(rank.network_fault_success_num)/coalesce(sum(rank.network_fault_num), 0) as sum_num " +
            "from rank_list_fault rank " +
            "where rank.back_time=?1 " +
            "group by rank.follow_back_peo_name " +
            "order by sum_num desc " +
            "limit 10", nativeQuery = true)
    List<Object[]> findInTimeBroad(@Param("date") String date);

    /** 重复故障率 */
    @Query(value = "select rank.follow_back_peo_name, sum(rank.repeat_num)/coalesce(sum(rank.install_num), 0) as sum_num " +
            "from rank_list_fault rank " +
            "where rank.back_time=?1 " +
            "group by rank.follow_back_peo_name " +
            "order by sum_num desc " +
            "limit 10", nativeQuery = true)
    List<Object[]> findRepeat(@Param("date") String date);

}
