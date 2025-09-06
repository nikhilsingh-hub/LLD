package DesignPatterns.AbstractFactory;

import DesignPatterns.AbstractFactory.processor.DocumentProcessor;
import DesignPatterns.AbstractFactory.parser.DocumentParser;
import DesignPatterns.AbstractFactory.printer.DocumentPrinter;

public abstract class DocumentFactory {
    
    public abstract DocumentType supportsType();
    
    public abstract DocumentProcessor createProcessor(String documentName);
    
    public abstract DocumentParser createParser(String documentName);
    
    public abstract DocumentPrinter createPrinter(String documentName);
}