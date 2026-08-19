package com.lld.BehaviouralDesignPatterns.StrategyPattern.Exercise;

public class Document {
    private String content;
    private TextFormatter formatter;

    public void setContent(String content) {
        this.content = content;
    }

    public void setFormatter(TextFormatter formatter) {
        this.formatter = formatter;
    }

    public void display() {
        String formattedText = this.formatter.format(this.content);
        System.out.println(formattedText);
    }
}
