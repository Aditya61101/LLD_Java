package com.lld.BehaviouralDesignPatterns.StrategyPattern.Exercise;

public class MarkdownFormatter implements TextFormatter {
    public String format(String content) {
        return "**" + content + "**";
    }
}
