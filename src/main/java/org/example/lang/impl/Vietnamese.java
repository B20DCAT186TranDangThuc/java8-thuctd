package org.example.lang.impl;

import org.example.lang.Language;

public class Vietnamese implements Language {
    @Override
    public String getGreeting() {
        return "Xin Chao";
    }

    @Override
    public String getBye() {
        return "Tam Biet";
    }
}
