package com.topway.convert;

import com.topway.dto.*;
import com.topway.pojo.WatchAction;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by haizhi on 2019/5/27.
 */
public class WatchAction2DeviceWatchActionDTOConvert {

    // TODO
    //获取系统时间
    static Date date =new Date();
    static Calendar cal = Calendar.getInstance();
    static final int NOWYEAR = date.getYear();
    static final int LASTYEAR = date.getYear()-1;

    static final String NOWMONTH = String.valueOf(new SimpleDateFormat("yyyy-MM"));
    static final String ONEMONTHAGO = String.valueOf(new SimpleDateFormat("yyyy-MM"));
    static final String TWOMONTHAGO = String.valueOf(new SimpleDateFormat("yyyy-MM"));
    static final String THREEMONTHAGO = String.valueOf(new SimpleDateFormat("yyyy-MM"));
    static final String FOURMONTHAGO = String.valueOf(new SimpleDateFormat("yyyy-MM"));
    static final String FIVEMONTHAGO = String.valueOf(new SimpleDateFormat("yyyy-MM"));

    public static DeviceWatchActionDTO convert(List<WatchAction> watchActionList){
        DeviceWatchActionDTO deviceWatchActionDTO = new DeviceWatchActionDTO();
        WatchYearStartRateDTO watchYearStartRateDTO = new WatchYearStartRateDTO();
        WatchMonthStartRate watchMonthStartRate = new WatchMonthStartRate();
        WatchLengthOfOnline watchLengthOfOnline = new WatchLengthOfOnline();
        WatchTimesOfEnter watchTimesOfEnter = new WatchTimesOfEnter();

        watchActionList.stream()
                .forEach(e -> {
                    switch (e.getStatisticalType()){
                        case "年开机天数":
                            if (e.getStatisticalCycle().equals(NOWYEAR)){
                                watchYearStartRateDTO.setThisYear(e.getNumbers() + "/" + nowDayOnYear());
                            }else if (e.getStatisticalCycle().equals(LASTYEAR)){
                                watchYearStartRateDTO.setLastYear(e.getNumbers() + "/" + lastYearDays());
                            }
                            break;
                        case "月开机天数":

                            break;
                        case "时移业务":
                            if (e.getStatisticalCycle().equals("最近7天")){
                                watchLengthOfOnline.setSevenDayTime(String.valueOf(e.getTimeLength()/3600)+"h");
                                watchTimesOfEnter.setSevenDayTime(String.valueOf(e.getNumbers())+"次");
                            }else if(e.getStatisticalCycle().equals("最近30天")){
                                watchLengthOfOnline.setThirtyDayTime(String.valueOf(e.getTimeLength()/3600)+"h");
                                watchTimesOfEnter.setThirtyDayTime(String.valueOf(e.getNumbers())+"次");
                            }
                            break;
                        case "天天影院":
                            if (e.getStatisticalCycle().equals("最近7天")){
                                watchLengthOfOnline.setSevenDayCinema(String.valueOf(e.getTimeLength()/3600)+"h");
                                watchTimesOfEnter.setSevenDayCinema(String.valueOf(e.getNumbers())+"次");
                            }else if(e.getStatisticalCycle().equals("最近30天")){
                                watchLengthOfOnline.setThirtyDayCinema(String.valueOf(e.getTimeLength()/3600)+"h");
                                watchTimesOfEnter.setThirtyDayCinema(String.valueOf(e.getNumbers())+"次");
                            }
                            break;
                        case "单片点播":
                            if (e.getStatisticalCycle().equals("最近7天")){
                                watchLengthOfOnline.setSevenDayDrop(String.valueOf(e.getTimeLength()/3600)+"h");
                                watchTimesOfEnter.setSevenDayDrop(String.valueOf(e.getNumbers())+"次");
                            }else if(e.getStatisticalCycle().equals("最近30天")){
                                watchLengthOfOnline.setThirtyDayDrop(String.valueOf(e.getTimeLength()/3600)+"h");
                                watchTimesOfEnter.setThirtyDayDrop(String.valueOf(e.getNumbers())+"次");
                            }
                            break;
                        case "直播":
                            if (e.getStatisticalCycle().equals("最近7天")){
                                watchLengthOfOnline.setSevenDayShow(String.valueOf(e.getTimeLength()/3600)+"h");
                                watchTimesOfEnter.setSevenDayShow(String.valueOf(e.getNumbers())+"次");
                            }else if(e.getStatisticalCycle().equals("最近30天")){
                                watchLengthOfOnline.setThirtyDayShow(String.valueOf(e.getTimeLength()/3600)+"h");
                                watchTimesOfEnter.setThirtyDayShow(String.valueOf(e.getNumbers())+"次");
                            }
                            break;
                    }
                });

        watchLengthOfOnline.setSevenTotal(
                watchLengthOfOnline.getSevenDayCinema()+watchLengthOfOnline.getSevenDayDrop()+
                        watchLengthOfOnline.getSevenDayShow()+watchLengthOfOnline.getSevenDayTime()+"h"
        );
        watchLengthOfOnline.setThirtyTotal(
                watchLengthOfOnline.getThirtyDayCinema()+watchLengthOfOnline.getThirtyDayDrop()+
                        watchLengthOfOnline.getThirtyDayShow()+watchLengthOfOnline.getThirtyDayTime()+"h"
        );

        watchTimesOfEnter.setSevenTotal(
                watchLengthOfOnline.getSevenDayCinema()+watchLengthOfOnline.getSevenDayDrop()+
                        watchLengthOfOnline.getSevenDayShow()+watchLengthOfOnline.getSevenDayTime()+"次"
        );
        watchTimesOfEnter.setThirtyTotal(
                watchLengthOfOnline.getThirtyDayCinema()+watchLengthOfOnline.getThirtyDayDrop()+
                        watchLengthOfOnline.getThirtyDayShow()+watchLengthOfOnline.getThirtyDayTime()+"次"
        );
        deviceWatchActionDTO.setYearStartRate(watchYearStartRateDTO);
        deviceWatchActionDTO.setMonthStartRate(watchMonthStartRate);
        deviceWatchActionDTO.setLengthOfOnline(watchLengthOfOnline);
        deviceWatchActionDTO.setTimesOfEnter(watchTimesOfEnter);
        return deviceWatchActionDTO;
    }




    /**
     * 获取月份天数
     * @param year
     * @param month
     * @return
     */
    public static int getMaxDay(int year,int month){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR,year);
        cal.set(Calendar.MONTH,month-1);
        return  cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取今年至今天数
     *
     * @return
     */
    public static String nowDayOnYear() {

        //%tj表示一年中的第几天
        String strDate =String.format("%tj",date);

        //输出时间
        return strDate;
    }


    /**
     * 获取去年总天数
     *
     * @return
     */
    public static String lastYearDays(){
        if(LASTYEAR % 4 == 0 && LASTYEAR % 100 != 0 || LASTYEAR % 400 == 0){
            return "365";
        }else{
            return "366";
        }
    }
}
