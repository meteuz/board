package com.vdc.board.repository.interfaces;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface BoardMapper {

    // Test
    Map<String, Object> getTestData(Map<String, Object> map);
}
