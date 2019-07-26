package com.topway.service.Impl;

import com.topway.dto.UserRoleDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * Created by haizhi on 2019/7/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RankListServiceImplTest {

    @Autowired
    RankListServiceImpl service;

    @Test
    public void findTop1() throws Exception {

        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO.setUserId("1461");
        userRoleDTO.setOpenId("1234");
        userRoleDTO.setUserName("tt");
        userRoleDTO.setUserRole("公司领导");
        userRoleDTO.setSpcodeId(Arrays.asList("天威","天隆", "光明"));
        userRoleDTO.setBusinessOfficeId(Arrays.asList("南山分公司", "福田分公司"));
//        userRoleDTO.setServiceGridId(Arrays.asList("3687","3682","3679","3677","3686","3680","3678"));
        userRoleDTO.setServiceGridId(Arrays.asList());

//        List<RankListShowStationDTO> stationDTOList = service.findTop1(userRoleDTO);
        System.out.println("");
    }

}