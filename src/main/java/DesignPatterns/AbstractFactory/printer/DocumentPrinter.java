package DesignPatterns.AbstractFactory.printer;

import DesignPatterns.AbstractFactory.DocumentType;

public abstract class DocumentPrinter {
    protected String documentName;

    public DocumentPrinter(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentName() {
        return documentName;
    }

    public abstract DocumentType supportsType();

    public abstract void printDocument();
}