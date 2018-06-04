package com.tbjj.portal.common.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dulei on 2/7/17.
 * 订单工具类
 */
public class Orders {

    private static final String ORDER_PREFIX = "QT";//母订单流水号
    private static final String ORDER_SUB_PREFIX = "MT";//子订单前缀
    private static final Integer RANDOM_RANGE = 3;//随机数范围

    private Orders(){}

    public static String getOrderLineNum(){
        return rebuildOrderId(ORDER_PREFIX);
    }

    public static String getOrderId(){
        return rebuildOrderId(ORDER_SUB_PREFIX);
    }

    private static String rebuildOrderId(String prefix){
        String randomNum = RandomStringUtils.random(RANDOM_RANGE, false, true);
        String dateStr = new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date());
        String id = new StringBuilder().append(prefix).append(dateStr)
                .append(randomNum).toString();
        return id;
    }


    /**
     * 计算配送时间(最大结算时间+1  如果是周日  那么就转天配送)
     */
    public static Date calculateDistributionTimeByOrderCreateTime(Date maxSettlementTime){

        DateTime maxSettlementDateTime = new DateTime(maxSettlementTime);
        return wrapDistributionTime(maxSettlementDateTime.plusDays(1).toDate());
    }

    private static Date wrapDistributionTime(Date distributionTime){
        DateTime currentDistributionDateTime = new DateTime(distributionTime);
        DateTime plusOneDay = currentDistributionDateTime.plusDays(1);
        boolean isSunday = (DateTimeConstants.SUNDAY == currentDistributionDateTime.getDayOfWeek());
        return isSunday ? plusOneDay.toDate() : currentDistributionDateTime.toDate() ;
    }



    /**
     * 计算默认结算时间
     * @param orderCreateTime
     * @return
     */
    public static Date calculateDefaultSettleTime(Date orderCreateTime){
        final DateTime DISTRIBUTION_TIME_SPLIT_TIME = new DateTime().withTime(15,0,0,0);

        DateTime orderCreateDateTime = new DateTime(orderCreateTime);
        Date today = orderCreateDateTime.toDate();
        Date tomorrow = orderCreateDateTime.plusDays(1).toDate();
        return orderCreateDateTime.isAfter(DISTRIBUTION_TIME_SPLIT_TIME) ? tomorrow : today;
    }

}
