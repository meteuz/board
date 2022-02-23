package com.vdc.board.common.enums;

import lombok.Getter;

@Getter
public enum Title {

    TOTAL_CALL("전체 콜 운영 현황"),
    JOB_CALL("업무별 콜 응대 현황"),
    TEAM_CALL("콜 상태 현황"),
    TEAM_COUNSELOR("상담원 상태 현황");

    private final String titleStr;

    Title(String titleStr) {
        this.titleStr = titleStr;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
