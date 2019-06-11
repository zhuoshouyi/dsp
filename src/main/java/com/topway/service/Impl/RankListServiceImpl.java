package com.topway.service.Impl;

import com.topway.DAO.RankListFaultDao;
import com.topway.DAO.RankListGridDao;
import com.topway.DAO.RankListLossDao;
import com.topway.DAO.RankListMarketDao;
import com.topway.convert.Objects2RankListContentDTOConvert;
import com.topway.dto.RankListContentDTO;
import com.topway.service.RankListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by haizhi on 2019/6/5.
 */
@Service
public class RankListServiceImpl implements RankListService{

    @Autowired
    RankListMarketDao rankListMarketDao;

    @Autowired
    RankListLossDao rankListLossDao;

    @Autowired
    RankListFaultDao rankListFaultDao;

    @Autowired
    RankListGridDao rankListGridDao;

    private String date = "2019-06-04 00:00:00";
    private String month = "2019-05";

    @Override
    public List<RankListContentDTO> findTop1(String STATION, String GRID) {
        if (STATION.equals("all")){
            if (GRID.equals("all")){
                return rankListMarketDao.findTop10(date).subList(0,10);

            }else {
                return rankListMarketDao.findByGrid(GRID, date).subList(0,10);
            }
        }else {
            return rankListMarketDao.findByStationAndGrid(STATION, GRID, date).subList(0,10);
        }
    }

    @Override
    public List<RankListContentDTO> findTop2(String STATION, String GRID) {
        List<Object[]> objects = rankListFaultDao.find24(date);
        return Objects2RankListContentDTOConvert.convert(objects);
    }

    @Override
    public List<RankListContentDTO> findTop3(String STATION, String GRID) {
        List<Object[]> objects = rankListFaultDao.find48(date);
        return Objects2RankListContentDTOConvert.convert(objects);
    }

    @Override
    public List<RankListContentDTO> findTop4(String STATION, String GRID) {
        List<Object[]> objects = rankListFaultDao.findInTimeWatch(date);
        return Objects2RankListContentDTOConvert.convert(objects);
    }

    @Override
    public List<RankListContentDTO> findTop5(String STATION, String GRID) {
        List<Object[]> objects = rankListFaultDao.findInTimeBroad(date);
        return Objects2RankListContentDTOConvert.convert(objects);
    }

    @Override
    public List<RankListContentDTO> findTop6(String STATION, String GRID) {
        return null;
    }

    @Override
    public List<RankListContentDTO> findTop7(String STATION, String GRID) {
        List<Object[]> objects = rankListLossDao.findWatchLoss(month);
        return Objects2RankListContentDTOConvert.convert(objects);
    }

    @Override
    public List<RankListContentDTO> findTop8(String STATION, String GRID) {
        List<Object[]> objects = rankListLossDao.find20MLoss(month);
        return Objects2RankListContentDTOConvert.convert(objects);
    }

    @Override
    public List<RankListContentDTO> findTop9(String STATION, String GRID) {
        List<Object[]> objects = rankListLossDao.find100MLoss(month);
        return Objects2RankListContentDTOConvert.convert(objects);
    }

    @Override
    public List<RankListContentDTO> findTop10(String STATION, String GRID) {
        List<Object[]> objects = rankListGridDao.findGrid(month);
        return Objects2RankListContentDTOConvert.convert(objects);
    }

    @Override
    public List<RankListContentDTO> findTop11(String STATION, String GRID) {
        List<Object[]> objects = rankListFaultDao.findRepeat(date);
        return Objects2RankListContentDTOConvert.convert(objects);
    }
}
