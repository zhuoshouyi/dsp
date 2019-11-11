package com.topway.service.Impl;

import com.topway.DAO.*;
import com.topway.dto.*;
import com.topway.service.RankListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    // 全局统一时间格式化格式
    SimpleDateFormat FMT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat FMT_MONTH = new SimpleDateFormat("yyyy-MM");

    // 实例化当天的日期
    Date today = new Date();

    // 用当天的日期减去一天的秒数得到昨天的日期
    String yesterday = new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(new Date(today.getTime()-86400000L));


    // 网格绩效月


    // 月初计算,累计一月

    // 当天往前推一个月
    public String getLastMonthStart(){

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        return FMT.format(m);
    }

    // 上个月的月份
    public String getLastMonth(){

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        return FMT_MONTH.format(m);
    }



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
     * filter
     *
     */
    public RankListFilterDTO rankListFilter(UserRoleDTO userRoleDTO){
        RankListFilterDTO rankListFilterDTO = new RankListFilterDTO();
        if (userRoleDTO.getUserRole().equals("公司领导") || userRoleDTO.getUserRole().equals("业务部门") || userRoleDTO.getUserRole().equals("站长")){

            // TODO 补充公司领导的排行榜权限

        }else if (userRoleDTO.getUserRole().equals("基础网格员") || userRoleDTO.getUserRole().equals("支撑网格员")){

            List<String> branchList = userDao.findBranchByGridId(userRoleDTO.getServiceGridId());
            log.info("【排行榜筛选】branchList: " + branchList.toString());
            if (branchList != null && branchList.size()>0){
                rankListFilterDTO.setBranch(branchList.get(0));
            }

            List<String> stationList = userDao.findByGridId(userRoleDTO.getServiceGridId());
            log.info("【排行榜筛选】stationList: " + stationList.toString());
            if (stationList != null && stationList.size()>0){
                rankListFilterDTO.setStation(stationList.get(0));
            }
        }

        return rankListFilterDTO;
    }


    /**
     * 工具类
     */
