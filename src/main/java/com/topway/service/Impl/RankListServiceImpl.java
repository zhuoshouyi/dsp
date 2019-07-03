package com.topway.service.Impl;

import com.topway.DAO.*;
import com.topway.convert.Objects2RankListContentDTOConvert;
import com.topway.convert.Objects2RankListContentPerDTOConvert;
import com.topway.dto.*;
import com.topway.service.RankListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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
        log.info("【排行榜筛选】用户负责维护站: " + stationList.toString());

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
            log.info("【排行榜筛选】用户负责的网格: " + gridList.toString());
        }



        rankListShowBranchDTO.setId("1");
        rankListShowBranchDTO.setChilds(rankListShowStationDTOList);
        rankListShowBranchDTO.setCode("");
        rankListShowBranchDTO.setName("全公司");


        return rankListShowBranchDTO;
    }



    @Override
    public List<RankListContentDTO> findTop1(String STATION, String GRID, RankListShowBranchDTO rankListShowBranchDTO) {

        List<String> stationList = new ArrayList<>();
        List<String> gridList = new ArrayList<>();

        if (STATION.equals("all")){
            // 获取此用户的所有维护站信息和网格信息
            for (int i=0; i<rankListShowBranchDTO.getChilds().size(); i++){
                stationList.add(rankListShowBranchDTO.getChilds().get(i).getName());
                for (int j=0; j<rankListShowBranchDTO.getChilds().get(i).getChilds().size(); j++){
                    gridList.add(rankListShowBranchDTO.getChilds().get(i).getChilds().get(j).getName());
                }
            }

            if (GRID.equals("all")){
                log.info("【排行榜top1】stationList=" + stationList.toString() + "\ngridList=" + gridList.toString());
                return rankListMarketDao.findTop10(date, stationList, gridList).subList(0,10);
            }else {
                log.info("【排行榜top1】stationList=" + stationList.toString() + "\ngridList=" + Arrays.asList(GRID));
                return rankListMarketDao.findTop10(date, stationList, Arrays.asList(GRID)).subList(0,10);
            }
        }else {
            // 获取此用户的此维护站信息的网格信息
            for (int i=0; i<rankListShowBranchDTO.getChilds().size(); i++){
                if (rankListShowBranchDTO.getChilds().get(i).getName().equals(STATION)){
                    for (int j=0; j<rankListShowBranchDTO.getChilds().get(i).getChilds().size(); j++){
                        gridList.add(rankListShowBranchDTO.getChilds().get(i).getChilds().get(j).getName());
                    }
                }
            }

            if (GRID.equals("all")){
                log.info("【排行榜top1】stationList=" + Arrays.asList(STATION) + "\ngridList=" + gridList.toString());
                return rankListMarketDao.findTop10(date, Arrays.asList(STATION), gridList).subList(0,10);
            }else {
                log.info("【排行榜top1】stationList=" + Arrays.asList(STATION) + "\ngridList=" + Arrays.asList(GRID));
                return rankListMarketDao.findTop10(date, Arrays.asList(STATION), Arrays.asList(GRID)).subList(0,10);
            }
        }
    }


    @Override
    public List<RankListContentDTO> findTop2(String STATION, String GRID, RankListShowBranchDTO rankListShowBranchDTO) {
        List<String> stationList = new ArrayList<>();
        List<String> gridList = new ArrayList<>();

        if (STATION.equals("all")){
            // 获取此用户的所有维护站信息和网格信息
            for (int i=0; i<rankListShowBranchDTO.getChilds().size(); i++){
                stationList.add(rankListShowBranchDTO.getChilds().get(i).getName());
                for (int j=0; j<rankListShowBranchDTO.getChilds().get(i).getChilds().size(); j++){
                    gridList.add(rankListShowBranchDTO.getChilds().get(i).getChilds().get(j).getName());
                }
            }

            if (GRID.equals("all")){
                log.info("【排行榜top2】stationList=" + stationList.toString() + "\ngridList=" + gridList.toString());
                List<Object[]> objects = rankListFaultDao.find24(date, stationList, gridList);
                return Objects2RankListContentDTOConvert.convert(objects);
            }else {
                log.info("【排行榜top2】stationList=" + stationList.toString() + "\ngridList=" + Arrays.asList(GRID));
                List<Object[]> objects = rankListFaultDao.find24(date, stationList, Arrays.asList(GRID));
                return Objects2RankListContentDTOConvert.convert(objects);
            }
        }else {
            // 获取此用户的此维护站信息的网格信息
            for (int i=0; i<rankListShowBranchDTO.getChilds().size(); i++){
                if (rankListShowBranchDTO.getChilds().get(i).getName().equals(STATION)){
                    for (int j=0; j<rankListShowBranchDTO.getChilds().get(i).getChilds().size(); j++){
                        gridList.add(rankListShowBranchDTO.getChilds().get(i).getChilds().get(j).getName());
                    }
                }
            }

            if (GRID.equals("all")){
                log.info("【排行榜top2】stationList=" + Arrays.asList(STATION) + "\ngridList=" + gridList.toString());
                List<Object[]> objects = rankListFaultDao.find24(date, Arrays.asList(STATION), gridList);
                return Objects2RankListContentDTOConvert.convert(objects);
            }else {
                log.info("【排行榜top2】stationList=" + Arrays.asList(STATION) + "\ngridList=" + Arrays.asList(GRID));
                List<Object[]> objects = rankListFaultDao.find24(date, Arrays.asList(STATION), Arrays.asList(GRID));
                return Objects2RankListContentDTOConvert.convert(objects);
            }
        }

    }

    @Override
    public List<RankListContentDTO> findTop3(String STATION, String GRID, RankListShowBranchDTO rankListShowBranchDTO) {
        List<String> stationList = new ArrayList<>();
        List<String> gridList = new ArrayList<>();

        if (STATION.equals("all")){
            // 获取此用户的所有维护站信息和网格信息
            for (int i=0; i<rankListShowBranchDTO.getChilds().size(); i++){
                stationList.add(rankListShowBranchDTO.getChilds().get(i).getName());
                for (int j=0; j<rankListShowBranchDTO.getChilds().get(i).getChilds().size(); j++){
                    gridList.add(rankListShowBranchDTO.getChilds().get(i).getChilds().get(j).getName());
                }
            }

            if (GRID.equals("all")){
                log.info("【排行榜top3】stationList=" + stationList.toString() + "\ngridList=" + gridList.toString());
                List<Object[]> objects = rankListFaultDao.find48(date, stationList, gridList);
                return Objects2RankListContentDTOConvert.convert(objects);
            }else {
                log.info("【排行榜top3】stationList=" + stationList.toString() + "\ngridList=" + Arrays.asList(GRID));
                List<Object[]> objects = rankListFaultDao.find48(date, stationList, Arrays.asList(GRID));
                return Objects2RankListContentDTOConvert.convert(objects);
            }
        }else {
            // 获取此用户的此维护站信息的网格信息
            for (int i=0; i<rankListShowBranchDTO.getChilds().size(); i++){
                if (rankListShowBranchDTO.getChilds().get(i).getName().equals(STATION)){
                    for (int j=0; j<rankListShowBranchDTO.getChilds().get(i).getChilds().size(); j++){
                        gridList.add(rankListShowBranchDTO.getChilds().get(i).getChilds().get(j).getName());
                    }
                }
            }

            if (GRID.equals("all")){
                log.info("【排行榜top3】stationList=" + Arrays.asList(STATION) + "\ngridList=" + gridList.toString());
                List<Object[]> objects = rankListFaultDao.find48(date, Arrays.asList(STATION), gridList);
                return Objects2RankListContentDTOConvert.convert(objects);
            }else {
                log.info("【排行榜top3】stationList=" + Arrays.asList(STATION) + "\ngridList=" + Arrays.asList(GRID));
                List<Object[]> objects = rankListFaultDao.find48(date, Arrays.asList(STATION), Arrays.asList(GRID));
                return Objects2RankListContentDTOConvert.convert(objects);
            }
        }
    }

    @Override
    public List<RankListContentDTO> findTop4(String STATION, String GRID, RankListShowBranchDTO rankListShowBranchDTO) {
        List<String> stationList = new ArrayList<>();
        List<String> gridList = new ArrayList<>();

        if (STATION.equals("all")){
            // 获取此用户的所有维护站信息和网格信息
            for (int i=0; i<rankListShowBranchDTO.getChilds().size(); i++){
                stationList.add(rankListShowBranchDTO.getChilds().get(i).getName());
                for (int j=0; j<rankListShowBranchDTO.getChilds().get(i).getChilds().size(); j++){
                    gridList.add(rankListShowBranchDTO.getChilds().get(i).getChilds().get(j).getName());
                }
            }

            if (GRID.equals("all")){
                log.info("【排行榜top4】stationList=" + stationList.toString() + "\ngridList=" + gridList.toString());
                List<Object[]> objects = rankListFaultDao.findInTimeWatch(date, stationList, gridList);
                return Objects2RankListContentDTOConvert.convert(objects);
            }else {
                log.info("【排行榜top4】stationList=" + stationList.toString() + "\ngridList=" + Arrays.asList(GRID));
                List<Object[]> objects = rankListFaultDao.findInTimeWatch(date, stationList, Arrays.asList(GRID));
                return Objects2RankListContentDTOConvert.convert(objects);
            }
        }else {
            // 获取此用户的此维护站信息的网格信息
            for (int i=0; i<rankListShowBranchDTO.getChilds().size(); i++){
                if (rankListShowBranchDTO.getChilds().get(i).getName().equals(STATION)){
                    for (int j=0; j<rankListShowBranchDTO.getChilds().get(i).getChilds().size(); j++){
                        gridList.add(rankListShowBranchDTO.getChilds().get(i).getChilds().get(j).getName());
                    }
                }
            }

            if (GRID.equals("all")){
                log.info("【排行榜top4】stationList=" + Arrays.asList(STATION) + "\ngridList=" + gridList.toString());
                List<Object[]> objects = rankListFaultDao.findInTimeWatch(date, Arrays.asList(STATION), gridList);
                return Objects2RankListContentDTOConvert.convert(objects);
            }else {
                log.info("【排行榜top4】stationList=" + Arrays.asList(STATION) + "\ngridList=" + Arrays.asList(GRID));
                List<Object[]> objects = rankListFaultDao.findInTimeWatch(date, Arrays.asList(STATION), Arrays.asList(GRID));
                return Objects2RankListContentDTOConvert.convert(objects);
            }
        }

    }

    @Override
    public List<RankListContentDTO> findTop5(String STATION, String GRID, RankListShowBranchDTO rankListShowBranchDTO) {
        List<String> stationList = new ArrayList<>();
        List<String> gridList = new ArrayList<>();

        if (STATION.equals("all")){
            // 获取此用户的所有维护站信息和网格信息
            for (int i=0; i<rankListShowBranchDTO.getChilds().size(); i++){
                stationList.add(rankListShowBranchDTO.getChilds().get(i).getName());
                for (int j=0; j<rankListShowBranchDTO.getChilds().get(i).getChilds().size(); j++){
                    gridList.add(rankListShowBranchDTO.getChilds().get(i).getChilds().get(j).getName());
                }
            }

            if (GRID.equals("all")){
                log.info("【排行榜top5】stationList=" + stationList.toString() + "\ngridList=" + gridList.toString());
                List<Object[]> objects = rankListFaultDao.findInTimeBroad(date, stationList, gridList);
                return Objects2RankListContentDTOConvert.convert(objects);
            }else {
                log.info("【排行榜top5】stationList=" + stationList.toString() + "\ngridList=" + Arrays.asList(GRID));
                List<Object[]> objects = rankListFaultDao.findInTimeBroad(date, stationList, Arrays.asList(GRID));
                return Objects2RankListContentDTOConvert.convert(objects);
            }
        }else {
            // 获取此用户的此维护站信息的网格信息
            for (int i=0; i<rankListShowBranchDTO.getChilds().size(); i++){
                if (rankListShowBranchDTO.getChilds().get(i).getName().equals(STATION)){
                    for (int j=0; j<rankListShowBranchDTO.getChilds().get(i).getChilds().size(); j++){
                        gridList.add(rankListShowBranchDTO.getChilds().get(i).getChilds().get(j).getName());
                    }
                }
            }

            if (GRID.equals("all")){
                log.info("【排行榜top5】stationList=" + Arrays.asList(STATION) + "\ngridList=" + gridList.toString());
                List<Object[]> objects = rankListFaultDao.findInTimeBroad(date, Arrays.asList(STATION), gridList);
                return Objects2RankListContentDTOConvert.convert(objects);
            }else {
                log.info("【排行榜top5】stationList=" + Arrays.asList(STATION) + "\ngridList=" + Arrays.asList(GRID));
                List<Object[]> objects = rankListFaultDao.findInTimeBroad(date, Arrays.asList(STATION), Arrays.asList(GRID));
                return Objects2RankListContentDTOConvert.convert(objects);
            }
        }
    }

    @Override
    public List<RankListContentDTO> findTop6(String STATION, String GRID, RankListShowBranchDTO rankListShowBranchDTO) {
        return null;
    }

    @Override
    public List<RankListContentPerDTO> findTop7(String STATION, String GRID, RankListShowBranchDTO rankListShowBranchDTO) {
        List<String> stationList = new ArrayList<>();
        List<String> gridList = new ArrayList<>();

        if (STATION.equals("all")){
            // 获取此用户的所有维护站信息和网格信息
            for (int i=0; i<rankListShowBranchDTO.getChilds().size(); i++){
                stationList.add(rankListShowBranchDTO.getChilds().get(i).getName());
                for (int j=0; j<rankListShowBranchDTO.getChilds().get(i).getChilds().size(); j++){
                    gridList.add(rankListShowBranchDTO.getChilds().get(i).getChilds().get(j).getName());
                }
            }

            if (GRID.equals("all")){
                log.info("【排行榜top7】stationList=" + stationList.toString() + "\ngridList=" + gridList.toString());
                List<Object[]> objects = rankListLossDao.findWatchLoss(month, stationList, gridList);
                return Objects2RankListContentPerDTOConvert.convert(objects);
            }else {
                log.info("【排行榜top7】stationList=" + stationList.toString() + "\ngridList=" + Arrays.asList(GRID));
                List<Object[]> objects = rankListLossDao.findWatchLoss(month, stationList, Arrays.asList(GRID));
                return Objects2RankListContentPerDTOConvert.convert(objects);
            }
        }else {
            // 获取此用户的此维护站信息的网格信息
            for (int i=0; i<rankListShowBranchDTO.getChilds().size(); i++){
                if (rankListShowBranchDTO.getChilds().get(i).getName().equals(STATION)){
                    for (int j=0; j<rankListShowBranchDTO.getChilds().get(i).getChilds().size(); j++){
                        gridList.add(rankListShowBranchDTO.getChilds().get(i).getChilds().get(j).getName());
                    }
                }
            }

            if (GRID.equals("all")){
                log.info("【排行榜top7】stationList=" + Arrays.asList(STATION) + "\ngridList=" + gridList.toString());
                List<Object[]> objects = rankListLossDao.findWatchLoss(month, Arrays.asList(STATION), gridList);
                return Objects2RankListContentPerDTOConvert.convert(objects);
            }else {
                log.info("【排行榜top7】stationList=" + Arrays.asList(STATION) + "\ngridList=" + Arrays.asList(GRID));
                List<Object[]> objects = rankListLossDao.findWatchLoss(month, Arrays.asList(STATION), Arrays.asList(GRID));
                return Objects2RankListContentPerDTOConvert.convert(objects);
            }
        }

    }

    @Override
    public List<RankListContentPerDTO> findTop8(String STATION, String GRID, RankListShowBranchDTO rankListShowBranchDTO) {
        List<String> stationList = new ArrayList<>();
        List<String> gridList = new ArrayList<>();

        if (STATION.equals("all")){
            // 获取此用户的所有维护站信息和网格信息
            for (int i=0; i<rankListShowBranchDTO.getChilds().size(); i++){
                stationList.add(rankListShowBranchDTO.getChilds().get(i).getName());
                for (int j=0; j<rankListShowBranchDTO.getChilds().get(i).getChilds().size(); j++){
                    gridList.add(rankListShowBranchDTO.getChilds().get(i).getChilds().get(j).getName());
                }
            }

            if (GRID.equals("all")){
                log.info("【排行榜top8】stationList=" + stationList.toString() + "\ngridList=" + gridList.toString());
                List<Object[]> objects = rankListLossDao.find20MLoss(month, stationList, gridList);
                return Objects2RankListContentPerDTOConvert.convert(objects);
            }else {
                log.info("【排行榜top8】stationList=" + stationList.toString() + "\ngridList=" + Arrays.asList(GRID));
                List<Object[]> objects = rankListLossDao.find20MLoss(month, stationList, Arrays.asList(GRID));
                return Objects2RankListContentPerDTOConvert.convert(objects);
            }
        }else {
            // 获取此用户的此维护站信息的网格信息
            for (int i=0; i<rankListShowBranchDTO.getChilds().size(); i++){
                if (rankListShowBranchDTO.getChilds().get(i).getName().equals(STATION)){
                    for (int j=0; j<rankListShowBranchDTO.getChilds().get(i).getChilds().size(); j++){
                        gridList.add(rankListShowBranchDTO.getChilds().get(i).getChilds().get(j).getName());
                    }
                }
            }

            if (GRID.equals("all")){
                log.info("【排行榜top8】stationList=" + Arrays.asList(STATION) + "\ngridList=" + gridList.toString());
                List<Object[]> objects = rankListLossDao.find20MLoss(month, Arrays.asList(STATION), gridList);
                return Objects2RankListContentPerDTOConvert.convert(objects);
            }else {
                log.info("【排行榜top8】stationList=" + Arrays.asList(STATION) + "\ngridList=" + Arrays.asList(GRID));
                List<Object[]> objects = rankListLossDao.find20MLoss(month, Arrays.asList(STATION), Arrays.asList(GRID));
                return Objects2RankListContentPerDTOConvert.convert(objects);
            }
        }
    }

    @Override
    public List<RankListContentPerDTO> findTop9(String STATION, String GRID, RankListShowBranchDTO rankListShowBranchDTO) {
        List<String> stationList = new ArrayList<>();
        List<String> gridList = new ArrayList<>();

        if (STATION.equals("all")){
            // 获取此用户的所有维护站信息和网格信息
            for (int i=0; i<rankListShowBranchDTO.getChilds().size(); i++){
                stationList.add(rankListShowBranchDTO.getChilds().get(i).getName());
                for (int j=0; j<rankListShowBranchDTO.getChilds().get(i).getChilds().size(); j++){
                    gridList.add(rankListShowBranchDTO.getChilds().get(i).getChilds().get(j).getName());
                }
            }

            if (GRID.equals("all")){
                log.info("【排行榜top9】stationList=" + stationList.toString() + "\ngridList=" + gridList.toString());
                List<Object[]> objects = rankListLossDao.find100MLoss(month, stationList, gridList);
                return Objects2RankListContentPerDTOConvert.convert(objects);
            }else {
                log.info("【排行榜top9】stationList=" + stationList.toString() + "\ngridList=" + Arrays.asList(GRID));
                List<Object[]> objects = rankListLossDao.find100MLoss(month, stationList, Arrays.asList(GRID));
                return Objects2RankListContentPerDTOConvert.convert(objects);
            }
        }else {
            // 获取此用户的此维护站信息的网格信息
            for (int i=0; i<rankListShowBranchDTO.getChilds().size(); i++){
                if (rankListShowBranchDTO.getChilds().get(i).getName().equals(STATION)){
                    for (int j=0; j<rankListShowBranchDTO.getChilds().get(i).getChilds().size(); j++){
                        gridList.add(rankListShowBranchDTO.getChilds().get(i).getChilds().get(j).getName());
                    }
                }
            }

            if (GRID.equals("all")){
                log.info("【排行榜top9】stationList=" + Arrays.asList(STATION) + "\ngridList=" + gridList.toString());
                List<Object[]> objects = rankListLossDao.find100MLoss(month, Arrays.asList(STATION), gridList);
                return Objects2RankListContentPerDTOConvert.convert(objects);
            }else {
                log.info("【排行榜top9】stationList=" + Arrays.asList(STATION) + "\ngridList=" + Arrays.asList(GRID));
                List<Object[]> objects = rankListLossDao.find100MLoss(month, Arrays.asList(STATION), Arrays.asList(GRID));
                return Objects2RankListContentPerDTOConvert.convert(objects);
            }
        }
    }

    @Override
    public List<RankListContentDTO> findTop10(String STATION, String GRID, RankListShowBranchDTO rankListShowBranchDTO) {
        List<String> stationList = new ArrayList<>();
        List<String> gridList = new ArrayList<>();

        if (STATION.equals("all")){
            // 获取此用户的所有维护站信息和网格信息
            for (int i=0; i<rankListShowBranchDTO.getChilds().size(); i++){
                stationList.add(rankListShowBranchDTO.getChilds().get(i).getName());
                for (int j=0; j<rankListShowBranchDTO.getChilds().get(i).getChilds().size(); j++){
                    gridList.add(rankListShowBranchDTO.getChilds().get(i).getChilds().get(j).getName());
                }
            }

            if (GRID.equals("all")){
                log.info("【排行榜top10】stationList=" + stationList.toString() + "\ngridList=" + gridList.toString());
                List<Object[]> objects = rankListGridDao.findGrid(month, stationList, gridList);
                return Objects2RankListContentDTOConvert.convert(objects);
            }else {
                log.info("【排行榜top10】stationList=" + stationList.toString() + "\ngridList=" + Arrays.asList(GRID));
                List<Object[]> objects = rankListGridDao.findGrid(month, stationList, Arrays.asList(GRID));
                return Objects2RankListContentDTOConvert.convert(objects);
            }
        }else {
            // 获取此用户的此维护站信息的网格信息
            for (int i=0; i<rankListShowBranchDTO.getChilds().size(); i++){
                if (rankListShowBranchDTO.getChilds().get(i).getName().equals(STATION)){
                    for (int j=0; j<rankListShowBranchDTO.getChilds().get(i).getChilds().size(); j++){
                        gridList.add(rankListShowBranchDTO.getChilds().get(i).getChilds().get(j).getName());
                    }
                }
            }

            if (GRID.equals("all")){
                log.info("【排行榜top10】stationList=" + Arrays.asList(STATION) + "\ngridList=" + gridList.toString());
                List<Object[]> objects = rankListGridDao.findGrid(month, Arrays.asList(STATION), gridList);
                return Objects2RankListContentDTOConvert.convert(objects);
            }else {
                log.info("【排行榜top10】stationList=" + Arrays.asList(STATION) + "\ngridList=" + Arrays.asList(GRID));
                List<Object[]> objects = rankListGridDao.findGrid(month, Arrays.asList(STATION), Arrays.asList(GRID));
                return Objects2RankListContentDTOConvert.convert(objects);
            }
        }

    }

    @Override
    public List<RankListContentDTO> findTop11(String STATION, String GRID, RankListShowBranchDTO rankListShowBranchDTO) {
        List<String> stationList = new ArrayList<>();
        List<String> gridList = new ArrayList<>();

        if (STATION.equals("all")){
            // 获取此用户的所有维护站信息和网格信息
            for (int i=0; i<rankListShowBranchDTO.getChilds().size(); i++){
                stationList.add(rankListShowBranchDTO.getChilds().get(i).getName());
                for (int j=0; j<rankListShowBranchDTO.getChilds().get(i).getChilds().size(); j++){
                    gridList.add(rankListShowBranchDTO.getChilds().get(i).getChilds().get(j).getName());
                }
            }

            if (GRID.equals("all")){
                log.info("【排行榜top11】stationList=" + stationList.toString() + "\ngridList=" + gridList.toString());
                List<Object[]> objects = rankListFaultDao.findRepeat(date, stationList, gridList);
                return Objects2RankListContentDTOConvert.convert(objects);
            }else {
                log.info("【排行榜top11】stationList=" + stationList.toString() + "\ngridList=" + Arrays.asList(GRID));
                List<Object[]> objects = rankListFaultDao.findRepeat(date, stationList, Arrays.asList(GRID));
                return Objects2RankListContentDTOConvert.convert(objects);
            }
        }else {
            // 获取此用户的此维护站信息的网格信息
            for (int i=0; i<rankListShowBranchDTO.getChilds().size(); i++){
                if (rankListShowBranchDTO.getChilds().get(i).getName().equals(STATION)){
                    for (int j=0; j<rankListShowBranchDTO.getChilds().get(i).getChilds().size(); j++){
                        gridList.add(rankListShowBranchDTO.getChilds().get(i).getChilds().get(j).getName());
                    }
                }
            }

            if (GRID.equals("all")){
                log.info("【排行榜top11】stationList=" + Arrays.asList(STATION) + "\ngridList=" + gridList.toString());
                List<Object[]> objects = rankListFaultDao.findRepeat(date, Arrays.asList(STATION), gridList);
                return Objects2RankListContentDTOConvert.convert(objects);
            }else {
                log.info("【排行榜top11】stationList=" + Arrays.asList(STATION) + "\ngridList=" + Arrays.asList(GRID));
                List<Object[]> objects = rankListFaultDao.findRepeat(date, Arrays.asList(STATION), Arrays.asList(GRID));
                return Objects2RankListContentDTOConvert.convert(objects);
            }
        }
    }

}
