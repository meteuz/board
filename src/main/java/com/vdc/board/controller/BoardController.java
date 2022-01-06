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

    private String[] totalCallTitles = new String[]{"총인입", "로그인", "응 대", "대기자", "포 기", "대기호", "응대율", "평균대기시간"};
    private String[] totalCallKeys = new String[]{"total_input", "login", "response", "waiter", "give_up", "wait_call", "response_rate", "average_wait_time"};

    private String[] jobCallTitles = new String[]{"구 분", "총인입", "응 대", "포 기", "응대율", "서비스레벨"};
    private String[] jobCallKeys = new String[]{"title", "total_input", "response", "give_up", "response_rate", "service_level"};

    private String[] teamCallTitles = new String[]{"인 입", "대 기 호", "응 대", "평균대기시간", "응 대 율", "서비스레벨"};
    private String[] teamCallKeys = new String[]{"input", "wait_call", "response", "average_wait_time", "response_rate", "service"};

    private String[] teamCounselorFirstTitles = new String[]{"인 입", "응 대", "응 대 율", "대 기 호", "평균대기시간", "서비스레벨"};
    private String[] teamCounselorFirstKeys = new String[]{"input", "response", "response_rate", "wait_call", "average_wait_time", "service"};
    private String[] teamCounselorSecondTitles = new String[]{"로 그 인", "대 기", "통 화 중", "후 처 리", "이 석"};
    private String[] teamCounselorSecondKeys = new String[]{"login", "wait", "calling", "after_handle", "other"};
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

    @RequestMapping("current-date")
    public String currentDate(Model model) {
        model.addAttribute("currentDate", Utils.getCurrentDate());
        model.addAttribute("currentTime", Utils.getCurrentTime());
        return "/board/current-date";
    }

    @RequestMapping("total-call")
    public String totalCall(@RequestParam(value = "dept", defaultValue = "17") String dept, Model model) {

        Map<String, Object> totalCallInfo = boardService.getTotalCallInfo();
        model.addAttribute("totalCallInfo", totalCallInfo);

        model.addAttribute("totalCallTitles", totalCallTitles);
        model.addAttribute("totalCallKeys", totalCallKeys);

        return "/board/total-call";
    }

    @RequestMapping("job-call")
    public String jobCall(Model model) {

//        Map<String, Map<String, Object>> jobCallInfo = boardService.getJobCallInfo();
//        model.addAttribute("jobCallInfo", jobCallInfo);

        List<Map<String, Object>> jobCallInfoList = boardService.getJobCallInfoList();
        model.addAttribute("jobCallInfoList", jobCallInfoList);

        model.addAttribute("jobCallTitles", jobCallTitles);
        model.addAttribute("jobCallKeys", jobCallKeys);

        return "/board/job-call";
    }

    @RequestMapping("team-call")
    public String teamCall(Model model) {

        Map<String, Object> teamCallInfo = boardService.getTeamCallInfo();
        model.addAttribute("teamCallInfo", teamCallInfo);

        List<Map<String, Object>> individualPerformance = boardService.getIndividualPerformance();
        model.addAttribute("individualPerformance", individualPerformance);

        model.addAttribute("teamCallTitles", teamCallTitles);
        model.addAttribute("teamCallKeys", teamCallKeys);

        return "/board/team-call";
    }

    @RequestMapping("team-counselor")
    public String teamCounselor(Model model) {

        Map<String, Object> teamCounselorInfo = boardService.getTeamCounselorInfo();
        model.addAttribute("teamCounselorInfo", teamCounselorInfo);

        Map<String, Object> teamCounselorInfo2 = boardService.getTeamCounselorInfo2();
        model.addAttribute("teamCounselorInfo2", teamCounselorInfo2);

        List<Map<String, Object>> individualCounselorState = boardService.getIndividualCounselorState();
        model.addAttribute("individualCounselorState", individualCounselorState);

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
        Map<String, Object> testData = boardService.getTestData(param);
        System.out.println(testData);

        return "/board/team-counselor";
    }


}
