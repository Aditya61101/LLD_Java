package com.lld.BehaviouralDesignPatterns.StrategyPattern.Exercise;

public class PlainTextFormatter implements TextFormatter {
    public String format(String content) {
        return content;
    }
}
