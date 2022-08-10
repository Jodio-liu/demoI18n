package com.example.demoi18n.test;

import com.alibaba.fastjson.JSON;
import com.google.api.client.util.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        List<Integer> msgIds = new ArrayList<>();
        for(int i=0; i<20; i++){
            msgIds.add(i+1);
        }
        int pageIndex = 1;
        int count = 3;
        int nextInt = new Random(20).nextInt(10);
        while((pageIndex-1) * 3 < 20){
            extracted(msgIds, pageIndex, count, nextInt);
            pageIndex++;
        }
    }

    private static void extracted(List<Integer> msgIds, int pageIndex, int count, int seed) {
        Collections.shuffle(msgIds, new Random(seed));
        System.out.println(seed + ":" + JSON.toJSON(msgIds));
        int start = (pageIndex -1) * 3;
        int size = msgIds.size();
        int end = Math.min(pageIndex * 3, size);
        List<Integer> subMsgIds = msgIds.subList(0, count);
        if(subMsgIds.size() < count){
            supplementMsgInfos(subMsgIds, msgIds, pageIndex, count);
        }
        System.out.println(JSON.toJSON(subMsgIds));
    }

    private static void supplementMsgInfos(List<Integer> subMsgIds, List<Integer> msgIds, int pageIndex, int count) {
        int supplementCount = count - subMsgIds.size();
        List<Integer> tmpList = msgIds.subList(0, count * (pageIndex - 1));
        Collections.shuffle(tmpList);
        subMsgIds.addAll(tmpList.subList(0, supplementCount));
    }
}
