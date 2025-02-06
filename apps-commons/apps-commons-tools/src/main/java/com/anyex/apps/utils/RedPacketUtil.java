package com.anyex.apps.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RedPacketUtil
{
    float       MINMONEY = 0f;
    
    float       MAXMONEY = 200f;
    
    final float TIMES    = 2.1f;
    
    private float randomRedPacket(float money, float mins, float maxs, int count)
    {
        if (count == 1)
        { return (float) (Math.round(money * 1000000)) / 1000000; }
        if (mins == maxs)
        {
            return mins;// 如果最大值和最小值一样，就返回mins
        }
        float max = maxs > money ? money : maxs;
        float one = ((float) Math.random() * (max - mins) + mins);
        one = (float) (Math.round(one * 1000000)) / 1000000;
        float moneyOther = money - one;
        if (isRight(moneyOther, count - 1))
        {
            return one;
        }
        else
        {
            // 重新分配
            float avg = moneyOther / (count - 1);
            if (avg < MINMONEY)
            {
                return randomRedPacket(money, mins, one, count);
            }
            else if (avg > MAXMONEY)
            { return randomRedPacket(money, one, maxs, count); }
        }
        return one;
    }
    
    private boolean isRight(float money, int count)
    {
        double avg = money / count;
        if (avg < MINMONEY)
        {
            return false;
        }
        else if (avg > MAXMONEY)
        { return false; }
        return true;
    }
    
    public List<Float> splitRedPackets(float money, int count, BigDecimal singleMax, BigDecimal singeMin)
    {
        this.MINMONEY = singeMin.floatValue();
        this.MAXMONEY = singleMax.floatValue();
        if (!isRight(money, count))
        { return null; }
        List<Float> list = new ArrayList<Float>();
        float max = (float) (money * TIMES / count);
        max = max > MAXMONEY ? MAXMONEY : max;
        for (int i = 0; i < count; i++)
        {
            float one = randomRedPacket(money, MINMONEY, max, count - i);
            list.add(one);
            money -= one;
        }
        return list;
    }
    
//    public static void main(String[] args)
//    {
//        System.out.println(new RedPacketUtil().splitRedPackets(10, 10, BigDecimal.valueOf(200), BigDecimal.valueOf(0.001)));
//    }
}
