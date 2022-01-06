package com.vdc.board.repository.classes;

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

    private String[] labels = new String[] {"title", "total_input", "response", "give_up", "response_rate", "service_level"};

    private String[] nameArray = new String[] {"고구정", "이지연", "김미현A", "이서하", "권태정", "이수언", "이정현", "이은옥"};
    private Integer[] countArray = new Integer[] {0, 12, 54, 38, 30, 49, 33, 25};

    private Map<String, Object> teamCounselorInfo;
    private Map<String, Object> teamCounselorInfo2;
    private List<Map<String, Object>> individualStateList;

    private String[] stateArray = new String[] {"init", "meal", "rest", "education", "work", "calling", "off", "handling"};
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

        totalCallInfo.put("total_input", "2,915");
        totalCallInfo.put("login", "17");
        totalCallInfo.put("response", "1,051");
        totalCallInfo.put("waiter", "5");
        totalCallInfo.put("give_up", "1,856");
        totalCallInfo.put("wait_call", "27");
        totalCallInfo.put("response_rate", "36.1%");
        totalCallInfo.put("average_wait_time", "0:01:27");
    }

    public void initJobCallInfo() {
        jobCallInfo = new HashMap<>();
        jobCallInfoList = new ArrayList<>();

        HashMap<String, Object> total = setMap(new HashMap<>(), new String[]{"전 체", "2,916", "1,051", "1,856", "36.0%", "6.2%"});
        jobCallInfo.put("total", total);
        jobCallInfoList.add(total);

        HashMap<String, Object> work = setMap(new HashMap<>(), new String[]{"워크넷", "620", "207", "411", "33.4%", "0.6%"});
        jobCallInfo.put("work", work);
        jobCallInfoList.add(work);

        HashMap<String, Object> foreigner = setMap(new HashMap<>(), new String[]{"외국인고용", "50", "33", "17", "66.0%", "0.0%"});
        jobCallInfo.put("foreigner", foreigner);
        jobCallInfoList.add(foreigner);

        HashMap<String, Object> insurance = setMap(new HashMap<>(), new String[]{"고용보험", "504", "340", "159", "67.5%", "3.4%"});
        jobCallInfo.put("insurance", insurance);
        jobCallInfoList.add(insurance);

        HashMap<String, Object> skill = setMap(new HashMap<>(), new String[]{"능력개발", "277", "234", "42", "84.5%", "34.3%"});
        jobCallInfo.put("skill", skill);
        jobCallInfoList.add(skill);

        HashMap<String, Object> counselor = setMap(new HashMap<>(), new String[]{"상담원연결", "193", "155", "37", "80.3%", "28.5%"});
        jobCallInfo.put("counselor", counselor);
        jobCallInfoList.add(counselor);

        HashMap<String, Object> etc = setMap(new HashMap<>(), new String[]{"기 타", "1,272", "82", "1,190", "6.4%", "0.7%"});
        jobCallInfo.put("etc", etc);
        jobCallInfoList.add(etc);
    }

    public HashMap<String, Object> setMap(HashMap<String, Object> mapName, String[] data) {

        IntStream.range(0, data.length).forEach(i -> mapName.put(labels[i], data[i]));

        return mapName;
    }

    public void initTeamCallInfo() {
        teamCallInfo = new HashMap<>();

        teamCallInfo.put("input", "277");
        teamCallInfo.put("wait_call", "0");
        teamCallInfo.put("response", "233");
        teamCallInfo.put("average_wait_time", "0:01:34");
        teamCallInfo.put("response_rate", "84.1%");
        teamCallInfo.put("service", "34.3%");
    }

    public void initIndividualPerformance() {
        individualPerformanceList = new ArrayList<>();

        IntStream.range(0, nameArray.length).forEach(i -> {
            HashMap<String, Object> individualMap = new HashMap<>();
            individualMap.put("name", nameArray[i]);
            individualMap.put("count", countArray[i]);
            individualPerformanceList.add(individualMap);
        });
    }

    public void initTeamCounselorInfo() {
        teamCounselorInfo = new HashMap<>();

        teamCounselorInfo.put("input", "277");
        teamCounselorInfo.put("response", "234");
        teamCounselorInfo.put("response_rate", "84.5%");
        teamCounselorInfo.put("wait_call", "0");
        teamCounselorInfo.put("average_wait_time", "0:01:34");
        teamCounselorInfo.put("service", "34.3%");
    }

    public void initTeamCounselorInfo2() {
        teamCounselorInfo2 = new HashMap<>();

        teamCounselorInfo2.put("login", "4");
        teamCounselorInfo2.put("wait", "4");
        teamCounselorInfo2.put("calling", "0");
        teamCounselorInfo2.put("after_handle", "0");
        teamCounselorInfo2.put("other", "0");
    }

    public void initIndividualCounselorState() {
        individualStateList = new ArrayList<>();

        IntStream.range(0, nameArray.length).forEach(i -> {
            HashMap<String, Object> individualMap = new HashMap<>();
            individualMap.put("name", nameArray[i]);
            individualMap.put("time", timeArray[i]);
            individualMap.put("state", stateArray[i]);
            individualStateList.add(individualMap);
        });
    }
}
