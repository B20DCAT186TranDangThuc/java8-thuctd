package org.example.lang.impl;

import org.example.lang.Language;

public class English implements Language {
    @Override
    public String getGreeting() {
        return "Hello";
    }

    @Override
    public String getBye() {
        return "Bye Bye";
    }
}
