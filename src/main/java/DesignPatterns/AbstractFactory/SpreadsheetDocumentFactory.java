package DesignPatterns.AbstractFactory;

import DesignPatterns.AbstractFactory.processor.SpreadsheetDocumentProcessor;
import DesignPatterns.AbstractFactory.parser.SpreadsheetDocumentParser;
import DesignPatterns.AbstractFactory.printer.SpreadsheetDocumentPrinter;

public class SpreadsheetDocumentFactory extends DocumentFactory {
    
    @Override
    public DocumentType supportsType() {
        return DocumentType.SPREAD_SHEET;
    }
    
    @Override
    public SpreadsheetDocumentProcessor createProcessor(String documentName) {
        return new SpreadsheetDocumentProcessor(documentName);
    }
    
    @Override
    public SpreadsheetDocumentParser createParser(String documentName) {
        return new SpreadsheetDocumentParser(documentName);
    }
    
    @Override
    public SpreadsheetDocumentPrinter createPrinter(String documentName) {
        return new SpreadsheetDocumentPrinter(documentName);
    }
}