//    public List<RankListShowStationDTO> convert(List<Object[]> objects, String topNum){
//        List<RankListShowStationDTO> rankListShowStationDTOList = new ArrayList<>();
//        if (objects.size()>0){
//            for (int i=0; i<objects.size(); i++){
//                RankListShowStationDTO rankListShowStationDTO = new RankListShowStationDTO();
//                rankListShowStationDTO.setId(String.valueOf(i));
//                rankListShowStationDTO.setName(String.valueOf(objects.get(i)[0]));
//                rankListShowStationDTO.setValue(Double.parseDouble(objects.get(i)[1]==null?"0":objects.get(i)[1].toString()));
//                if (topNum.equals("top7") || topNum.equals("top8") || topNum.equals("top9")){
//                    rankListShowStationDTO.setPer(Double.parseDouble(objects.get(i)[2]==null?"0":objects.get(i)[2].toString()));
//                }
//                // 获取每一个 station 的 grid 排行
//                List<RankListShowGridDTO> rankListShowGridDTOList = new ArrayList<>();
//
//                List<Object[]> gridObjects = new ArrayList<>();
//                switch (topNum){
//                    case "top1":
//                        gridObjects = rankListMarketDao.findGridByStation(String.valueOf(objects.get(i)[0]), "2019-06-04 00:00:00");
//                        break;
//                    case "top2":
//                        gridObjects = rankListFaultDao.find24Grid(String.valueOf(objects.get(i)[0]), "2019-06-04 00:00:00");
//                        break;
//                    case "top3":
//                        gridObjects = rankListFaultDao.find48Grid(String.valueOf(objects.get(i)[0]), "2019-06-04 00:00:00");
//                        break;
//                    case "top4":
//                        gridObjects = rankListFaultDao.findInTimeWatchGrid(String.valueOf(objects.get(i)[0]), "2019-06-04 00:00:00");
//                        break;
//                    case "top5":
//                        gridObjects = rankListFaultDao.findInTimeBroadGrid(String.valueOf(objects.get(i)[0]), "2019-06-04 00:00:00");
//                        break;
//                    case "top6":
//                        gridObjects = rankListFaultDao.find24Grid(String.valueOf(objects.get(i)[0]), "2019-06-04 00:00:00");
//                        break;
//                    case "top7":
//                        gridObjects = rankListLossDao.findWatchLossGrid(String.valueOf(objects.get(i)[0]), "2019-05");
//                        break;
//                    case "top8":
//                        gridObjects = rankListLossDao.find20MLossGrid(String.valueOf(objects.get(i)[0]), "2019-05");
//                        break;
//                    case "top9":
//                        gridObjects = rankListLossDao.find100MLossGrid(String.valueOf(objects.get(i)[0]), "2019-05");
//                        break;
//                    case "top10":
//                        gridObjects = rankListGridDao.findGridGrid(String.valueOf(objects.get(i)[0]), "2019-05");
//                        break;
//                    case "top11":
//                        gridObjects = rankListFaultDao.findRepeatGrid(String.valueOf(objects.get(i)[0]), "2019-06-04 00:00:00");
//                        break;
//                    default:
//                        break;
//                }
//                if (gridObjects.size()>0){
//                    for (int j=0; j<gridObjects.size(); j++){
//                        RankListShowGridDTO rankListShowGridDTO = new RankListShowGridDTO();
//                        rankListShowGridDTO.setId(String.valueOf(j));
//                        rankListShowGridDTO.setName(String.valueOf(gridObjects.get(j)[0]));
//                        rankListShowGridDTO.setValue(Double.parseDouble(gridObjects.get(j)[1]==null?"0":gridObjects.get(j)[1].toString()));
//                        if (topNum.equals("top7") || topNum.equals("top8") || topNum.equals("top9")){
//                            rankListShowGridDTO.setPer(Double.parseDouble(gridObjects.get(j)[2]==null?"0":gridObjects.get(j)[2].toString()));
//                        }
//                        rankListShowGridDTO.setParentId(String.valueOf(i));
//                        rankListShowGridDTOList.add(rankListShowGridDTO);
//                    }
//                }
//
//                // 将 gridList 放进每一个 station 的 childs 下面
//                rankListShowStationDTO.setChilds(rankListShowGridDTOList);
//
//
//                // 将 station 排行放进第一层
//                rankListShowStationDTOList.add(rankListShowStationDTO);
//            }
//        }
//        return rankListShowStationDTOList;
//    }


    /**
     * objects[0]: no
     * objects[1]: person
     * objects[2]: value
     *
     * @param objects
     * @param username
     * @return
     */
    // 将 objects 转换成个人维度显示
    public List<RankListShowPersonDTO> convertPerson(List<Object[]> objects, String username) {
        List<RankListShowPersonDTO> rankListShowPersonDTOList = new ArrayList<>();
        if (objects != null && objects.size() > 0) {
            for (int i = 0; i < objects.size(); i++) {
                RankListShowPersonDTO rankListShowPersonDTO = new RankListShowPersonDTO();
                String s = String.valueOf(objects.get(i)[0]);
                String[] strings = s.split("\\.");
                rankListShowPersonDTO.setId(strings[0]);
                rankListShowPersonDTO.setName(String.valueOf(objects.get(i)[1]));
                rankListShowPersonDTO.setValue(Double.parseDouble(objects.get(i)[2] == null ? "0" : objects.get(i)[2].toString()));
                if (String.valueOf(objects.get(i)[1]).equals(username)) rankListShowPersonDTO.setIsOneself(true);
                rankListShowPersonDTOList.add(rankListShowPersonDTO);
            }
        }
        return rankListShowPersonDTOList;
    }

    /**
     *
     * objects[0]: grid
     * objects[1]: grid_id
     * objects[2]: value
     * objects[3]: value_per (null)
     *
     * @param objects
     * @param topNum
     * @param grids
     * @return
     */
    // 将 objects 转换成网格维度显示
    public List<RankListShowGridDTO> convertGrid(List<Object[]> objects, String topNum, List<String> grids) {
        List<RankListShowGridDTO> rankListShowGridDTOList = new ArrayList<>();
        if (objects != null && objects.size() > 0) {
            for (int i = 0; i < objects.size(); i++) {
                RankListShowGridDTO rankListShowGridDTO = new RankListShowGridDTO();
                rankListShowGridDTO.setId(String.valueOf(i+1));
                rankListShowGridDTO.setName(String.valueOf(objects.get(i)[0]));
                rankListShowGridDTO.setValue(Double.parseDouble(objects.get(i)[2] == null ? "0" : objects.get(i)[2].toString()));
                if (topNum.equals("top7") || topNum.equals("top8") || topNum.equals("top9")) {
                    rankListShowGridDTO.setPer(Double.parseDouble(objects.get(i)[3] == null ? "0" : objects.get(i)[3].toString()));
                }
                // 将匹配上的网格都打上标记
                for (String grid : grids){
                    if (grid.equals(String.valueOf(objects.get(i)[1]))){
                        rankListShowGridDTO.setIsOneself(true);
                        break;
                    }
                }
                rankListShowGridDTOList.add(rankListShowGridDTO);
            }
        }
        return rankListShowGridDTOList;
    }


    public RankListFilterDTO findRankList(UserRoleDTO userRoleDTO){
        RankListFilterDTO rankListFilterDTO = new RankListFilterDTO();
        List<String> branchList = userDao.findBranchByGridId(userRoleDTO.getServiceGridId());
        if (branchList != null && branchList.size()>0){
            rankListFilterDTO.setBranch(branchList.get(0));
        }

        List<String> stationList = userDao.findByGridId(userRoleDTO.getServiceGridId());
        if (stationList != null && stationList.size()>0){
            rankListFilterDTO.setStation(stationList.get(0));
        }
        return rankListFilterDTO;
    }





    /** 排行榜11个指标计算 */
    @Override
    public List<RankListShowPersonDTO> findTop1(UserRoleDTO userRoleDTO, String branchOrStation, String gridOrPerson){

        List<RankListShowPersonDTO> rankListShowPersonDTOList;
        RankListFilterDTO rankListFilterDTO = findRankList(userRoleDTO);
        List<Object[]> objects = new ArrayList<>();

        // 判断用户权限
//        if (userRoleDTO.getUserRole().equals("公司领导") || userRoleDTO.getUserRole().equals("业务部门")){
//            log.info("【排行榜】用户身份为公司领导或业务部门");
//            log.info("【排行榜top1】");
//            List<Object[]> objects = rankListMarketDao.findStationsBySpcodeAndBranchAndGrid(userRoleDTO.getSpcodeId(), userRoleDTO.getBusinessOfficeId(), null, "2019-06-04 00:00:00");
//            rankListShowStationDTOList = convert(objects, "top1");
//
//        }else if (userRoleDTO.getUserRole().equals("站长")){
//            log.info("【排行榜】用户身份为站长");
//            log.info("【排行榜top1】");
//            List<Object[]> objects = rankListMarketDao.findStationsBySpcodeAndBranchAndGrid(null, userRoleDTO.getBusinessOfficeId(), null, "2019-06-04 00:00:00");
//            rankListShowStationDTOList = convert(objects, "top1");
//
//        }else if (userRoleDTO.getUserRole().equals("基础网格员") || userRoleDTO.getUserRole().equals("支撑网格员")){
//            log.info("【排行榜】用户身份为支撑网格员或基础网格员");
//            log.info("【排行榜top1】");
//            List<Object[]> objects = rankListMarketDao.findStationsBySpcodeAndBranchAndGrid(null, null, userRoleDTO.getServiceGridId(), "2019-06-04 00:00:00");
//            rankListShowStationDTOList = convert(objects, "top1");
//
//        }else {
//            log.info("【排行榜】用户无身份,默认基础网格员");
//            log.info("【排行榜top1】");
//            List<Object[]> objects = rankListMarketDao.findStationsBySpcodeAndBranchAndGrid(null, null, userRoleDTO.getServiceGridId(), "2019-06-04 00:00:00");
//            rankListShowStationDTOList = convert(objects, "top1");
//        }

        if (branchOrStation.equals("分公司")){

            log.info("【Top1】排行范围: 分公司  排行维度: 个人维度");
            objects = rankListMarketDao.findPersonByBranchAndStation(rankListFilterDTO.getBranch(), null, userRoleDTO.getUserName(), getLastMonthStart(), yesterday);
            rankListShowPersonDTOList = convertPerson(objects, userRoleDTO.getUserName());

        }else if (branchOrStation.equals("分部")){

            log.info("【Top1】排行范围: 分部  排行维度: 个人维度");
            objects = rankListMarketDao.findPersonByBranchAndStation(null, rankListFilterDTO.getStation(), userRoleDTO.getUserName(), getLastMonthStart(), yesterday);
            rankListShowPersonDTOList = convertPerson(objects, userRoleDTO.getUserName());

        }else {
            rankListShowPersonDTOList = null;
        }

        return rankListShowPersonDTOList;
    }


    @Override
    public List findTop2(UserRoleDTO userRoleDTO, String branchOrStation, String gridOrPerson) {

        List<RankListShowPersonDTO> rankListShowPersonDTOList = new ArrayList<>();
        List<RankListShowGridDTO> rankListShowGridDTOList = new ArrayList<>();
        RankListFilterDTO rankListFilterDTO = findRankList(userRoleDTO);
        List<Object[]> objects = new ArrayList<>();

        if (branchOrStation.equals("分公司") && gridOrPerson.equals("个人维度")){

            log.info("【Top2】排行范围: 分公司  排行维度: 个人维度");
            objects = rankListFaultDao.find24Person(rankListFilterDTO.getBranch(), null, userRoleDTO.getUserName(), getLastMonthStart(), yesterday);
            rankListShowPersonDTOList = convertPerson(objects, userRoleDTO.getUserName());
            return rankListShowPersonDTOList;

        }else if (branchOrStation.equals("分公司") && gridOrPerson.equals("网格维度")){

            log.info("【Top2】排行范围: 分公司  排行维度: 网格维度");
            objects = rankListFaultDao.find24Grid(rankListFilterDTO.getBranch(), null, getLastMonthStart(), yesterday);
            rankListShowGridDTOList = convertGrid(objects, "top2", userRoleDTO.getServiceGridId());
            return rankListShowGridDTOList;

        }else if (branchOrStation.equals("分部") && gridOrPerson.equals("个人维度")){

            log.info("【Top2】排行范围: 分部  排行维度: 个人维度");
            objects = rankListFaultDao.find24Person(null, rankListFilterDTO.getStation(), userRoleDTO.getUserName(), getLastMonthStart(), yesterday);
            rankListShowPersonDTOList = convertPerson(objects, userRoleDTO.getUserName());
            return rankListShowPersonDTOList;

        }else if (branchOrStation.equals("分部") && gridOrPerson.equals("网格维度")){

            log.info("【Top2】排行范围: 分部  排行维度: 网格维度");
            objects = rankListFaultDao.find24Grid(null, rankListFilterDTO.getStation(), getLastMonthStart(), yesterday);
            rankListShowGridDTOList = convertGrid(objects, "top2", userRoleDTO.getServiceGridId());
            return rankListShowGridDTOList;

        }else {
            return null;
        }
    }

    @Override
    public List findTop3(UserRoleDTO userRoleDTO, String branchOrStation, String gridOrPerson) {

        List<RankListShowPersonDTO> rankListShowPersonDTOList = new ArrayList<>();
        List<RankListShowGridDTO> rankListShowGridDTOList = new ArrayList<>();
        RankListFilterDTO rankListFilterDTO = findRankList(userRoleDTO);
        List<Object[]> objects = new ArrayList<>();

        if (branchOrStation.equals("分公司") && gridOrPerson.equals("个人维度")){

            log.info("【Top3】排行范围: 分公司  排行维度: 个人维度");
            objects = rankListFaultDao.find48Person(rankListFilterDTO.getBranch(), null, userRoleDTO.getUserName(), getLastMonthStart(), yesterday);
            rankListShowPersonDTOList = convertPerson(objects, userRoleDTO.getUserName());
            return rankListShowPersonDTOList;

        }else if (branchOrStation.equals("分公司") && gridOrPerson.equals("网格维度")){

            log.info("【Top3】排行范围: 分公司  排行维度: 网格维度");
            objects = rankListFaultDao.find48Grid(rankListFilterDTO.getBranch(), null, getLastMonthStart(), yesterday);
            rankListShowGridDTOList = convertGrid(objects, "top3", userRoleDTO.getServiceGridId());
            return rankListShowGridDTOList;

        }else if (branchOrStation.equals("分部") && gridOrPerson.equals("个人维度")){

            log.info("【Top3】排行范围: 分部  排行维度: 个人维度");
            objects = rankListFaultDao.find48Person(null, rankListFilterDTO.getStation(), userRoleDTO.getUserName(), getLastMonthStart(), yesterday);
            rankListShowPersonDTOList = convertPerson(objects, userRoleDTO.getUserName());
            return rankListShowPersonDTOList;

        }else if (branchOrStation.equals("分部") && gridOrPerson.equals("网格维度")){

            log.info("【Top3】排行范围: 分部  排行维度: 网格维度");
            objects = rankListFaultDao.find48Grid(null, rankListFilterDTO.getStation(), getLastMonthStart(), yesterday);
            rankListShowGridDTOList = convertGrid(objects, "top3", userRoleDTO.getServiceGridId());
            return rankListShowGridDTOList;

        }else {
            return null;
        }
    }

    @Override
    public List findTop4(UserRoleDTO userRoleDTO, String branchOrStation, String gridOrPerson) {

        List<RankListShowPersonDTO> rankListShowPersonDTOList = new ArrayList<>();
        List<RankListShowGridDTO> rankListShowGridDTOList = new ArrayList<>();
        RankListFilterDTO rankListFilterDTO = findRankList(userRoleDTO);
        List<Object[]> objects = new ArrayList<>();

        if (branchOrStation.equals("分公司") && gridOrPerson.equals("个人维度")){

            log.info("【Top4】排行范围: 分公司  排行维度: 个人维度");
            objects = rankListFaultDao.findInTimeWatchPerson(rankListFilterDTO.getBranch(), null, userRoleDTO.getUserName(), getLastMonthStart(), yesterday);
            rankListShowPersonDTOList = convertPerson(objects, userRoleDTO.getUserName());
            return rankListShowPersonDTOList;

        }else if (branchOrStation.equals("分公司") && gridOrPerson.equals("网格维度")){

            log.info("【Top4】排行范围: 分公司  排行维度: 网格维度");
            objects = rankListFaultDao.findInTimeWatchGrid(rankListFilterDTO.getBranch(), null, getLastMonthStart(), yesterday);
            rankListShowGridDTOList = convertGrid(objects, "top4", userRoleDTO.getServiceGridId());
            return rankListShowGridDTOList;

        }else if (branchOrStation.equals("分部") && gridOrPerson.equals("个人维度")){

            log.info("【Top4】排行范围: 分部  排行维度: 个人维度");
            objects = rankListFaultDao.findInTimeWatchPerson(null, rankListFilterDTO.getStation(), userRoleDTO.getUserName(), getLastMonthStart(), yesterday);
            rankListShowPersonDTOList = convertPerson(objects, userRoleDTO.getUserName());
            return rankListShowPersonDTOList;

        }else if (branchOrStation.equals("分部") && gridOrPerson.equals("网格维度")){

            log.info("【Top4】排行范围: 分部  排行维度: 网格维度");
            objects = rankListFaultDao.findInTimeWatchGrid(null, rankListFilterDTO.getStation(), getLastMonthStart(), yesterday);
            rankListShowGridDTOList = convertGrid(objects, "top4", userRoleDTO.getServiceGridId());
            return rankListShowGridDTOList;

        }else {
            return null;
        }
    }

    @Override
    public List findTop5(UserRoleDTO userRoleDTO, String branchOrStation, String gridOrPerson) {

        List<RankListShowPersonDTO> rankListShowPersonDTOList = new ArrayList<>();
        List<RankListShowGridDTO> rankListShowGridDTOList = new ArrayList<>();
        RankListFilterDTO rankListFilterDTO = findRankList(userRoleDTO);
        List<Object[]> objects = new ArrayList<>();

        if (branchOrStation.equals("分公司") && gridOrPerson.equals("个人维度")){

            log.info("【Top5】排行范围: 分公司  排行维度: 个人维度");
            objects = rankListFaultDao.findInTimeBroadPerson(rankListFilterDTO.getBranch(), null, userRoleDTO.getUserName(), getLastMonthStart(), yesterday);
            rankListShowPersonDTOList = convertPerson(objects, userRoleDTO.getUserName());
            return rankListShowPersonDTOList;

        }else if (branchOrStation.equals("分公司") && gridOrPerson.equals("网格维度")){

            log.info("【Top5】排行范围: 分公司  排行维度: 网格维度");
            objects = rankListFaultDao.findInTimeBroadGrid(rankListFilterDTO.getBranch(), null, getLastMonthStart(), yesterday);
            rankListShowGridDTOList = convertGrid(objects, "top5", userRoleDTO.getServiceGridId());
            return rankListShowGridDTOList;

        }else if (branchOrStation.equals("分部") && gridOrPerson.equals("个人维度")){

            log.info("【Top5】排行范围: 分部  排行维度: 个人维度");
            objects = rankListFaultDao.findInTimeBroadPerson(null, rankListFilterDTO.getStation(), userRoleDTO.getUserName(), getLastMonthStart(), yesterday);
            rankListShowPersonDTOList = convertPerson(objects, userRoleDTO.getUserName());
            return rankListShowPersonDTOList;

        }else if (branchOrStation.equals("分部") && gridOrPerson.equals("网格维度")){

            log.info("【Top5】排行范围: 分部  排行维度: 网格维度");
            objects = rankListFaultDao.findInTimeBroadGrid(null, rankListFilterDTO.getStation(), getLastMonthStart(), yesterday);
            rankListShowGridDTOList = convertGrid(objects, "top5", userRoleDTO.getServiceGridId());
            return rankListShowGridDTOList;

        }else {
            return null;
        }
    }

    @Override
    public List<RankListShowStationDTO> findTop6(UserRoleDTO userRoleDTO, String branchOrStation, String gridOrPerson) {
        return null;
    }

    @Override
    public List<RankListShowGridDTO> findTop7(UserRoleDTO userRoleDTO, String branchOrStation, String gridOrPerson) {

        List<RankListShowGridDTO> rankListShowGridDTOList = new ArrayList<>();
        RankListFilterDTO rankListFilterDTO = findRankList(userRoleDTO);
        List<Object[]> objects = new ArrayList<>();

        if (branchOrStation.equals("分公司")){

            log.info("【Top7】排行范围: 分公司  排行维度: 网格维度");
            objects = rankListLossDao.findWatchLossGrid(rankListFilterDTO.getBranch(), null, getLastMonth());
            rankListShowGridDTOList = convertGrid(objects, "top7", userRoleDTO.getServiceGridId());
            return rankListShowGridDTOList;

        }else if (branchOrStation.equals("分部")){

            log.info("【Top7】排行范围: 分部  排行维度: 网格维度");
            objects = rankListLossDao.findWatchLossGrid(null, rankListFilterDTO.getStation(), getLastMonth());
            rankListShowGridDTOList = convertGrid(objects, "top7", userRoleDTO.getServiceGridId());
            return rankListShowGridDTOList;

        }else {
            return null;
        }
    }

    @Override
    public List<RankListShowGridDTO> findTop8(UserRoleDTO userRoleDTO, String branchOrStation, String gridOrPerson) {

        List<RankListShowGridDTO> rankListShowGridDTOList = new ArrayList<>();
        RankListFilterDTO rankListFilterDTO = findRankList(userRoleDTO);
        List<Object[]> objects = new ArrayList<>();

        if (branchOrStation.equals("分公司")){

            log.info("【Top8】排行范围: 分公司  排行维度: 网格维度");
            objects = rankListLossDao.find20MLossGrid(rankListFilterDTO.getBranch(), null, getLastMonth());
            rankListShowGridDTOList = convertGrid(objects, "top8", userRoleDTO.getServiceGridId());
            return rankListShowGridDTOList;

        }else if (branchOrStation.equals("分部")){

            log.info("【Top8】排行范围: 分部  排行维度: 网格维度");
            objects = rankListLossDao.find20MLossGrid(null, rankListFilterDTO.getStation(), getLastMonth());
            rankListShowGridDTOList = convertGrid(objects, "top8", userRoleDTO.getServiceGridId());
            return rankListShowGridDTOList;

        }else {
            return null;
        }
    }

    @Override
    public List<RankListShowGridDTO> findTop9(UserRoleDTO userRoleDTO, String branchOrStation, String gridOrPerson) {

        List<RankListShowGridDTO> rankListShowGridDTOList = new ArrayList<>();
        RankListFilterDTO rankListFilterDTO = findRankList(userRoleDTO);
        List<Object[]> objects = new ArrayList<>();

        if (branchOrStation.equals("分公司")){

            log.info("【Top9】排行范围: 分公司  排行维度: 网格维度");
            objects = rankListLossDao.find100MLossGrid(rankListFilterDTO.getBranch(), null, getLastMonth());
            rankListShowGridDTOList = convertGrid(objects, "top9", userRoleDTO.getServiceGridId());
            return rankListShowGridDTOList;

        }else if (branchOrStation.equals("分部")){

            log.info("【Top9】排行范围: 分部  排行维度: 网格维度");
            objects = rankListLossDao.find100MLossGrid(null, rankListFilterDTO.getStation(), getLastMonth());
            rankListShowGridDTOList = convertGrid(objects, "top9", userRoleDTO.getServiceGridId());
            return rankListShowGridDTOList;

        }else {
            return null;
        }
    }

    @Override
    public List<RankListShowGridDTO> findTop10(UserRoleDTO userRoleDTO, String branchOrStation, String gridOrPerson) {

        List<RankListShowGridDTO> rankListShowGridDTOList = new ArrayList<>();
        RankListFilterDTO rankListFilterDTO = findRankList(userRoleDTO);
        List<Object[]> objects = new ArrayList<>();

        if (branchOrStation.equals("分公司")){

            log.info("【Top10】排行范围: 分公司  排行维度: 网格维度");
            objects = rankListGridDao.findGridGrid(rankListFilterDTO.getBranch(), null, getLastMonth());
            rankListShowGridDTOList = convertGrid(objects, "top10", userRoleDTO.getServiceGridId());
            return rankListShowGridDTOList;

        }else if (branchOrStation.equals("分部")){

            log.info("【Top10】排行范围: 分部  排行维度: 网格维度");
            objects = rankListGridDao.findGridGrid(null, rankListFilterDTO.getStation(), getLastMonth());
            rankListShowGridDTOList = convertGrid(objects, "top10", userRoleDTO.getServiceGridId());
            return rankListShowGridDTOList;

        }else {
            return null;
        }
    }

    @Override
    public List<RankListShowGridDTO> findTop11(UserRoleDTO userRoleDTO, String branchOrStation, String gridOrPerson) {

        List<RankListShowGridDTO> rankListShowGridDTOList = new ArrayList<>();
        RankListFilterDTO rankListFilterDTO = findRankList(userRoleDTO);
        List<Object[]> objects = new ArrayList<>();

        if (branchOrStation.equals("分公司")){

            log.info("【Top11】排行范围: 分公司  排行维度: 网格维度");
            objects = rankListGridDao.findRepeatGrid(rankListFilterDTO.getBranch(), null, getLastMonth());
            rankListShowGridDTOList = convertGrid(objects, "top11", userRoleDTO.getServiceGridId());
            return rankListShowGridDTOList;

        }else if (branchOrStation.equals("分部")){

            log.info("【Top11】排行范围: 分部  排行维度: 网格维度");
            objects = rankListGridDao.findRepeatGrid(null, rankListFilterDTO.getStation(), getLastMonth());
            rankListShowGridDTOList = convertGrid(objects, "top11", userRoleDTO.getServiceGridId());
            return rankListShowGridDTOList;

        }else {
            return null;
        }
    }

}
