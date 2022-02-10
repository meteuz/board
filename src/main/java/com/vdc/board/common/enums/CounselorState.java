package com.vdc.board.common.enums;

import lombok.Getter;

@Getter
public enum CounselorState {
    // 상담원상태
    //-이석(초기, 식사,휴식,교육,업무)
    //-후처리
    //-통화중
    //-로그오프
    //-기타
    // IDLE, WORK, CONNECTED, AWAY,
    INIT("init", "icon_init", "fa-keyboard"),                           // 이석(초기)
    MEAL("meal", "icon_meal", "fa-utensils"),                           // 이석(식사)
    REST("rest", "icon_rest", "fa-mug-hot"),                            // 이석(휴식)
    EDUCATION("education", "icon_education", "fa-chalkboard-teacher"),  // 이석(교육)
    WORK("work", "icon_work", "fa-phone-rotary"),                       // 이석(업무)
    CALLING("calling", "icon_calling", "fa-phone-volume"),              // 통화중
    OFF("off", "icon_off", "fa-user-slash"),                            // 로그오프
    HANDLING("handling", "icon_handling", "fa-pencil-alt"),             // 후처리
    ETC("etc", "icon_etc", "fa-grip-horizontal");                       // 기타

    private final String state;
    private final String color;
    private final String icon;

    CounselorState(String state, String color, String icon) {
        this.state = state;
        this.color = color;
        this.icon = icon;
    }
}
