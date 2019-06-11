package com.topway.DAO;

import com.topway.pojo.RankListGrid;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by haizhi on 2019/6/5.
 */
public interface RankListGridDao extends JpaRepository<RankListGrid, Integer> {


    /** 网格报障数/率 */
    @Query(value = "select rank.grid, sum(fault_num) as sum_num " +
            "from rank_list_grid rank " +
            "where rank.month=?1 " +
            "group by rank.grid " +
            "order by sum_num desc " +
            "limit 10", nativeQuery = true)
    List<Object[]> findGrid(@Param("month") String month);
}
