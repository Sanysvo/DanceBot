package com.barseg.dance;

import org.sikuli.script.Key;
import org.sikuli.script.Pattern;

public class Arrow {
    Pattern image;
    String key;

    public Arrow(Pattern image, String key) {
        this.image = image;
        this.key = key;
    }

    public Pattern getImage() {
        return image;
    }

    public String  getKey() {
        return key;
    }
}
