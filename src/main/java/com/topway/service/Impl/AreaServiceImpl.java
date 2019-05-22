package com.topway.service.Impl;

import com.topway.DAO.AreaDao;
import com.topway.pojo.Area;
import com.topway.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by haizhi on 2019/5/22.
 */
@Service
public class AreaServiceImpl implements AreaService{

    @Autowired
    AreaDao dao;

    @Override
    public List<Area> findByAreaList() {
        return null;
    }

    @Override
    public Area findByAreaId(String areaId) {
        // TODO 修改日期,改为昨天
//        Area area = dao.findByFka9350c89AndFk560a959bAndFkfceb956f(areaId, "免费", "2019-04-20 00:00:00");
        Area area = dao.findByFka9350c89(areaId);
        return area;
    }

    @Override
    public Page<Area> findByAreaNameLike(String areaName, Pageable pageable) {
        Page<Area> areaPage = dao.findByFk999cd340Like(areaName, pageable);
        return areaPage;
    }
}
