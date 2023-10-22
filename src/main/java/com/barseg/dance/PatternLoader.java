package com.barseg.dance;

import org.sikuli.script.Pattern;

public class PatternLoader {
    public static final Pattern ARROW_UP = new Pattern(PatternLoader.class.getClassLoader().getResource("dance/arrowUp.png"));
    public static final Pattern ARROW_DOWN = new Pattern(PatternLoader.class.getClassLoader().getResource("dance/arrowDown.png"));
    public static final Pattern ARROW_LEFT = new Pattern(PatternLoader.class.getClassLoader().getResource("dance/arrowLeft.png"));
    public static final Pattern ARROW_RIGHT = new Pattern(PatternLoader.class.getClassLoader().getResource("dance/arrowRight.png"));
    public static final Pattern ARROW_PRESSED = new Pattern(PatternLoader.class.getClassLoader().getResource("dance/arrowPressed.png"));
    public static final Pattern SPACE = new Pattern(PatternLoader.class.getClassLoader().getResource("dance/space.png"));
    public static final Pattern BALL = new Pattern(PatternLoader.class.getClassLoader().getResource("dance/ball.png"));
}
