package com.vdc.board.repository.classes;

import com.vdc.board.common.enums.TeamCounselorSecondConst;
import com.vdc.board.common.enums.TotalCallConst;
import com.vdc.board.repository.interfaces.BoardRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.IntStream;

@Repository
public class MemoryBoardRepository implements BoardRepository {

    private Map<String, Object> totalCallInfo;

    private Map<String, Map<String, Object>> jobCallInfo;
    private List<Map<String, Object>> jobCallInfoList;

    private Map<String, Object> teamCallInfo;
    private List<Map<String, Object>> individualPerformanceList;

    private String[] labels = new String[] {"Received", "Answered", "Abandoned", "AnswerRate", "ServiceLevel"};

    private String[] nameArray = new String[] {"고구정", "이지연", "김미현A", "이서하", "권태정", "이수언", "이정현", "이은옥"};
    private Integer[] countArray = new Integer[] {0, 12, 54, 38, 30, 49, 33, 25};

    private Map<String, Object> teamCounselorInfo;
    private Map<String, Object> teamCounselorInfo2;
    private List<Map<String, Object>> individualStateList;

    private String[] stateArray = new String[] {"INIT", "MEAL", "REST", "EDUCATION", "WORK", "CALLING", "OFF", "HANDLING"};
    private String[] timeArray = new String[] {"0:00:00", "00:00:49", "0:00:00", "0:00:00", "00:02:47", "0:00:00", "00:01:09", "00:04:59"};


    public MemoryBoardRepository() {
        initTotalCallInfo();

        initJobCallInfo();

        initTeamCallInfo();
        initIndividualPerformance();

        initTeamCounselorInfo();
        initTeamCounselorInfo2();
        initIndividualCounselorState();
    }

    @Override
    public Map<String, Object> getTotalCallInfo() {
        return totalCallInfo;
    }

    @Override
    public Map<String, Map<String, Object>> getJobCallInfo() {
        return jobCallInfo;
    }

    @Override
    public List<Map<String, Object>> getJobCallInfoList() {
        return jobCallInfoList;
    }

    @Override
    public Map<String, Object> getTeamCallInfo() {
        return teamCallInfo;
    }

    @Override
    public List<Map<String, Object>> getIndividualPerformance() {
        return individualPerformanceList;
    }

    @Override
    public Map<String, Object> getTeamCounselorInfo() {
        return teamCounselorInfo;
    }

    @Override
    public Map<String, Object> getTeamCounselorInfo2() {
        return teamCounselorInfo2;
    }

    @Override
    public List<Map<String, Object>> getIndividualCounselorState() {
        return individualStateList;
    }

    public void initTotalCallInfo() {
        totalCallInfo = new HashMap<>();

        totalCallInfo.put(TotalCallConst.RECEIVED.getKey(), "2,915");
        totalCallInfo.put(TotalCallConst.LOGIN.getKey(), "17");
        totalCallInfo.put(TotalCallConst.ANSWERED.getKey(), "1,051");
        totalCallInfo.put(TotalCallConst.IDLE.getKey(), "5");
        totalCallInfo.put(TotalCallConst.ABANDONED.getKey(), "1,856");
        totalCallInfo.put(TotalCallConst.WAIT_CALLS.getKey(), "27");
        totalCallInfo.put(TotalCallConst.ANSWER_RATE.getKey(), "36.1%");
        totalCallInfo.put(TotalCallConst.AVERAGE_ANSWER_WAIT_TIME.getKey(), "0:01:27");
    }

    public void initJobCallInfo() {
        jobCallInfo = new HashMap<>();
        jobCallInfoList = new ArrayList<>();

        HashMap<String, Object> total = setMap(new HashMap<>(), new String[]{"2,916", "1,051", "1,856", "36.0%", "6.2%"});
        jobCallInfo.put("total", total);
        jobCallInfoList.add(total);

        HashMap<String, Object> work = setMap(new HashMap<>(), new String[]{"620", "207", "411", "33.4%", "0.6%"});
        jobCallInfo.put("work", work);
        jobCallInfoList.add(work);

        HashMap<String, Object> insurance = setMap(new HashMap<>(), new String[]{"50", "33", "17", "66.0%", "0.0"});
        jobCallInfo.put("insurance", insurance);
        jobCallInfoList.add(insurance);

        HashMap<String, Object> skill = setMap(new HashMap<>(), new String[]{"504", "340", "159", "67.5%", "3.4%"});
        jobCallInfo.put("skill", skill);
        jobCallInfoList.add(skill);

        HashMap<String, Object> foreigner = setMap(new HashMap<>(), new String[]{"277", "234", "42", "84.5%", "34.3%"});
        jobCallInfo.put("foreigner", foreigner);
        jobCallInfoList.add(foreigner);

        HashMap<String, Object> etc = setMap(new HashMap<>(), new String[]{"193", "155", "37", "80.3%", "28.5%"});
        jobCallInfo.put("etc", etc);
        jobCallInfoList.add(etc);
    }

    public HashMap<String, Object> setMap(HashMap<String, Object> mapName, String[] data) {

        IntStream.range(0, data.length).forEach(i -> mapName.put(labels[i], data[i]));

        return mapName;
    }

    public void initTeamCallInfo() {
        teamCallInfo = new HashMap<>();

        teamCallInfo.put("Received", "277");
        teamCallInfo.put("WaitCalls", "0");
        teamCallInfo.put("Answered", "233");
        teamCallInfo.put("AverageAnsweredWaitTime", "0:01:34");
        teamCallInfo.put("AnswerRate", "84.1%");
        teamCallInfo.put("ServiceLevel", "34.3%");
    }

    public void initIndividualPerformance() {
        individualPerformanceList = new ArrayList<>();

        IntStream.range(0, nameArray.length).forEach(i -> {
            HashMap<String, Object> individualMap = new HashMap<>();
            individualMap.put("Emp_Nm", nameArray[i]);
            individualMap.put("Handled", countArray[i]);
            individualPerformanceList.add(individualMap);
        });
    }

    public void initTeamCounselorInfo() {
        teamCounselorInfo = new HashMap<>();

        teamCounselorInfo.put("Received", "277");
        teamCounselorInfo.put("Answered", "234");
        teamCounselorInfo.put("AnswerRate", "84.5%");
        teamCounselorInfo.put("WaitCalls", "0");
        teamCounselorInfo.put("AverageAnsweredWaitTime", "0:01:34");
        teamCounselorInfo.put("ServiceLevel", "34.3%");
    }

    public void initTeamCounselorInfo2() {
        teamCounselorInfo2 = new HashMap<>();

        teamCounselorInfo2.put(TeamCounselorSecondConst.LOGIN.toString(), "4");
        teamCounselorInfo2.put(TeamCounselorSecondConst.IDLE.toString(), "4");
        teamCounselorInfo2.put(TeamCounselorSecondConst.BUSY.toString(), "0");
        teamCounselorInfo2.put(TeamCounselorSecondConst.WORK.toString(), "0");
        teamCounselorInfo2.put(TeamCounselorSecondConst.AWAY.toString(), "0");
    }

    public void initIndividualCounselorState() {
        individualStateList = new ArrayList<>();

        IntStream.range(0, nameArray.length).forEach(i -> {
            HashMap<String, Object> individualMap = new HashMap<>();
            individualMap.put("Emp_Nm", nameArray[i]);
            individualMap.put("CurrentActTime", timeArray[i]);
            individualMap.put("State", stateArray[i]);
            individualStateList.add(individualMap);
        });
    }
}
