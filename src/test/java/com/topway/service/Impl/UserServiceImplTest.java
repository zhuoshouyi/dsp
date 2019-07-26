package com.topway.service.Impl;

import com.topway.dto.DeviceWatchActionDTO;
import com.topway.dto.DeviceWorkOrderDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by haizhi on 2019/5/31.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    UserServiceImpl userService;


    @Test
    public void findDeviceWorkOrderDetailDTO() throws Exception {
        List<DeviceWorkOrderDTO> deviceWorkOrderDTOList = userService.findDeviceWorkOrderDTO("1010577290", "6100000237362");
        System.out.println("");

    }

    @Test
    public void findDeviceComplaintDetailDTO() throws Exception {

    }

    @Test
    public void save() throws Exception {

    }

    @Test
    public void findDeviceWatchActionDTO() {

        DeviceWatchActionDTO userServiceDeviceWatchActionDTO = userService.findDeviceWatchActionDTO("3001431951", "8075588017430627");
        System.out.println("");

    }


}