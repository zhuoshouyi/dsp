package com.topway.service;

import com.topway.dto.RankListContentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by haizhi on 2019/6/5.
 */
@Service
public interface RankListService {

    // 营销生效额
    List<RankListContentDTO> findTop1(String STATION, String GRID);

    // 24安装处理成功率(数字+宽带)
    List<RankListContentDTO> findTop2(String STATION, String GRID);

    // 48安装处理成功率(数字+宽带)
    List<RankListContentDTO> findTop3(String STATION, String GRID);

    // 故障及时处理成功率(数字)
    List<RankListContentDTO> findTop4(String STATION, String GRID);

    // 故障及时处理成功率(宽带)
    List<RankListContentDTO> findTop5(String STATION, String GRID);

    // 故障单预约规范率
    List<RankListContentDTO> findTop6(String STATION, String GRID);

    // 数字电视用户流失数/率
    List<RankListContentDTO> findTop7(String STATION, String GRID);

    // 20M宽带流失数/率
    List<RankListContentDTO> findTop8(String STATION, String GRID);

    // 100M宽带流失数/率
    List<RankListContentDTO> findTop9(String STATION, String GRID);

    // 网格保障率(网格内上月top5小区)
    List<RankListContentDTO> findTop10(String STATION, String GRID);

    // 重复故障率
    List<RankListContentDTO> findTop11(String STATION, String GRID);

}
