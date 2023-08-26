package com.example.demoi18n.test;


import com.alibaba.fastjson.JSON;
import com.example.demoi18n.test.VO.GameRankConf;
import com.google.common.collect.Lists;
import org.apache.commons.lang.time.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ProbabilityTest {

    public static void main(String[] args) {
        String text = "aas asad";
        List<String> buttonTextList = Lists.newArrayList(text.split("##"));
        // 生成随机索引
        Random random = new Random();
        int randomIndex = random.nextInt(buttonTextList.size());
        System.out.println(buttonTextList.get(randomIndex));
    }


}
