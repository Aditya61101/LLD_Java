package com.lld.BehaviouralDesignPatterns.StrategyPattern.Exercise;

import java.util.Scanner;

public class FlexiText {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Document document = new Document();

        String userInput = sc.nextLine();
        document.setContent(userInput);

        TextFormatter htmlFormatter = new HTMLFormatter();
        document.setFormatter(htmlFormatter);
        document.display();

        TextFormatter plainTextFormatter = new PlainTextFormatter();
        document.setFormatter(plainTextFormatter);
        document.display();

        TextFormatter markdownFormatter = new MarkdownFormatter();
        document.setFormatter(markdownFormatter);
        document.display();
    }
}
