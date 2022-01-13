package com.vdc.board.common.enums;

import lombok.Getter;

@Getter
public enum TotalCallConst {

    RECEIVED("총인입", "Received"),
    LOGIN("로그인", "LOGIN"),
    ANSWERED("응 대", "Answered"),
    IDLE("대기자", "IDLE"),
    ABANDONED("포 기", "Abandoned"),
    WAIT_CALLS("대기호", "WaitCalls"),
    ANSWER_RATE("응대율", "AnswerRate"),
    AVERAGE_ANSWER_WAIT_TIME("평균대기시간", "AverageAnsweredWaitTime");

    private final String title;
    private final String key;

    TotalCallConst(String title, String key) {
        this.title = title;
        this.key = key;
    }

}
