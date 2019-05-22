package com.topway.service.Impl;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by haizhi on 2019/5/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaServiceImplTest {

    @Autowired
    AreaServiceImpl areaService;

//    @Test
//    public void findByAreaId() throws Exception {
//        Area area = areaService.findByAreaId("1110");
//        System.out.println("area=" + area.toString());
//    }
//
//    @Test
//    public void findByNameLike() throws Exception{
//        PageRequest pageRequest = new PageRequest(0, 1);
//        Page<Area> areaPage = areaService.findByAreaNameLike("天然居", pageRequest);
//        System.out.println(areaPage.getTotalElements());
//        System.out.println(areaPage.getContent());
//    }

}