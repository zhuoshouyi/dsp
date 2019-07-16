package com.topway.service.Impl;

import com.topway.DAO.*;
import com.topway.dto.RankListShowGridDTO;
import com.topway.dto.RankListShowStationDTO;
import com.topway.dto.UserRoleDTO;
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



    /** 排行榜 filter
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
                userRoleDTO.getUserRole().equals("基础网格员")){
            log.info("【排行榜】用户身份为支撑网格员或基础网格员");
            stationList = userDao.findByGridId(userRoleDTO.getServiceGridId());

        }else if (userRoleDTO.getUserRole().equals("站长")) {
            log.info("【排行榜】用户身份为站长");
            stationList = userDao.findStationBySpcodeAndBranch(userRoleDTO.getSpcodeId(), userRoleDTO.getBusinessOfficeId());

        } else {
            log.info("【排行榜】用户无身份,默认基础网格员");
            stationList = userDao.findByGridId(userRoleDTO.getServiceGridId());

        }
        log.info("【排行榜筛选】用户负责维护站: " + stationList.toString());

        List<RankListShowStationDTO> rankListShowStationDTOList = new ArrayList<>();
        for (int i=0; i<stationList.size(); i++){
            RankListShowStationDTO rankListShowStationDTO = new RankListShowStationDTO();
            rankListShowStationDTO.setId(String.valueOf(i));
            rankListShowStationDTO.setValue(0.0);
            rankListShowStationDTO.setParentId(String.valueOf(1));
            rankListShowStationDTO.setName(stationList.get(i));

            List<RankListShowGridDTO> rankListShowGridDTOList = new ArrayList<>();
            List<String> gridList = userDao.findByStation(stationList.get(i));
            for (int j=0; j<gridList.size(); j++){
                RankListShowGridDTO rankListShowGridDTO = new RankListShowGridDTO();
                rankListShowGridDTO.setId(String.valueOf(j));
                rankListShowGridDTO.setValue(0.0);
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
        rankListShowBranchDTO.setValue("");
        rankListShowBranchDTO.setName("全公司");


        return rankListShowBranchDTO;
    }
    */


    /**
     * 工具类
     */
    public List<RankListShowStationDTO> convert(List<Object[]> objects, String topNum){
        List<RankListShowStationDTO> rankListShowStationDTOList = new ArrayList<>();
        if (objects.size()>0){
            for (int i=0; i<objects.size(); i++){
                RankListShowStationDTO rankListShowStationDTO = new RankListShowStationDTO();
                rankListShowStationDTO.setId(String.valueOf(i));
                rankListShowStationDTO.setName(String.valueOf(objects.get(i)[0]));
                rankListShowStationDTO.setValue(Double.parseDouble(objects.get(i)[1]==null?"0":objects.get(i)[1].toString()));
                if (topNum.equals("top7") || topNum.equals("top8") || topNum.equals("top9")){
                    rankListShowStationDTO.setPer(Double.parseDouble(objects.get(i)[2]==null?"0":objects.get(i)[2].toString()));
                }
                // 获取每一个 station 的 grid 排行
                List<RankListShowGridDTO> rankListShowGridDTOList = new ArrayList<>();

                List<Object[]> gridObjects = new ArrayList<>();
                switch (topNum){
                    case "top1":
                        gridObjects = rankListMarketDao.findGridByStation(String.valueOf(objects.get(i)[0]), "2019-06-04 00:00:00");
                        break;
                    case "top2":
                        gridObjects = rankListFaultDao.find24Grid(String.valueOf(objects.get(i)[0]), "2019-06-04 00:00:00");
                        break;
                    case "top3":
                        gridObjects = rankListFaultDao.find48Grid(String.valueOf(objects.get(i)[0]), "2019-06-04 00:00:00");
                        break;
                    case "top4":
                        gridObjects = rankListFaultDao.findInTimeWatchGrid(String.valueOf(objects.get(i)[0]), "2019-06-04 00:00:00");
                        break;
                    case "top5":
                        gridObjects = rankListFaultDao.findInTimeBroadGrid(String.valueOf(objects.get(i)[0]), "2019-06-04 00:00:00");
                        break;
                    case "top6":
                        gridObjects = rankListFaultDao.find24Grid(String.valueOf(objects.get(i)[0]), "2019-06-04 00:00:00");
                        break;
                    case "top7":
                        gridObjects = rankListLossDao.findWatchLossGrid(String.valueOf(objects.get(i)[0]), "2019-05");
                        break;
                    case "top8":
                        gridObjects = rankListLossDao.find20MLossGrid(String.valueOf(objects.get(i)[0]), "2019-05");
                        break;
                    case "top9":
                        gridObjects = rankListLossDao.find100MLossGrid(String.valueOf(objects.get(i)[0]), "2019-05");
                        break;
                    case "top10":
                        gridObjects = rankListGridDao.findGridGrid(String.valueOf(objects.get(i)[0]), "2019-05");
                        break;
                    case "top11":
                        gridObjects = rankListFaultDao.findRepeatGrid(String.valueOf(objects.get(i)[0]), "2019-06-04 00:00:00");
                        break;
                    default:
                        break;
                }
                if (gridObjects.size()>0){
                    for (int j=0; j<gridObjects.size(); j++){
                        RankListShowGridDTO rankListShowGridDTO = new RankListShowGridDTO();
                        rankListShowGridDTO.setId(String.valueOf(j));
                        rankListShowGridDTO.setName(String.valueOf(gridObjects.get(j)[0]));
                        rankListShowGridDTO.setValue(Double.parseDouble(gridObjects.get(j)[1]==null?"0":gridObjects.get(j)[1].toString()));
                        if (topNum.equals("top7") || topNum.equals("top8") || topNum.equals("top9")){
                            rankListShowGridDTO.setPer(Double.parseDouble(gridObjects.get(j)[2]==null?"0":gridObjects.get(j)[2].toString()));
                        }
                        rankListShowGridDTO.setParentId(String.valueOf(i));
                        rankListShowGridDTOList.add(rankListShowGridDTO);
                    }
                }

                // 将 gridList 放进每一个 station 的 childs 下面
                rankListShowStationDTO.setChilds(rankListShowGridDTOList);


                // 将 station 排行放进第一层
                rankListShowStationDTOList.add(rankListShowStationDTO);
            }
        }
        return rankListShowStationDTOList;
    }


    @Override
    public List<RankListShowStationDTO> findTop1(UserRoleDTO userRoleDTO){

        List<RankListShowStationDTO> rankListShowStationDTOList;

        // 判断用户权限
        if (userRoleDTO.getUserRole().equals("公司领导") || userRoleDTO.getUserRole().equals("业务部门")){
            log.info("【排行榜】用户身份为公司领导或业务部门");
            log.info("【排行榜top1】");
            List<Object[]> objects = rankListMarketDao.findStationsBySpcodeAndBranchAndGrid(userRoleDTO.getSpcodeId(), userRoleDTO.getBusinessOfficeId(), null, "2019-06-04 00:00:00");
            rankListShowStationDTOList = convert(objects, "top1");

        }else if (userRoleDTO.getUserRole().equals("站长")){
            log.info("【排行榜】用户身份为站长");
            log.info("【排行榜top1】");
            List<Object[]> objects = rankListMarketDao.findStationsBySpcodeAndBranchAndGrid(null, userRoleDTO.getBusinessOfficeId(), null, "2019-06-04 00:00:00");
            rankListShowStationDTOList = convert(objects, "top1");

        }else if (userRoleDTO.getUserRole().equals("基础网格员") || userRoleDTO.getUserRole().equals("支撑网格员")){
            log.info("【排行榜】用户身份为支撑网格员或基础网格员");
            log.info("【排行榜top1】");
            List<Object[]> objects = rankListMarketDao.findStationsBySpcodeAndBranchAndGrid(null, null, userRoleDTO.getServiceGridId(), "2019-06-04 00:00:00");
            rankListShowStationDTOList = convert(objects, "top1");

        }else {
            log.info("【排行榜】用户无身份,默认基础网格员");
            log.info("【排行榜top1】");
            List<Object[]> objects = rankListMarketDao.findStationsBySpcodeAndBranchAndGrid(null, null, userRoleDTO.getServiceGridId(), "2019-06-04 00:00:00");
            rankListShowStationDTOList = convert(objects, "top1");
        }

        return rankListShowStationDTOList;
    }


    @Override
    public List<RankListShowStationDTO> findTop2(UserRoleDTO userRoleDTO) {

        List<RankListShowStationDTO> rankListShowStationDTOList;

        // 判断用户权限
        if (userRoleDTO.getUserRole().equals("公司领导") || userRoleDTO.getUserRole().equals("业务部门")){
            log.info("【排行榜】用户身份为公司领导或业务部门");
            log.info("【排行榜top2】");
            List<Object[]> objects = rankListFaultDao.find24Station(userRoleDTO.getSpcodeId(), userRoleDTO.getBusinessOfficeId(), null, "2019-06-04 00:00:00");
            rankListShowStationDTOList = convert(objects, "top2");

        }else if (userRoleDTO.getUserRole().equals("站长")){
            log.info("【排行榜】用户身份为站长");
            log.info("【排行榜top2】");
            List<Object[]> objects = rankListFaultDao.find24Station(null, userRoleDTO.getBusinessOfficeId(), null, "2019-06-04 00:00:00");
            rankListShowStationDTOList = convert(objects, "top2");

        }else if (userRoleDTO.getUserRole().equals("基础网格员") || userRoleDTO.getUserRole().equals("支撑网格员")){
            log.info("【排行榜】用户身份为支撑网格员或基础网格员");
            log.info("【排行榜top2】");
            List<Object[]> objects = rankListFaultDao.find24Station(null, null, userRoleDTO.getServiceGridId(), "2019-06-04 00:00:00");
            rankListShowStationDTOList = convert(objects, "top2");

        }else {
            log.info("【排行榜】用户无身份,默认基础网格员");
            log.info("【排行榜top2】");
            List<Object[]> objects = rankListFaultDao.find24Station(null, null, userRoleDTO.getServiceGridId(), "2019-06-04 00:00:00");
            rankListShowStationDTOList = convert(objects, "top2");
        }

        return rankListShowStationDTOList;

    }


    @Override
    public List<RankListShowStationDTO> findTop3(UserRoleDTO userRoleDTO) {

        List<RankListShowStationDTO> rankListShowStationDTOList;

        // 判断用户权限
        if (userRoleDTO.getUserRole().equals("公司领导") || userRoleDTO.getUserRole().equals("业务部门")){
            log.info("【排行榜】用户身份为公司领导或业务部门");
            log.info("【排行榜top3】");
            List<Object[]> objects = rankListFaultDao.find48Station(userRoleDTO.getSpcodeId(), userRoleDTO.getBusinessOfficeId(), null, "2019-06-04 00:00:00");
            rankListShowStationDTOList = convert(objects, "top3");

        }else if (userRoleDTO.getUserRole().equals("站长")){
            log.info("【排行榜】用户身份为站长");
            log.info("【排行榜top3】");
            List<Object[]> objects = rankListFaultDao.find48Station(null, userRoleDTO.getBusinessOfficeId(), null, "2019-06-04 00:00:00");
            rankListShowStationDTOList = convert(objects, "top3");

        }else if (userRoleDTO.getUserRole().equals("基础网格员") || userRoleDTO.getUserRole().equals("支撑网格员")){
            log.info("【排行榜】用户身份为支撑网格员或基础网格员");
            log.info("【排行榜top3】");
            List<Object[]> objects = rankListFaultDao.find48Station(null, null, userRoleDTO.getServiceGridId(), "2019-06-04 00:00:00");
            rankListShowStationDTOList = convert(objects, "top3");

        }else {
            log.info("【排行榜】用户无身份,默认基础网格员");
            log.info("【排行榜top3】");
            List<Object[]> objects = rankListFaultDao.find48Station(null, null, userRoleDTO.getServiceGridId(), "2019-06-04 00:00:00");
            rankListShowStationDTOList = convert(objects, "top3");
        }

        return rankListShowStationDTOList;

    }

    @Override
    public List<RankListShowStationDTO> findTop4(UserRoleDTO userRoleDTO) {

        List<RankListShowStationDTO> rankListShowStationDTOList;

        // 判断用户权限
        if (userRoleDTO.getUserRole().equals("公司领导") || userRoleDTO.getUserRole().equals("业务部门")){
            log.info("【排行榜】用户身份为公司领导或业务部门");
            log.info("【排行榜top4】");
            List<Object[]> objects = rankListFaultDao.findInTimeWatchStation(userRoleDTO.getSpcodeId(), userRoleDTO.getBusinessOfficeId(), null, "2019-06-04 00:00:00");
            rankListShowStationDTOList = convert(objects, "top4");

        }else if (userRoleDTO.getUserRole().equals("站长")){
            log.info("【排行榜】用户身份为站长");
            log.info("【排行榜top4】");
            List<Object[]> objects = rankListFaultDao.findInTimeWatchStation(null, userRoleDTO.getBusinessOfficeId(), null, "2019-06-04 00:00:00");
            rankListShowStationDTOList = convert(objects, "top4");

        }else if (userRoleDTO.getUserRole().equals("基础网格员") || userRoleDTO.getUserRole().equals("支撑网格员")){
            log.info("【排行榜】用户身份为支撑网格员或基础网格员");
            log.info("【排行榜top4】");
            List<Object[]> objects = rankListFaultDao.findInTimeWatchStation(null, null, userRoleDTO.getServiceGridId(), "2019-06-04 00:00:00");
            rankListShowStationDTOList = convert(objects, "top4");

        }else {
            log.info("【排行榜】用户无身份,默认基础网格员");
            log.info("【排行榜top4】");
            List<Object[]> objects = rankListFaultDao.findInTimeWatchStation(null, null, userRoleDTO.getServiceGridId(), "2019-06-04 00:00:00");
            rankListShowStationDTOList = convert(objects, "top4");
        }

        return rankListShowStationDTOList;

    }

    @Override
    public List<RankListShowStationDTO> findTop5(UserRoleDTO userRoleDTO) {

        List<RankListShowStationDTO> rankListShowStationDTOList;

        // 判断用户权限
        if (userRoleDTO.getUserRole().equals("公司领导") || userRoleDTO.getUserRole().equals("业务部门")){
            log.info("【排行榜】用户身份为公司领导或业务部门");
            log.info("【排行榜top5】");
            List<Object[]> objects = rankListFaultDao.findInTimeBroadStation(userRoleDTO.getSpcodeId(), userRoleDTO.getBusinessOfficeId(), null, "2019-06-04 00:00:00");
            rankListShowStationDTOList = convert(objects, "top5");

        }else if (userRoleDTO.getUserRole().equals("站长")){
            log.info("【排行榜】用户身份为站长");
            log.info("【排行榜top5】");
            List<Object[]> objects = rankListFaultDao.findInTimeBroadStation(null, userRoleDTO.getBusinessOfficeId(), null, "2019-06-04 00:00:00");
            rankListShowStationDTOList = convert(objects, "top5");

        }else if (userRoleDTO.getUserRole().equals("基础网格员") || userRoleDTO.getUserRole().equals("支撑网格员")){
            log.info("【排行榜】用户身份为支撑网格员或基础网格员");
            log.info("【排行榜top5】");
            List<Object[]> objects = rankListFaultDao.findInTimeBroadStation(null, null, userRoleDTO.getServiceGridId(), "2019-06-04 00:00:00");
            rankListShowStationDTOList = convert(objects, "top5");

        }else {
            log.info("【排行榜】用户无身份,默认基础网格员");
            log.info("【排行榜top5】");
            List<Object[]> objects = rankListFaultDao.findInTimeBroadStation(null, null, userRoleDTO.getServiceGridId(), "2019-06-04 00:00:00");
            rankListShowStationDTOList = convert(objects, "top5");
        }

        return rankListShowStationDTOList;
    }

    @Override
    public List<RankListShowStationDTO> findTop6(UserRoleDTO userRoleDTO) {
        return null;
    }

    @Override
    public List<RankListShowStationDTO> findTop7(UserRoleDTO userRoleDTO) {

        List<RankListShowStationDTO> rankListShowStationDTOList;

        // 判断用户权限
        if (userRoleDTO.getUserRole().equals("公司领导") || userRoleDTO.getUserRole().equals("业务部门")){
            log.info("【排行榜】用户身份为公司领导或业务部门");
            log.info("【排行榜top7】");
            List<Object[]> objects = rankListLossDao.findWatchLossStation(userRoleDTO.getSpcodeId(), userRoleDTO.getBusinessOfficeId(), null, "2019-05");
            rankListShowStationDTOList = convert(objects, "top7");

        }else if (userRoleDTO.getUserRole().equals("站长")){
            log.info("【排行榜】用户身份为站长");
            log.info("【排行榜top7】");
            List<Object[]> objects = rankListLossDao.findWatchLossStation(null, userRoleDTO.getBusinessOfficeId(), null, "2019-05");
            rankListShowStationDTOList = convert(objects, "top7");

        }else if (userRoleDTO.getUserRole().equals("基础网格员") || userRoleDTO.getUserRole().equals("支撑网格员")){
            log.info("【排行榜】用户身份为支撑网格员或基础网格员");
            log.info("【排行榜top7】");
            List<Object[]> objects = rankListLossDao.findWatchLossStation(null, null, userRoleDTO.getServiceGridId(), "2019-05");
            rankListShowStationDTOList = convert(objects, "top7");

        }else {
            log.info("【排行榜】用户无身份,默认基础网格员");
            log.info("【排行榜top7】");
            List<Object[]> objects = rankListLossDao.findWatchLossStation(null, null, userRoleDTO.getServiceGridId(), "2019-05");
            rankListShowStationDTOList = convert(objects, "top7");
        }

        return rankListShowStationDTOList;

    }


    @Override
    public List<RankListShowStationDTO> findTop8(UserRoleDTO userRoleDTO) {

        List<RankListShowStationDTO> rankListShowStationDTOList;

        // 判断用户权限
        if (userRoleDTO.getUserRole().equals("公司领导") || userRoleDTO.getUserRole().equals("业务部门")){
            log.info("【排行榜】用户身份为公司领导或业务部门");
            log.info("【排行榜top8】");
            List<Object[]> objects = rankListLossDao.find20MLossStation(userRoleDTO.getSpcodeId(), userRoleDTO.getBusinessOfficeId(), null, "2019-05");
            rankListShowStationDTOList = convert(objects, "top8");

        }else if (userRoleDTO.getUserRole().equals("站长")){
            log.info("【排行榜】用户身份为站长");
            log.info("【排行榜top8】");
            List<Object[]> objects = rankListLossDao.find20MLossStation(null, userRoleDTO.getBusinessOfficeId(), null, "2019-05");
            rankListShowStationDTOList = convert(objects, "top8");

        }else if (userRoleDTO.getUserRole().equals("基础网格员") || userRoleDTO.getUserRole().equals("支撑网格员")){
            log.info("【排行榜】用户身份为支撑网格员或基础网格员");
            log.info("【排行榜top8】");
            List<Object[]> objects = rankListLossDao.find20MLossStation(null, null, userRoleDTO.getServiceGridId(), "2019-05");
            rankListShowStationDTOList = convert(objects, "top8");

        }else {
            log.info("【排行榜】用户无身份,默认基础网格员");
            log.info("【排行榜top8】");
            List<Object[]> objects = rankListLossDao.find20MLossStation(null, null, userRoleDTO.getServiceGridId(), "2019-05");
            rankListShowStationDTOList = convert(objects, "top8");
        }

        return rankListShowStationDTOList;

    }

    @Override
    public List<RankListShowStationDTO> findTop9(UserRoleDTO userRoleDTO) {

        List<RankListShowStationDTO> rankListShowStationDTOList;

        // 判断用户权限
        if (userRoleDTO.getUserRole().equals("公司领导") || userRoleDTO.getUserRole().equals("业务部门")){
            log.info("【排行榜】用户身份为公司领导或业务部门");
            log.info("【排行榜top9】");
            List<Object[]> objects = rankListLossDao.find100MLossStation(userRoleDTO.getSpcodeId(), userRoleDTO.getBusinessOfficeId(), null, "2019-05");
            rankListShowStationDTOList = convert(objects, "top9");

        }else if (userRoleDTO.getUserRole().equals("站长")){
            log.info("【排行榜】用户身份为站长");
            log.info("【排行榜top9】");
            List<Object[]> objects = rankListLossDao.find100MLossStation(null, userRoleDTO.getBusinessOfficeId(), null, "2019-05");
            rankListShowStationDTOList = convert(objects, "top9");

        }else if (userRoleDTO.getUserRole().equals("基础网格员") || userRoleDTO.getUserRole().equals("支撑网格员")){
            log.info("【排行榜】用户身份为支撑网格员或基础网格员");
            log.info("【排行榜top9】");
            List<Object[]> objects = rankListLossDao.find100MLossStation(null, null, userRoleDTO.getServiceGridId(), "2019-05");
            rankListShowStationDTOList = convert(objects, "top9");

        }else {
            log.info("【排行榜】用户无身份,默认基础网格员");
            log.info("【排行榜top9】");
            List<Object[]> objects = rankListLossDao.find100MLossStation(null, null, userRoleDTO.getServiceGridId(), "2019-05");
            rankListShowStationDTOList = convert(objects, "top9");
        }

        return rankListShowStationDTOList;

    }

    @Override
    public List<RankListShowStationDTO> findTop10(UserRoleDTO userRoleDTO) {

        List<RankListShowStationDTO> rankListShowStationDTOList;

        // 判断用户权限
        if (userRoleDTO.getUserRole().equals("公司领导") || userRoleDTO.getUserRole().equals("业务部门")){
            log.info("【排行榜】用户身份为公司领导或业务部门");
            log.info("【排行榜top10】");
            List<Object[]> objects = rankListGridDao.findGridStation(userRoleDTO.getSpcodeId(), userRoleDTO.getBusinessOfficeId(), null, "2019-05");
            rankListShowStationDTOList = convert(objects, "top10");

        }else if (userRoleDTO.getUserRole().equals("站长")){
            log.info("【排行榜】用户身份为站长");
            log.info("【排行榜top10】");
            List<Object[]> objects = rankListGridDao.findGridStation(null, userRoleDTO.getBusinessOfficeId(), null, "2019-05");
            rankListShowStationDTOList = convert(objects, "top10");

        }else if (userRoleDTO.getUserRole().equals("基础网格员") || userRoleDTO.getUserRole().equals("支撑网格员")){
            log.info("【排行榜】用户身份为支撑网格员或基础网格员");
            log.info("【排行榜top10】");
            List<Object[]> objects = rankListGridDao.findGridStation(null, null, userRoleDTO.getServiceGridId(), "2019-05");
            rankListShowStationDTOList = convert(objects, "top10");

        }else {
            log.info("【排行榜】用户无身份,默认基础网格员");
            log.info("【排行榜top10】");
            List<Object[]> objects = rankListGridDao.findGridStation(null, null, userRoleDTO.getServiceGridId(), "2019-05");
            rankListShowStationDTOList = convert(objects, "top10");
        }

        return rankListShowStationDTOList;


    }

    @Override
    public List<RankListShowStationDTO> findTop11(UserRoleDTO userRoleDTO) {

        List<RankListShowStationDTO> rankListShowStationDTOList;

        // 判断用户权限
        if (userRoleDTO.getUserRole().equals("公司领导") || userRoleDTO.getUserRole().equals("业务部门")){
            log.info("【排行榜】用户身份为公司领导或业务部门");
            log.info("【排行榜top11】");
            List<Object[]> objects = rankListFaultDao.findRepeatStation(userRoleDTO.getSpcodeId(), userRoleDTO.getBusinessOfficeId(), null, "2019-06-04 00:00:00");
            rankListShowStationDTOList = convert(objects, "top11");

        }else if (userRoleDTO.getUserRole().equals("站长")){
            log.info("【排行榜】用户身份为站长");
            log.info("【排行榜top11】");
            List<Object[]> objects = rankListFaultDao.findRepeatStation(null, userRoleDTO.getBusinessOfficeId(), null, "2019-06-04 00:00:00");
            rankListShowStationDTOList = convert(objects, "top11");

        }else if (userRoleDTO.getUserRole().equals("基础网格员") || userRoleDTO.getUserRole().equals("支撑网格员")){
            log.info("【排行榜】用户身份为支撑网格员或基础网格员");
            log.info("【排行榜top11】");
            List<Object[]> objects = rankListFaultDao.findRepeatStation(null, null, userRoleDTO.getServiceGridId(), "2019-06-04 00:00:00");
            rankListShowStationDTOList = convert(objects, "top11");

        }else {
            log.info("【排行榜】用户无身份,默认基础网格员");
            log.info("【排行榜top11】");
            List<Object[]> objects = rankListFaultDao.findRepeatStation(null, null, userRoleDTO.getServiceGridId(), "2019-06-04 00:00:00");
            rankListShowStationDTOList = convert(objects, "top11");
        }

        return rankListShowStationDTOList;

    }

}
