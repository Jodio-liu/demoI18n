package com.example.demoi18n.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessagesSceneSlaveMapper {
    List<Integer> getMsgIdBySceneId(@Param("sceneId") long sceneId);
}
