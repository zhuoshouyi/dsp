package com.topway.service;

import com.topway.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by haizhi on 2019/6/5.
 */
@Service
public interface RankListService {


    // 排行榜筛选器
//    RankListShowBranchDTO rankListShow(UserRoleDTO userRoleDTO);

    // 筛选器-查找所属维护站
//    List<RankListShowStationDTO> findAllStation(UserRoleDTO userRoleDTO);

    // 营销生效额
    List<RankListShowStationDTO> findTop1(UserRoleDTO userRoleDTO);

    // 24安装处理成功率(数字+宽带)
    List<RankListShowStationDTO> findTop2(UserRoleDTO userRoleDTO);

    // 48安装处理成功率(数字+宽带)
    List<RankListShowStationDTO> findTop3(UserRoleDTO userRoleDTO);

    // 故障及时处理成功率(数字)
    List<RankListShowStationDTO> findTop4(UserRoleDTO userRoleDTO);

    // 故障及时处理成功率(宽带)
    List<RankListShowStationDTO> findTop5(UserRoleDTO userRoleDTO);

    // 故障单预约规范率
    List<RankListShowStationDTO> findTop6(UserRoleDTO userRoleDTO);

    // 数字电视用户流失数/率
    List<RankListShowStationDTO> findTop7(UserRoleDTO userRoleDTO);

    // 20M宽带流失数/率
    List<RankListShowStationDTO> findTop8(UserRoleDTO userRoleDTO);

    // 100M宽带流失数/率
    List<RankListShowStationDTO> findTop9(UserRoleDTO userRoleDTO);

    // 网格保障率(网格内上月top5小区)
    List<RankListShowStationDTO> findTop10(UserRoleDTO userRoleDTO);

    // 重复故障率
    List<RankListShowStationDTO> findTop11(UserRoleDTO userRoleDTO);

}
