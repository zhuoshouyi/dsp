package com.topway.DAO;

import com.topway.pojo.Area;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * Created by haizhi on 2019/5/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaDaoTest{

    private EntityManagerFactory emf;

    @PersistenceUnit//使用这个标记来注入EntityManagerFactory
    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Autowired
    AreaDao dao;

    @Test
    public void findById() {
        System.out.println("result=" + dao.findById(1).orElse(null));
    }

    @Test
    public void save(){
        Area area = new Area();
        area.setFk3c052170("jkl");
        area.setFk1adc9b4a(123);
        dao.save(area);
        System.out.println("area = " + area.toString());
    }

    @Test
    public void findByAreaId(){
//        List<Area> areaList = dao.findByFka9350c89("1110");
//        for (Area area : areaList ){
//            System.out.println("area=" + area.toString());
//        }
        Area area = dao.findByFka9350c89("1110");
        System.out.println("area" + area.toString());
    }

    @Test
    public void findByUniqId(){
        Area area = dao.findByFka9350c89AndFk560a959bAndFkfceb956f("1110", "免费", "2019-04-20 00:00:00");
        System.out.println("area="+area);
    }

    @Test
    public void page(){
        PageRequest pageRequest = new PageRequest(0,1);
        Page<Area> result = dao.findByFk999cd340Like("天然居", pageRequest);
        System.out.println(result.getTotalElements());

    }

}