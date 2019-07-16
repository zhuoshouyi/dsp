package com.topway.controller;

import com.topway.VO.RankListShowVO;
import com.topway.VO.ResultVO;
import com.topway.dto.*;
import com.topway.service.Impl.RankListServiceImpl;
import com.topway.utils.ResultVOUtil;
import com.topway.utils.UserAuthentication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by haizhi on 2019/6/5.
 */
@RestController
@RequestMapping("/rank")
@Slf4j
public class RankListController {


    @Autowired
    RankListServiceImpl rankListService;

//    RankListShowBranchDTO rankListShowBranchDTO = new RankListShowBranchDTO();


//    @PostMapping("/filter")
//    public ResultVO rankFilter(HttpServletRequest httpServletRequest){
//
//        log.info("【排行榜筛选】-------------------------------------------------------");
//
//        /** 1.识别用户身份,判断权限 */
//        UserRoleDTO userRoleDTO = UserAuthentication.authentication(httpServletRequest);
//
//        rankListShowBranchDTO = rankListService.rankListShow(userRoleDTO);
//
//        return ResultVOUtil.success(rankListShowBranchDTO);
//    }



    @PostMapping("/list")
    public ResultVO rankList(HttpServletRequest httpServletRequest){

        log.info("【排行榜展示】-------------------------------------------------------");

        /** 1.识别用户身份,判断权限 */
        UserRoleDTO userRoleDTO = UserAuthentication.authentication(httpServletRequest);

        /** 2.计算各个排行 */
        RankListShowVO rankListShowDTO = new RankListShowVO();

        // 营销生效额
        log.info("【排行榜】营销生效额排行");
        List<RankListShowStationDTO> rank1List1 = rankListService.findTop1(userRoleDTO);

        // 24安装处理成功率(数字+宽带)
        log.info("【排行榜】24安装处理成功率(数字+宽带)");
        List<RankListShowStationDTO> rank2List = rankListService.findTop2(userRoleDTO);

        // 48安装处理成功率(数字+宽带)
        log.info("【排行榜】48安装处理成功率(数字+宽带)");
        List<RankListShowStationDTO> rank3List = rankListService.findTop3(userRoleDTO);

        // 故障及时处理成功率(数字)
        log.info("【排行榜】故障及时处理成功率(数字)");
        List<RankListShowStationDTO> rank4List = rankListService.findTop4(userRoleDTO);

        // 故障及时处理成功率(宽带)
        log.info("【排行榜】故障及时处理成功率(宽带)");
        List<RankListShowStationDTO> rank5List = rankListService.findTop5(userRoleDTO);

        // 故障单预约规范率
        log.info("【排行榜】故障单预约规范率");
        List<RankListShowStationDTO> rank6List = rankListService.findTop6(userRoleDTO);

        // 数字电视用户流失数/率
        log.info("【排行榜】数字电视用户流失数/率");
        List<RankListShowStationDTO> rank7List = rankListService.findTop7(userRoleDTO);

        // 20M宽带流失数/率
        log.info("【排行榜】20M宽带流失数/率");
        List<RankListShowStationDTO> rank8List = rankListService.findTop8(userRoleDTO);

        // 100M宽带流失数/率
        log.info("【排行榜】100M宽带流失数/率");
        List<RankListShowStationDTO> rank9List = rankListService.findTop9(userRoleDTO);

        // 网格保障率(网格内上月top5小区)
        log.info("【排行榜】网格保障率(网格内上月top5小区)");
        List<RankListShowStationDTO> rank10List = rankListService.findTop10(userRoleDTO);

        // 重复故障率
        log.info("【排行榜】重复故障率");
        List<RankListShowStationDTO> rank11List = rankListService.findTop11(userRoleDTO);

        /** 3.拼接json */
        rankListShowDTO.setF1(rank1List1);
        rankListShowDTO.setF2(rank2List);
        rankListShowDTO.setF3(rank3List);
        rankListShowDTO.setF4(rank4List);
        rankListShowDTO.setF5(rank5List);
        rankListShowDTO.setF6(rank6List);
        rankListShowDTO.setF7(rank7List);
        rankListShowDTO.setF8(rank8List);
        rankListShowDTO.setF9(rank9List);
        rankListShowDTO.setF10(rank10List);
        rankListShowDTO.setF11(rank11List);

        return ResultVOUtil.success(rankListShowDTO);
    }
}
