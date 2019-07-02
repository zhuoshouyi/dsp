package com.topway.service.Impl;

import com.topway.DAO.*;
import com.topway.convert.Objects2RankListContentDTOConvert;
import com.topway.dto.*;
import com.topway.service.RankListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haizhi on 2019/6/5.
 */
@Service
@Slf4j
public class RankListServiceImpl implements RankListService{

    @Autowired
    RankListMarketDao rankListMarketDao;

    @Autowired
    RankListLossDao rankListLossDao;

    @Autowired
    RankListFaultDao rankListFaultDao;

    @Autowired
    RankListGridDao rankListGridDao;

    @Autowired
    UserDao userDao;

    private String date = "2019-06-04 00:00:00";
    private String month = "2019-05";



    /** 排行榜 filter */
    @Override
    public RankListShowBranchDTO rankListShow(UserRoleDTO userRoleDTO){
        RankListShowBranchDTO rankListShowBranchDTO = new RankListShowBranchDTO();

        // 全公司下的维护站
        // 查询此网格员权限范围内有多少个维护站
        List<String> stationList;
        if (userRoleDTO.getUserRole().equals("公司领导") || userRoleDTO.getUserRole().equals("业务部门") ){
            log.info("【排行榜】用户身份为公司领导或业务部门");
            stationList = userDao.findStationBySpcodeAndBranch(userRoleDTO.getSpcodeId(), userRoleDTO.getBusinessOfficeId());

        }else if (userRoleDTO.getUserRole().equals("支撑网格员") ||
                userRoleDTO.getUserRole().equals("基础网格员") ||
                userRoleDTO.getUserRole().equals("站长")){
            log.info("【排行榜】用户身份为非公司领导");
            stationList = userDao.findByGridId(userRoleDTO.getServiceGridId());

        }else {
            log.info("【排行榜】用户无身份,默认基础网格员");
            stationList = userDao.findByGridId(userRoleDTO.getServiceGridId());

        }

        List<RankListShowStationDTO> rankListShowStationDTOList = new ArrayList<>();
        for (int i=0; i<stationList.size(); i++){
            RankListShowStationDTO rankListShowStationDTO = new RankListShowStationDTO();
            rankListShowStationDTO.setId(String.valueOf(i));
            rankListShowStationDTO.setCode("");
            rankListShowStationDTO.setParentId(String.valueOf(1));
            rankListShowStationDTO.setName(stationList.get(i));

            List<RankListShowGridDTO> rankListShowGridDTOList = new ArrayList<>();
            List<String> gridList = userDao.findByStation(stationList.get(i));
            for (int j=0; j<gridList.size(); j++){
                RankListShowGridDTO rankListShowGridDTO = new RankListShowGridDTO();
                rankListShowGridDTO.setId(String.valueOf(j));
                rankListShowGridDTO.setCode("");
                rankListShowGridDTO.setParentId(String.valueOf(i));
                rankListShowGridDTO.setName(gridList.get(j));

                rankListShowGridDTOList.add(rankListShowGridDTO);
            }
            rankListShowStationDTO.setChilds(rankListShowGridDTOList);

            rankListShowStationDTOList.add(rankListShowStationDTO);
        }

        rankListShowBranchDTO.setId("1");
        rankListShowBranchDTO.setChilds(rankListShowStationDTOList);
        rankListShowBranchDTO.setCode("");
        rankListShowBranchDTO.setName("全公司");


        return rankListShowBranchDTO;
    }



    @Override
    public List<RankListContentDTO> findTop1(String STATION, String GRID) {
        if (STATION.equals("all")){
            if (GRID.equals("all")){
                return rankListMarketDao.findTop10(date).subList(0,10);

            }else {
                return rankListMarketDao.findByGrid(GRID, date).subList(0,10);
            }
        }else {
            return rankListMarketDao.findByStationAndGrid(STATION, GRID, date).subList(0,10);
        }
    }


    @Override
    public List<RankListContentDTO> findTop2(String STATION, String GRID) {
        List<Object[]> objects = rankListFaultDao.find24(date);
        return Objects2RankListContentDTOConvert.convert(objects);
    }

    @Override
    public List<RankListContentDTO> findTop3(String STATION, String GRID) {
        List<Object[]> objects = rankListFaultDao.find48(date);
        return Objects2RankListContentDTOConvert.convert(objects);
    }

    @Override
    public List<RankListContentDTO> findTop4(String STATION, String GRID) {
        List<Object[]> objects = rankListFaultDao.findInTimeWatch(date);
        return Objects2RankListContentDTOConvert.convert(objects);
    }

    @Override
    public List<RankListContentDTO> findTop5(String STATION, String GRID) {
        List<Object[]> objects = rankListFaultDao.findInTimeBroad(date);
        return Objects2RankListContentDTOConvert.convert(objects);
    }

    @Override
    public List<RankListContentDTO> findTop6(String STATION, String GRID) {
        return null;
    }

    @Override
    public List<RankListContentDTO> findTop7(String STATION, String GRID) {
        List<Object[]> objects = rankListLossDao.findWatchLoss(month);
        return Objects2RankListContentDTOConvert.convert(objects);
    }

    @Override
    public List<RankListContentDTO> findTop8(String STATION, String GRID) {
        List<Object[]> objects = rankListLossDao.find20MLoss(month);
        return Objects2RankListContentDTOConvert.convert(objects);
    }

    @Override
    public List<RankListContentDTO> findTop9(String STATION, String GRID) {
        List<Object[]> objects = rankListLossDao.find100MLoss(month);
        return Objects2RankListContentDTOConvert.convert(objects);
    }

    @Override
    public List<RankListContentDTO> findTop10(String STATION, String GRID) {
        List<Object[]> objects = rankListGridDao.findGrid(month);
        return Objects2RankListContentDTOConvert.convert(objects);
    }

    @Override
    public List<RankListContentDTO> findTop11(String STATION, String GRID) {
        List<Object[]> objects = rankListFaultDao.findRepeat(date);
        return Objects2RankListContentDTOConvert.convert(objects);
    }

}
