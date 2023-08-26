package com.example.demoi18n.test;

import com.example.demoi18n.test.VO.PetSkinWeight;
import com.example.demoi18n.test.VO.WishExtJsonInfo;
import com.google.api.client.util.Lists;

import java.util.*;
import java.util.stream.Collectors;

public class Test<A, B, C, D> {
    private A a;
    private B b;
    private C c;
    private D d;

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public C getC() {
        return c;
    }

    public void setC(C c) {
        this.c = c;
    }

    public D getD() {
        return d;
    }

    public void setD(D d) {
        this.d = d;
    }

    public <T extends Number & Comparable<? super Integer>> void test(T t) {
        List<Object> s = Lists.newArrayList();
        s.add(1);
        s.add("23");
        s.add(1.2);
        System.out.println(t);
    }

    public static String calculateText(String input) {
        StringBuilder sb = new StringBuilder();
        String[] lines = input.split("\n");
        int lineCount = 0;
        for (int i = 0; i < lines.length && lineCount < 10; i++) {
            String line = lines[i];
            while (line.length() > 53 && lineCount < 9) {
                int index = line.lastIndexOf(" ", 53);
                if (index == -1) {
                    break;
                }
                sb.append(line, 0, index+1);
                line = line.substring(index+1);
                lineCount++;
            }
            if (lineCount < 9) {
                sb.append(line).append("\n");
                lineCount++;
            } else if (lineCount == 9 && line.length() > 53) {
                sb.append(line, 0, 40).append("...");
                lineCount++;
            } else {
                break;
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {

        String a = "qweqewqe'11";
        System.out.println(a.replaceAll("'", "''"));

//        List<PetSkinWeight> list = Lists.newArrayList();
//        PetSkinWeight petSkinWeight = new PetSkinWeight();
//        petSkinWeight.setWeight(1);
//        petSkinWeight.setCoverUrl("11111");
//        petSkinWeight.setSkin("qqqqq");
//        list.add(petSkinWeight);
//        PetSkinWeight petSkinWeight1 = new PetSkinWeight();
//        petSkinWeight1.setWeight(2);
//        petSkinWeight1.setCoverUrl("22222");
//        petSkinWeight1.setSkin("wwwww");
//        list.add(petSkinWeight1);
//        PetSkinWeight petSkinWeight2 = new PetSkinWeight();
//        petSkinWeight2.setWeight(3);
//        petSkinWeight2.setCoverUrl("33333");
//        petSkinWeight2.setSkin("eeeee");
//        list.add(petSkinWeight2);
//
//        List<WishExtJsonInfo> collect = list.stream().map(Test::filterPo).filter(Objects::nonNull).collect(Collectors.toList());
//        System.out.println(collect);
    }

    private static WishExtJsonInfo filterPo(PetSkinWeight po) {
        if(po.getWeight() < 2){
            return null;
        } else {
            WishExtJsonInfo info = new WishExtJsonInfo();
            info.setSkin(po.getSkin());
            return info;
        }

    }

}
