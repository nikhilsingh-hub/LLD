package DesignPatterns.AbstractFactory.printer;

import DesignPatterns.AbstractFactory.DocumentType;

public class TextDocumentPrinter extends DocumentPrinter {

    public TextDocumentPrinter(String documentName) {
        super(documentName);
    }

    @Override
    public DocumentType supportsType() {
        return DocumentType.TEXT;
    }

    @Override
    public void printDocument() {
        // Implement text document printing logic
        System.out.println("Printing a text document: " + getDocumentName());
        // Additional logic for text document printing
    }
}