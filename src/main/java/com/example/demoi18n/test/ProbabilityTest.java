package com.example.demoi18n.test;

import java.util.Stack;

public class ProbabilityTest {
    public static void main(String[] args) {
        String str = "t (adasadsda, asda,sadasddsfs";
        int count = 0;
        int from = -1;
        int to = -1;
        char[] chars = str.toCharArray();
        for(int i = 0; i < chars.length; i++){
            if('(' == chars[i]){
                count ++;
                if(from == -1){
                    from = i;
                }
            }
            if(')' == chars[i]){
                count --;
                if(count == 0){
                    to = i;
                    break;
                }
            }
        }
        if(from >=0 && from < to){
            System.out.println(str.substring(from+1, to));
        }

    }


}
