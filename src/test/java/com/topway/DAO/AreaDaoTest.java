package com.topway.DAO;

import com.topway.pojo.Property;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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



}