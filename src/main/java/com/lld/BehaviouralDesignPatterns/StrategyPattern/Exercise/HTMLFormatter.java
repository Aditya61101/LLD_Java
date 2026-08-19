package com.lld.BehaviouralDesignPatterns.StrategyPattern.Exercise;

public class HTMLFormatter implements TextFormatter {
    public String format(String content) {
        return "<html><body>" + content + "</body></html>";
    }
}
