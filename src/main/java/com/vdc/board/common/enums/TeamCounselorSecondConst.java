package com.vdc.board.common.enums;

import lombok.Getter;

@Getter
public enum TeamCounselorSecondConst {

    LOGIN("로 그 인"),
    IDLE("대 기"),
    BUSY("통 화 중"),
    WORK("후 처 리"),
    AWAY("이 석");

    private final String title;

    TeamCounselorSecondConst(String title) {
        this.title = title;
    }
}
