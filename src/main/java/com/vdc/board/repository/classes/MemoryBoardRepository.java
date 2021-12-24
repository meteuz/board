package com.vdc.board.repository.classes;

import com.vdc.board.repository.interfaces.BoardRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryBoardRepository implements BoardRepository {

    private Map<String, Object> totalCallInfo;
    private Map<String, Map<String, Object>> jobCallInfo;
    private List<Map<String, Object>> jobCallInfoList;

    private String[] labels = new String[] {"total_input", "response", "give_up", "response_rate", "service_level"};

    public MemoryBoardRepository() {
        initTotalCallInfo();
        initJobCallInfo();
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
        return null;
    }

    @Override
    public List<Map<String, Object>> getIndividualPerformance() {
        return null;
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

        HashMap<String, Object> total = setMap(new HashMap<>(), new String[]{"2,916", "1,051", "1,856", "36.0%", "6.2%"});
        jobCallInfo.put("total", total);
        jobCallInfoList.add(total);

        HashMap<String, Object> work = setMap(new HashMap<>(), new String[]{"620", "207", "411", "33.4%", "0.6%"});
        jobCallInfo.put("work", work);
        jobCallInfoList.add(work);

        HashMap<String, Object> foreigner = setMap(new HashMap<>(), new String[]{"50", "33", "17", "66.0%", "0.0%"});
        jobCallInfo.put("foreigner", foreigner);
        jobCallInfoList.add(foreigner);

        HashMap<String, Object> insurance = setMap(new HashMap<>(), new String[]{"504", "340", "159", "67.5%", "3.4%"});
        jobCallInfo.put("insurance", insurance);
        jobCallInfoList.add(insurance);

        HashMap<String, Object> skill = setMap(new HashMap<>(), new String[]{"277", "234", "42", "84.5%", "34.3%"});
        jobCallInfo.put("skill", skill);
        jobCallInfoList.add(skill);

        HashMap<String, Object> counselor = setMap(new HashMap<>(), new String[]{"193", "155", "37", "80.3%", "28.5%"});
        jobCallInfo.put("counselor", counselor);
        jobCallInfoList.add(counselor);

        HashMap<String, Object> etc = setMap(new HashMap<>(), new String[]{"1,272", "82", "1,190", "6.4%", "0.7%"});
        jobCallInfo.put("etc", etc);
        jobCallInfoList.add(etc);
    }

    public HashMap<String, Object> setMap(HashMap<String, Object> mapName, String[] data) {

        for (int i = 0; i < data.length; i++) {
            mapName.put(labels[i], data[i]);
        }

        return mapName;
    }
}
