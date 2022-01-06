package com.vdc.board.repository.interfaces;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BoardRepository {

    // Total Call
    Map<String, Object> getTotalCallInfo();

    // Job Call
    Map<String, Map<String, Object>> getJobCallInfo();
    List<Map<String, Object>> getJobCallInfoList();

    // Team Call
    Map<String, Object> getTeamCallInfo();
    List<Map<String, Object>> getIndividualPerformance();

    // Team Counselor
    Map<String, Object> getTeamCounselorInfo();
    Map<String, Object> getTeamCounselorInfo2();
    List<Map<String, Object>> getIndividualCounselorState();

}
