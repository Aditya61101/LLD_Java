package com.lld.CreationDesignPatterns.Factory.Exercise;

public class DocumentService {
    public static void main(String[] args) {
        Document pdfDocument = DocumentFactory.createDocument("pdf");
        pdfDocument.displayType();

        Document wordDocument = DocumentFactory.createDocument("word");
        wordDocument.displayType();

        Document htmlDocument = DocumentFactory.createDocument("html");
        htmlDocument.displayType();
    }
}
