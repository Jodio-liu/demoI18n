package com.example.demoi18n.test.VO;

import lombok.Data;

import java.util.Map;

@Data
public class GameRankConf {
    private Map<String, GameRankInfo> divison_sheet;
}
