package DesignPatterns.AbstractFactory;

import DesignPatterns.AbstractFactory.processor.TextDocumentProcessor;
import DesignPatterns.AbstractFactory.parser.TextDocumentParser;
import DesignPatterns.AbstractFactory.printer.TextDocumentPrinter;

public class TextDocumentFactory extends DocumentFactory {
    
    @Override
    public DocumentType supportsType() {
        return DocumentType.TEXT;
    }
    
    @Override
    public TextDocumentProcessor createProcessor(String documentName) {
        return new TextDocumentProcessor(documentName);
    }
    
    @Override
    public TextDocumentParser createParser(String documentName) {
        return new TextDocumentParser(documentName);
    }
    
    @Override
    public TextDocumentPrinter createPrinter(String documentName) {
        return new TextDocumentPrinter(documentName);
    }
}