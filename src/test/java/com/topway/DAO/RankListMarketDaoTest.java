package com.topway.DAO;

import com.topway.convert.Objects2RankListContentDTOConvert;
import com.topway.dto.RankListContentDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Created by haizhi on 2019/6/5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RankListMarketDaoTest {

    @Autowired
    RankListMarketDao dao;

    @Test
    public void findByStationAndGrid() throws Exception {

    }

    @Test
    public void findByGrid() throws Exception {

    }

    @Test
    public void findTop10() throws Exception {

        List<String> stationList = Arrays.asList("前海分部");
        List<String> gridList = Arrays.asList("后海A");

        List<Object[]> objects = dao.findTop10("2019-06-04 00:00:00", stationList, gridList);
        List<RankListContentDTO> dtoList = Objects2RankListContentDTOConvert.convert(objects);
        System.out.println("");
    }

}