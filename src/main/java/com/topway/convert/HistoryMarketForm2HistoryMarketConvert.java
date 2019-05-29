package com.topway.convert;

import com.topway.form.HistoryMarketForm;
import com.topway.pojo.HistoryMarket;

/**
 * Created by haizhi on 2019/5/29.
 */
public class HistoryMarketForm2HistoryMarketConvert {

    public static HistoryMarket convert(HistoryMarketForm historyMarketForm){
        HistoryMarket historyMarket = new HistoryMarket();
        historyMarket.setAreaId(historyMarketForm.getAreaId());
        historyMarket.setContent(historyMarketForm.getContent());

        return historyMarket;
    }
}
