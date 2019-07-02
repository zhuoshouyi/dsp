package com.topway.service.Impl;

import com.topway.DAO.WarningLossDao;
import com.topway.DAO.WarningMarketDao;
import com.topway.DAO.WarningServiceDao;
import com.topway.dto.UserRoleDTO;
import com.topway.service.WarningService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haizhi on 2019/6/25.
 */
@Service
@Slf4j
public class WarningServiceImpl implements WarningService {

    @Autowired
    WarningMarketDao warningMarketDao;

    @Autowired
    WarningLossDao warningLossDao;

    @Autowired
    WarningServiceDao warningServiceDao;

    @Override
    public List<Double> WatchLossNumAndWbLossNum(UserRoleDTO userRoleDTO) {

        Object[] objects = warningLossDao.findByGridIdAndDate(userRoleDTO.getServiceGridId(), "2019-05");
        Object[] object0 = (Object[])objects[0];
        List<Double> doubleList = new ArrayList<>();
        System.out.println(object0.length);
        if (object0!=null && object0.length>0){
            doubleList.add(object0[0]==null ? 0.0 : (double)object0[0]);
            doubleList.add(object0[1]==null ? 0.0 : (double)object0[1]);
        }

        return doubleList;
    }



    @Override
    public Double InstallSuccess24h(UserRoleDTO userRoleDTO){

        Object[] objects = warningServiceDao.findBy24(userRoleDTO.getServiceGridId());
//        Object[] object0 = (Object[]) objects[0];

        return Double.valueOf(objects[0]==null ? "0" : objects[0].toString());
    }



    @Override
    public Double MarketEffectNum(UserRoleDTO userRoleDTO){

        Object[] objects = warningMarketDao.findByGridId("2019-06-23 00:00:00", userRoleDTO.getServiceGridId());
//        Object[] object0 = (Object[]) objects[0];

        return Double.valueOf(objects[0]==null ? "0" : objects[0].toString());
    }


    @Override
    public Double RegionAverageNum(UserRoleDTO userRoleDTO){

        Object[] objects = warningServiceDao.findByRegionAverage("2019-06-23 00:00:00", userRoleDTO.getServiceGridId());
//        Object[] object0 = (Object[]) objects[0];

        return Double.valueOf(objects[0]==null ? "0" : objects[0].toString());
    }

    @Override
    public Double RegionFaultSuccess(UserRoleDTO userRoleDTO){

        Object[] objects = warningServiceDao.findByFaultSuccess("2019-06-23 00:00:00", userRoleDTO.getServiceGridId());
//        Object[] object0 = (Object[]) objects[0];

        return Double.valueOf(objects[0]==null ? "0" : objects[0].toString());
    }



}
