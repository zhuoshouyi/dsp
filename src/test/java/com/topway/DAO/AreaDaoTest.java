package com.topway.DAO;

import com.topway.pojo.Area;
import com.topway.pojo.Property;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haizhi on 2019/5/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaDaoTest{

//    private EntityManagerFactory emf;
//
//    @PersistenceUnit//使用这个标记来注入EntityManagerFactory
//    public void setEntityManagerFactory(EntityManagerFactory emf) {
//        this.emf = emf;
//    }
//
    @Autowired
    AreaDao dao;

    @Autowired
    PropertyDao propertyDao;
//
//    @Test
//    public void findById() {
//        System.out.println("result=" + dao.findById(1).orElse(null));
//    }
//
    @Test
    public void save(){
        Property property = propertyDao.findByAreaId("7777");
        property.setPropertyAddress("jjjjjj");
        propertyDao.save(property);
    }
//
//    @Test
//    public void findByAreaId(){
////        List<Area> areaList = dao.findByFka9350c89("1110");
////        for (Area area : areaList ){
////            System.out.println("area=" + area.toString());
////        }
//        Area area = dao.findByFka9350c89("1110");
//        System.out.println("area" + area.toString());
//    }
//
//    @Test
//    public void findByUniqId(){
//        Area area = dao.findByFka9350c89AndFk560a959bAndFkfceb956f("1110", "免费", "2019-04-20 00:00:00");
//        System.out.println("area="+area);
//    }


    @Test
    public void findByAreaId(){

        List<String> gridList = new ArrayList<>();
        gridList.add("911");
        gridList.add("128");
        List<String> spcodeList = new ArrayList();
        spcodeList.add("天威");
        List<String> businessList = new ArrayList();
        businessList.add("罗湖分公司");

        Area daoByAreaId = dao.findByAreaId("3164", "免费", "2019-05-27 00:00:00", gridList, null, null);
//        Area daoByAreaId = dao.findByAreaId("3164", "免费", "2019-05-27 00:00:00", "'911','128'", "天威", "罗湖分公司");
        System.out.println("");

    }

    @Test
    public void findByName(){
        PageRequest pageRequest = new PageRequest(0, 5);

        List<String> gridList = new ArrayList<>();
        gridList.add("705");
        gridList.add("742");

        Page<Area> areaPage = dao.findByFk999cd340Like("光明", "2019-05-27 00:00:00", gridList, null, null, pageRequest);
        System.out.println("");
    }

}