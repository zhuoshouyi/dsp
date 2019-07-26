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
    List<RankListShowPersonDTO> findTop1(UserRoleDTO userRoleDTO, String branchOrStation, String gridOrPerson);

    // 24安装处理成功率(数字+宽带)
    List findTop2(UserRoleDTO userRoleDTO, String branchOrStation, String gridOrPerson);

    // 48安装处理成功率(数字+宽带)
    List findTop3(UserRoleDTO userRoleDTO, String branchOrStation, String gridOrPerson);

    // 故障及时处理成功率(数字)
    List findTop4(UserRoleDTO userRoleDTO, String branchOrStation, String gridOrPerson);

    // 故障及时处理成功率(宽带)
    List findTop5(UserRoleDTO userRoleDTO, String branchOrStation, String gridOrPerson);

    // 故障单预约规范率
    List findTop6(UserRoleDTO userRoleDTO, String branchOrStation, String gridOrPerson);

    // 数字电视用户流失数/率
    List<RankListShowGridDTO> findTop7(UserRoleDTO userRoleDTO, String branchOrStation, String gridOrPerson);

    // 20M宽带流失数/率
    List<RankListShowGridDTO> findTop8(UserRoleDTO userRoleDTO, String branchOrStation, String gridOrPerson);

    // 100M宽带流失数/率
    List<RankListShowGridDTO> findTop9(UserRoleDTO userRoleDTO, String branchOrStation, String gridOrPerson);

    // 网格保障率(网格内上月top5小区)
    List<RankListShowGridDTO> findTop10(UserRoleDTO userRoleDTO, String branchOrStation, String gridOrPerson);

    // 重复故障率
    List<RankListShowGridDTO> findTop11(UserRoleDTO userRoleDTO, String branchOrStation, String gridOrPerson);

}
