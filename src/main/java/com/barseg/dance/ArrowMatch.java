package com.barseg.dance;

import org.sikuli.script.Match;

public class ArrowMatch {
    Match match;
    Arrow arrow;

    public ArrowMatch(Match match, Arrow arrow) {
        this.match = match;
        this.arrow = arrow;
    }

    public Match getMatch() {
        return match;
    }

    public Arrow getArrow() {
        return arrow;
    }
}
