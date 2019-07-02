package com.topway.controller;

import com.topway.VO.ResultVO;
import com.topway.dto.RankListContentDTO;
import com.topway.VO.RankListShowVO;
import com.topway.dto.RankListShowBranchDTO;
import com.topway.dto.UserRoleDTO;
import com.topway.form.RankListForm;
import com.topway.service.Impl.RankListServiceImpl;
import com.topway.utils.ResultVOUtil;
import com.topway.utils.UserAuthentication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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


    @PostMapping("/filter")
    public ResultVO rankFilter(HttpServletRequest httpServletRequest){

        log.info("【排行榜筛选】-------------------------------------------------------");

        /** 1.识别用户身份,判断权限 */
        UserRoleDTO userRoleDTO = UserAuthentication.authentication(httpServletRequest);

        RankListShowBranchDTO rankListShowBranchDTO = rankListService.rankListShow(userRoleDTO);

        return ResultVOUtil.success(rankListShowBranchDTO);
    }



    @PostMapping("/list")
    public ResultVO rankList(@RequestBody RankListForm rankListForm){

        log.info("【排行榜展示】-------------------------------------------------------");

        /** 1.获取传入参数,默认为all */
        // 参数1,分部
        final String STATION = rankListForm.getStation();
        // 参数2,网格
        final String GRID = rankListForm.getGrid();

        /** 2.计算各个排行 */
        RankListShowVO rankListShowDTO = new RankListShowVO();

        // 营销生效额
        log.info("【排行榜】营销生效额排行");
        List<RankListContentDTO> rank1List = rankListService.findTop1(STATION, GRID);

        // 24安装处理成功率(数字+宽带)
        log.info("【排行榜】24安装处理成功率(数字+宽带)");
        List<RankListContentDTO> rank2List = rankListService.findTop2(STATION, GRID);

        // 48安装处理成功率(数字+宽带)
        log.info("【排行榜】48安装处理成功率(数字+宽带)");
        List<RankListContentDTO> rank3List = rankListService.findTop3(STATION, GRID);

        // 故障及时处理成功率(数字)
        log.info("【排行榜】故障及时处理成功率(数字)");
        List<RankListContentDTO> rank4List = rankListService.findTop4(STATION, GRID);

        // 故障及时处理成功率(宽带)
        log.info("【排行榜】故障及时处理成功率(宽带)");
        List<RankListContentDTO> rank5List = rankListService.findTop5(STATION, GRID);

        // 故障单预约规范率
        log.info("【排行榜】故障单预约规范率");
        List<RankListContentDTO> rank6List = rankListService.findTop6(STATION, GRID);

        // 数字电视用户流失数/率
        log.info("【排行榜】数字电视用户流失数/率");
        List<RankListContentDTO> rank7List = rankListService.findTop7(STATION, GRID);

        // 20M宽带流失数/率
        log.info("【排行榜】20M宽带流失数/率");
        List<RankListContentDTO> rank8List = rankListService.findTop8(STATION, GRID);

        // 100M宽带流失数/率
        log.info("【排行榜】100M宽带流失数/率");
        List<RankListContentDTO> rank9List = rankListService.findTop9(STATION, GRID);

        // 网格保障率(网格内上月top5小区)
        log.info("【排行榜】网格保障率(网格内上月top5小区)");
        List<RankListContentDTO> rank10List = rankListService.findTop10(STATION, GRID);

        // 重复故障率
        log.info("【排行榜】重复故障率");
        List<RankListContentDTO> rank11List = rankListService.findTop11(STATION, GRID);

        /** 3.拼接json */
        rankListShowDTO.setF1(rank1List);
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
