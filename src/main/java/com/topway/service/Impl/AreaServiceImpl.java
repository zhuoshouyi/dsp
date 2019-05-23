package com.topway.service.Impl;

import com.topway.DAO.AreaDao;
import com.topway.dto.AreaBusinessDTO;
import com.topway.dto.AreaMonthlyDevelopmentDTO;
import com.topway.pojo.Area;
import com.topway.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by haizhi on 2019/5/22.
 */
@Service
public class AreaServiceImpl implements AreaService{

    // 实例化当天的日期
    Date today = new Date();
    // 用当天的日期减去昨天的日期
    String yesterday = new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(new Date(today.getTime()-86400000L));


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

    @Override
    public AreaBusinessDTO calAreaBusiness(String areaId) {
        AreaBusinessDTO areaBusinessDTO = new AreaBusinessDTO();

        Area area1 = dao.findByFka9350c89AndFk560a959bAndFkfceb956f(areaId, "收费", "2019-05-22 00:00:00");
//        Area area2 = dao.findByFka9350c89AndFk560a959bAndFkfceb956f(areaId, "免费", "2019-04-20 00:00:00");

        // 1 数字电视在线收费终端数(fk06266ce4 数字电视用户数)
        areaBusinessDTO.setF1(area1.getFk06266ce4());

        // 2 单电视+单宽用户(fk20d1fbb4 单电视用户数, fkcef5362c 单宽用户数)
        areaBusinessDTO.setF2(area1.getFk20d1fbb4() +
                area1.getFkcef5362c());

        // 3 高清双向终端(fk2d7cd0f7 高清双向用户数)
        areaBusinessDTO.setF3(area1.getFk2d7cd0f7());

        // 4 高清双向占比(fk2d7cd0f7 高清双向用户数, fk06266ce4 数字电视用户数)
        areaBusinessDTO.setF4((area1.getFk2d7cd0f7()) / (area1.getFk06266ce4()));

        // 5 4K终端(fkf50b49a2 高清4K用户数)
        areaBusinessDTO.setF5(area1.getFkf50b49a2());

        // 6 4K占比(fkf50b49a2 高清4K用户数, fk06266ce4 数字电视用户数)
        areaBusinessDTO.setF6((area1.getFkf50b49a2()) / (area1.getFk06266ce4()));

        // 7 新融合产品在线量(fk2705c653 新融合在线数)
        areaBusinessDTO.setF7(area1.getFk2705c653());

        // 8 新融合占比(fk2705c653 新融合在线数, fk06266ce4 数字电视用户数)
        areaBusinessDTO.setF8(area1.getFk2705c653() / area1.getFk06266ce4());

        // 9 互动业务在线量(fk3a3f0350 新融合单互动在线数)
        areaBusinessDTO.setF9(area1.getFk3a3f0350());

        // 10 订购互动业务终端数(fka6e43d86 新融合单互动用户数)
        areaBusinessDTO.setF10(area1.getFka6e43d86());

        // 11 互动业务订购率(fka6e43d86 新融合单互动用户数, fk06266ce4 数字电视用户数)
        areaBusinessDTO.setF11(area1.getFka6e43d86() / area1.getFk06266ce4());

        // 12 宽带在线收费终端(fk4de24ed7 宽带用户数)
        areaBusinessDTO.setF12(area1.getFk4de24ed7());

        // 13 单宽百兆(fk63d253a7 单宽百兆用户数)
        areaBusinessDTO.setF13(area1.getFk63d253a7());

        // 14 融合百兆(fk80c2f500 融合百兆用户数)
        areaBusinessDTO.setF14(area1.getFk80c2f500());

        // 15 百兆占比(fk27ed9a8c 百兆用户数, fk4de24ed7 宽带用户数)
        areaBusinessDTO.setF15(area1.getFk27ed9a8c() / area1.getFk4de24ed7());

        // 16 单宽光纤(fkd9012b7e 单宽光纤用户数)
        areaBusinessDTO.setF16(area1.getFkd9012b7e());

        // 17 融合光纤(fkd8fecceb 融合光纤用户数)
        areaBusinessDTO.setF17(area1.getFkd8fecceb());

        // 18 光纤占比(fk64d7d498 光纤用户数, fk4de24ed7 宽带用户数)
        areaBusinessDTO.setF18(area1.getFk64d7d498() / area1.getFk4de24ed7());

        return areaBusinessDTO;
    }


    @Override
    public AreaMonthlyDevelopmentDTO calMonthlyDevelopment(String areaId) {
        AreaMonthlyDevelopmentDTO areaMonthlyDevelopmentDTO = new AreaMonthlyDevelopmentDTO();

        Area area1 = dao.findByFka9350c89AndFk560a959bAndFkfceb956f(areaId, "收费", "2019-05-22 00:00:00");
//        Area area2 = dao.findByFka9350c89AndFk560a959bAndFkfceb956f(areaId, "免费", "2019-04-20 00:00:00");


        // 1 数字电视发展量(fk8c01e6bb 本月数字电视发展数)
        areaMonthlyDevelopmentDTO.setF1(area1.getFk8c01e6bb());

        // 2 数字电视发展占比(fk8c01e6bb 本月数字电视发展数, fk13dd3f5c 月底数字电视用户数)
        areaMonthlyDevelopmentDTO.setF2(area1.getFk8c01e6bb() / area1.getFk13dd3f5c());

        // 3 宽带发展量(fk8b3a75eb 本月宽带发展数)
        areaMonthlyDevelopmentDTO.setF3(area1.getFk8b3a75eb());

        // 4 宽带发展占比(fk8b3a75eb 本月宽带发展数, fk877f3ef8 月底宽带用户数)
        areaMonthlyDevelopmentDTO.setF4(area1.getFk8b3a75eb() / area1.getFk877f3ef8());

        // 5 数字电视流失量(fk3feba260 本月数字电视流失数)
        areaMonthlyDevelopmentDTO.setF5(area1.getFk3feba260());

        // 6 数字电视流占比(fk3feba260 本月数字电视流失数, fk13dd3f5c 月底数字电视用户数)
        areaMonthlyDevelopmentDTO.setF6(area1.getFk3feba260() / area1.getFk13dd3f5c());

        // 7 宽带流失量(fk7378541a 本月宽带流失数)
        areaMonthlyDevelopmentDTO.setF7(area1.getFk7378541a());

        // 8 宽带流失占比(fk7378541a 本月宽带流失数, fk877f3ef8 月底宽带用户数)
        areaMonthlyDevelopmentDTO.setF8(area1.getFk7378541a() / area1.getFk877f3ef8());

        return areaMonthlyDevelopmentDTO;
    }
}
