package com.vdc.board.controller;

import com.vdc.board.common.util.Utils;
import com.vdc.board.service.interfaces.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    private String[] season = new String[]{"spring", "summer", "fall", "winter"};

    private String[] totalCallTitles = new String[]{"총인입", "로그인", "응 대", "대기자", "포 기", "대기호", "응대율", "평균대기시간"};
    private String[] totalCallKeys = new String[]{"Received", "LOGIN", "Answered", "IDLE", "Abandoned", "WaitCalls", "AnswerRate", "AverageAnsweredWaitTime"};

    private String[] jobCallTitles = new String[]{"구 분", "총인입", "응 대", "포 기", "응대율", "서비스레벨"};
    private String[] jobCallKeys = new String[]{"title", "Received", "Answered", "Abandoned", "AnswerRate", "ServiceLevel"};

    private String[] teamCallTitles = new String[]{"인 입", "대 기 호", "응 대", "평균대기시간", "응 대 율", "서비스레벨"};
    private String[] teamCallKeys = new String[]{"Received", "WaitCalls", "Answered", "AverageAnsweredWaitTime", "AnswerRate", "ServiceLevel"};

    private String[] teamCounselorFirstTitles = new String[]{"인 입", "응 대", "응 대 율", "대 기 호", "평균대기시간", "서비스레벨"};
    private String[] teamCounselorFirstKeys = new String[]{"Received", "Answered", "AnswerRate", "WaitCalls", "AverageAnsweredWaitTime", "ServiceLevel"};
    private String[] teamCounselorSecondTitles = new String[]{"로 그 인", "대 기", "통 화 중", "후 처 리", "이 석"};
    private String[] teamCounselorSecondKeys = new String[]{"LOGIN", "IDLE", "BUSY", "WORK", "AWAY"};
    // 상담원상태
    //-이석(초기, 식사,휴식,교육,업무)
    //-후처리
    //-통화중
    //-로그오프
    //-기타
    // IDLE, WORK, CONNECTED, AWAY,
    private Map<String, Object> stateColorMap = new HashMap<String, Object>() {{
        put("init", "icon_init");
        put("meal", "icon_meal");
        put("rest", "icon_rest");
        put("education", "icon_education");
        put("work", "icon_work");
        put("calling", "icon_calling");
        put("off", "icon_off");
        put("handling", "icon_handling");
    }};
    private Map<String, Object> stateIconMap = new HashMap<String, Object>() {{
        put("init", "fa-keyboard");
        put("meal", "fa-utensils");
        put("rest", "fa-mug-hot");
        put("education", "fa-chalkboard-teacher");
        put("work", "fa-phone-rotary");
        put("calling", "fa-phone-volume");
        put("off", "fa-user-slash");
        put("handling", "fa-pencil-alt");
    }};

    // AgentStatus(로그인, 대기, 통화중, 후처리, 이석)
    // QueueRealTime(대기호, 평균대기시간, 서비스레벨)
    // QueueCumulative_Rate(인입, 응대, 포기, 응대율)
    // getUserRealTime(개인별 상태)

    @RequestMapping("current-date")
    public String currentDate(@RequestParam Map<String, Object> param, Model model) {

        model.addAttribute("season", season[Integer.parseInt((String) param.get("season")) - 1]);

        model.addAttribute("currentDate", Utils.getCurrentDate());
        model.addAttribute("currentTime", Utils.getCurrentTime());

        return "/board/current-date";
    }

    @RequestMapping("total-call")
    public String totalCall(@RequestParam Map<String, Object> param, Model model) {

        model.addAttribute("season", season[Integer.parseInt((String) param.get("season")) - 1]);

//        Map<String, Object> totalCallInfo = boardService.getTotalCallInfo();
//        model.addAttribute("totalCallInfo", totalCallInfo);
        Map<String, Object> queueCumulativeRate = boardService.getQueueCumulativeRate(param);
        Map<String, Object> queueRealTime = boardService.getQueueRealTime(param);
        queueRealTime.putAll(queueCumulativeRate);
        Map<String, Object> agentStatus = boardService.getAgentStatus(param);
        queueRealTime.putAll(agentStatus);
        model.addAttribute("queueRealTime", queueRealTime);

        model.addAttribute("totalCallTitles", totalCallTitles);
        model.addAttribute("totalCallKeys", totalCallKeys);

        return "/board/total-call";
    }

    @RequestMapping("job-call")
    public String jobCall(@RequestParam Map<String, Object> param, Model model) {

        model.addAttribute("season", season[Integer.parseInt((String) param.get("season")) - 1]);

//        Map<String, Map<String, Object>> jobCallInfo = boardService.getJobCallInfo();
//        model.addAttribute("jobCallInfo", jobCallInfo);

        List<Map<String, Object>> jobCallInfoList = boardService.getJobCallInfoList();
        model.addAttribute("jobCallInfoList", jobCallInfoList);

        model.addAttribute("jobCallTitles", jobCallTitles);
        model.addAttribute("jobCallKeys", jobCallKeys);

        return "/board/job-call";
    }

    @RequestMapping("team-call")
    public String teamCall(@RequestParam Map<String, Object> param, Model model) {

        model.addAttribute("season", season[Integer.parseInt((String) param.get("season")) - 1]);

//        Map<String, Object> teamCallInfo = boardService.getTeamCallInfo();
//        model.addAttribute("teamCallInfo", teamCallInfo);
        Map<String, Object> queueCumulativeRate = boardService.getQueueCumulativeRate(param);
        Map<String, Object> queueRealTime = boardService.getQueueRealTime(param);
        queueRealTime.putAll(queueCumulativeRate);
        model.addAttribute("queueRealTime", queueRealTime);

//        List<Map<String, Object>> individualPerformance = boardService.getIndividualPerformance();
//        model.addAttribute("individualPerformance", individualPerformance);
        List<Map<String, Object>> userRealTime = boardService.getUserRealTime(param);
        model.addAttribute("userRealTime", userRealTime);

        model.addAttribute("teamCallTitles", teamCallTitles);
        model.addAttribute("teamCallKeys", teamCallKeys);

        return "/board/team-call";
    }

    @RequestMapping("team-counselor")
    public String teamCounselor(@RequestParam Map<String, Object> param, Model model) {

        model.addAttribute("season", season[Integer.parseInt((String) param.get("season")) - 1]);

//        Map<String, Object> teamCounselorInfo = boardService.getTeamCounselorInfo();
//        model.addAttribute("teamCounselorInfo", teamCounselorInfo);
        // QueueCumulative_Rate(인입, 응대, 응대율)
        Map<String, Object> queueCumulativeRate = boardService.getQueueCumulativeRate(param);
        // QueueRealTime(대기호, 평균대기시간, 서비스레벨)
        Map<String, Object> queueRealTime = boardService.getQueueRealTime(param);
        queueRealTime.putAll(queueCumulativeRate);
        model.addAttribute("queueRealTime", queueRealTime);
        System.out.println(queueRealTime);



//        Map<String, Object> teamCounselorInfo2 = boardService.getTeamCounselorInfo2();
//        model.addAttribute("teamCounselorInfo2", teamCounselorInfo2);
        // AgentStatus(로그인, 대기, 통화중, 후처리, 이석)
        Map<String, Object> agentStatus = boardService.getAgentStatus(param);
        model.addAttribute("agentStatus", agentStatus);



//        List<Map<String, Object>> individualCounselorState = boardService.getIndividualCounselorState();
//        model.addAttribute("individualCounselorState", individualCounselorState);
        // UserRealTime
        List<Map<String, Object>> userRealTime = boardService.getUserRealTime(param);
        model.addAttribute("userRealTime", userRealTime);


        model.addAttribute("teamCounselorFirstTitles", teamCounselorFirstTitles);
        model.addAttribute("teamCounselorFirstKeys", teamCounselorFirstKeys);

        model.addAttribute("teamCounselorSecondTitles", teamCounselorSecondTitles);
        model.addAttribute("teamCounselorSecondKeys", teamCounselorSecondKeys);

        model.addAttribute("stateColorMap", stateColorMap);
        model.addAttribute("stateIconMap", stateIconMap);

        return "/board/team-counselor";
    }

    @RequestMapping("test-data")
    public String testData(@RequestParam Map<String, Object> param, Model model) {

        System.out.println(param);
        Map<String, Object> testData = boardService.getAgentStatus(param);
        System.out.println(testData);

        return "/board/team-counselor";
    }


}
