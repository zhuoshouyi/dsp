package com.topway.convert;

import com.topway.dto.*;
import com.topway.pojo.WatchAction;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by haizhi on 2019/5/27.
 */
public class WatchAction2DeviceWatchActionDTOConvert {

    // TODO
    // 全局统一时间格式化格式
    static SimpleDateFormat FMT = new SimpleDateFormat("yyyy-MM");

    // 全局统一保留两位小数格式化
    static DecimalFormat DF = new java.text.DecimalFormat("###.00");

    // 获取系统时间
    static Date date =new Date();

    // 保留两位小数
    DecimalFormat df0 = new DecimalFormat("#");
    DecimalFormat df2 = new DecimalFormat("#.00");

    static final int NOWYEAR = date.getYear();
    static final int LASTYEAR = date.getYear()-1;

    static final String NOWMONTH = monthago(0);
    static final String ONEMONTHAGO = monthago(1);
    static final String TWOMONTHAGO = monthago(2);
    static final String THREEMONTHAGO = monthago(3);
    static final String FOURMONTHAGO = monthago(4);
    static final String FIVEMONTHAGO = monthago(5);

    public static DeviceWatchActionDTO convert(List<WatchAction> watchActionList){
        DeviceWatchActionDTO deviceWatchActionDTO = new DeviceWatchActionDTO();
        WatchYearStartRateDTO watchYearStartRateDTO = new WatchYearStartRateDTO();
        WatchMonthStartRate watchMonthStartRate = new WatchMonthStartRate();
        WatchLengthOfOnline watchLengthOfOnline = new WatchLengthOfOnline();
        WatchTimesOfEnter watchTimesOfEnter = new WatchTimesOfEnter();

        final double[] watchLengthOfOnlineSevenTotal = {0};
        final double[] watchLengthOfOnlineThirtyTotal = {0};
        final double[] watchTimesOfEnterSevenTotal = {0};
        final double[] watchTimesOfEnterThirtyTotal = {0};

        watchActionList.stream()
                .forEach(e -> {
                    switch (e.getStatisticalType()){

                        case "年开机天数":
                            //
                            if (e.getStatisticalCycle().equals(NOWYEAR)){
                                watchYearStartRateDTO.setThisYear(e.getNumbers() + "/" + nowDayOnYear());
                            }else if (e.getStatisticalCycle().equals(LASTYEAR)){
                                watchYearStartRateDTO.setLastYear(e.getNumbers() + "/" + lastYearDays());
                            }
                            break;
                        case "月开机天数":
                            if (e.getStatisticalCycle().equals(NOWMONTH)){
                                watchMonthStartRate.setNowMonthAgo(e.getNumbers() + "/" + monthAgoDays(0));
                            }else if (e.getStatisticalCycle().equals(ONEMONTHAGO)){
                                watchMonthStartRate.setOneMonthAgo(e.getNumbers() + "/" + monthAgoDays(1));
                            }else if (e.getStatisticalCycle().equals(TWOMONTHAGO)){
                                watchMonthStartRate.setTwoMonthAgo(e.getNumbers() + "/" + monthAgoDays(2));
                            }else if (e.getStatisticalCycle().equals(THREEMONTHAGO)){
                                watchMonthStartRate.setThreeMonthAgo(e.getNumbers() + "/" + monthAgoDays(3));
                            }else if (e.getStatisticalCycle().equals(FOURMONTHAGO)){
                                watchMonthStartRate.setFourMonthAgo(e.getNumbers() + "/" + monthAgoDays(4));
                            }else if (e.getStatisticalCycle().equals(FIVEMONTHAGO)){
                                watchMonthStartRate.setFiveMonthAgo(e.getNumbers() + "/" + monthAgoDays(5));
                            }
                            break;
                        case "时移业务":
                            if (e.getStatisticalCycle().equals("最近7天")){
                                watchLengthOfOnline.setSevenDayTime(String.valueOf(DF.format(e.getTimeLength()/3600))+"h");
                                watchTimesOfEnter.setSevenDayTime(String.valueOf(e.getNumbers())+"次");
                                watchLengthOfOnlineSevenTotal[0] += e.getTimeLength()/3600;
                                watchTimesOfEnterSevenTotal[0] += e.getNumbers();
                            }else if(e.getStatisticalCycle().equals("最近30天")){
                                watchLengthOfOnline.setThirtyDayTime(String.valueOf(DF.format(e.getTimeLength()/3600))+"h");
                                watchTimesOfEnter.setThirtyDayTime(String.valueOf(e.getNumbers())+"次");
                                watchLengthOfOnlineThirtyTotal[0] += e.getTimeLength()/3600;
                                watchLengthOfOnlineThirtyTotal[0] += e.getNumbers();
                            }
                            break;
                        case "天天影院":
                            if (e.getStatisticalCycle().equals("最近7天")){
                                watchLengthOfOnline.setSevenDayCinema(String.valueOf(DF.format(e.getTimeLength()/3600))+"h");
                                watchTimesOfEnter.setSevenDayCinema(String.valueOf(e.getNumbers())+"次");
                                watchLengthOfOnlineSevenTotal[0] += e.getTimeLength()/3600;
                                watchTimesOfEnterSevenTotal[0] += e.getNumbers();
                            }else if(e.getStatisticalCycle().equals("最近30天")){
                                watchLengthOfOnline.setThirtyDayCinema(String.valueOf(DF.format(e.getTimeLength()/3600))+"h");
                                watchTimesOfEnter.setThirtyDayCinema(String.valueOf(e.getNumbers())+"次");
                                watchLengthOfOnlineSevenTotal[0] += e.getTimeLength()/3600;
                                watchTimesOfEnterSevenTotal[0] += e.getNumbers();
                            }
                            break;
                        case "单片点播":
                            if (e.getStatisticalCycle().equals("最近7天")){
                                watchLengthOfOnline.setSevenDayDrop(String.valueOf(DF.format(e.getTimeLength()/3600))+"h");
                                watchTimesOfEnter.setSevenDayDrop(String.valueOf(e.getNumbers())+"次");
                            }else if(e.getStatisticalCycle().equals("最近30天")){
                                watchLengthOfOnline.setThirtyDayDrop(String.valueOf(DF.format(e.getTimeLength()/3600))+"h");
                                watchTimesOfEnter.setThirtyDayDrop(String.valueOf(e.getNumbers())+"次");
                            }
                            break;
                        case "直播":
                            if (e.getStatisticalCycle().equals("最近7天")){
                                watchLengthOfOnline.setSevenDayShow(String.valueOf(DF.format(e.getTimeLength()/3600))+"h");
                                watchTimesOfEnter.setSevenDayShow(String.valueOf(e.getNumbers())+"次");
                            }else if(e.getStatisticalCycle().equals("最近30天")){
                                watchLengthOfOnline.setThirtyDayShow(String.valueOf(DF.format(e.getTimeLength()/3600))+"h");
                                watchTimesOfEnter.setThirtyDayShow(String.valueOf(e.getNumbers())+"次");
                            }
                            break;
                    }
                });

        watchLengthOfOnline.setSevenTotal(DF.format(watchLengthOfOnlineSevenTotal[0])+"h");
        watchLengthOfOnline.setThirtyTotal(DF.format(watchLengthOfOnlineThirtyTotal[0])+"h");

        watchTimesOfEnter.setSevenTotal(watchTimesOfEnterSevenTotal[0]+"次");
        watchTimesOfEnter.setThirtyTotal(watchTimesOfEnterThirtyTotal[0]+"次");

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


    /**
     * 计算本月、上月、上上月、上上上月等月份
     * @param month
     * @return
     */
    public static String monthago(int month){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -month);
        Date date = cal.getTime();
        return FMT.format(date);
    }

    /**
     * 计算本月、上月、上上月等月份天数
     * @param month
     * @return
     */
    public static int monthAgoDays(int month){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        if (month==0){
            return cal.get(Calendar.DAY_OF_MONTH);
        }else {
            cal.add(Calendar.MONTH, -month);
            cal.setTime(cal.getTime());
            return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
    }
}
