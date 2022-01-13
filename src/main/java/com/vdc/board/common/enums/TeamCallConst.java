package com.vdc.board.common.enums;

import lombok.Getter;

@Getter
public enum TeamCallConst {

    RECEIVED("인 입", "Received"),
    WAIT_CALLS("대 기 호", "WaitCalls"),
    ANSWERED("응 대", "Answered"),
    AVERAGE_ANSWERED_WAIT_TIME("평균대기시간", "AverageAnsweredWaitTime"),
    ANSWER_RATE("응 대 율", "AnswerRate"),
    SERVICE_LEVEL("서비스레벨", "ServiceLevel");

    private final String title;
    private final String key;

    TeamCallConst(String title, String key) {
        this.title = title;
        this.key = key;
    }
}
