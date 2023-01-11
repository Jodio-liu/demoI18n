package com.example.demoi18n.mapper;

import com.example.demoi18n.entity.UserItemPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserItemMapper {

    int insert(@Param("userId") long userId,
               @Param("objId") String objId,
               @Param("objType") long objType);

    List<UserItemPo> queryItemByUserId(@Param("userId") long userId);
}
