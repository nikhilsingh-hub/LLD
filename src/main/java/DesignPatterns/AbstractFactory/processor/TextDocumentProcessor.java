package DesignPatterns.AbstractFactory.processor;

import DesignPatterns.AbstractFactory.DocumentType;

public class TextDocumentProcessor extends DocumentProcessor {

    public TextDocumentProcessor(String documentName) {
        super(documentName);
    }

    @Override
    public DocumentType supportsType() {
        return DocumentType.TEXT;
    }

    @Override
    public void processDocument() {
        // Implement text document processing logic
        System.out.println("Processing a text document: " + getDocumentName());
        // Additional logic for text document processing
    }
}