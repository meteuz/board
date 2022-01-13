package com.vdc.board.common.enums;

import lombok.Getter;

@Getter
public enum Season {

    SPRING("lightpink"),
    SUMMER("royalblue"),
    FALL("goldenrod"),
    WINTER("darkgray");

    private final String color;

    Season(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
