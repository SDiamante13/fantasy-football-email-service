package tech.pathtoprogramming.fantasyfootball.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Position {
    QB("qb"),
    RB("rb"),
    WR("wr"),
    TE("te");

    private final String value;

    public String value() {
        return value;
    }
}
