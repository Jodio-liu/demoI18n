package com.example.demoi18n.mapper;

import com.example.demoi18n.entity.MsgIdMapSceneIdsPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessagesSceneSlaveMapper {
    List<Integer> getMsgIdBySceneId(@Param("sceneId") long sceneId);

    List<MsgIdMapSceneIdsPo> getSceneIdsByMsgIds(@Param("msgIds") List<Integer> msgIds);
}
