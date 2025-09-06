package DesignPatterns.AbstractFactory.parser;

import DesignPatterns.AbstractFactory.DocumentType;

public class TextDocumentParser extends DocumentParser {

    public TextDocumentParser(String documentName) {
        super(documentName);
    }

    @Override
    public DocumentType supportsType() {
        return DocumentType.TEXT;
    }

    @Override
    public void parseDocument() {
        // Implement text document parsing logic
        System.out.println("Parsing a text document: " + getDocumentName());
        // Additional logic for text document parsing
    }
}