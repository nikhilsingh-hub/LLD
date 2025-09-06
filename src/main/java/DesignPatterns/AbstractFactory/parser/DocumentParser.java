package DesignPatterns.AbstractFactory.parser;

import DesignPatterns.AbstractFactory.DocumentType;

public abstract class DocumentParser {
    protected String documentName;

    public DocumentParser(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentName() {
        return documentName;
    }

    public abstract DocumentType supportsType();

    public abstract void parseDocument();
}