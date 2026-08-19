package com.lld.CreationDesignPatterns.Factory.Exercise;

public class DocumentFactory {

    public static Document createDocument(String type) {
        return switch (type.toLowerCase()) {
            case "pdf" -> new PDF();
            case "word" -> new Word();
            case "html" -> new HTML();
            default -> throw new IllegalArgumentException("Unknown document type: " + type);
        };
    }
}
