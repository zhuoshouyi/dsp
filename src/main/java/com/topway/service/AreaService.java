package com.topway.service;

import com.topway.pojo.Area;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by haizhi on 2019/5/22.
 */
@Service
public interface AreaService {

    List<Area> findByAreaList();

    Area findByAreaId(String areaId);

    Page<Area> findByAreaNameLike(String areaName, Pageable pageable);


}
