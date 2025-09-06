package DesignPatterns.AbstractFactory.processor;

import DesignPatterns.AbstractFactory.DocumentType;

public abstract class DocumentProcessor {
    protected String documentName;

    public DocumentProcessor(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentName() {
        return documentName;
    }

    public abstract DocumentType supportsType();

    public abstract void processDocument();
}