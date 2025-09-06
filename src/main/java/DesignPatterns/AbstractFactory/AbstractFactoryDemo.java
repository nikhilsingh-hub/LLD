package DesignPatterns.AbstractFactory;

import DesignPatterns.AbstractFactory.parser.SpreadsheetDocumentParser;
import DesignPatterns.AbstractFactory.printer.SpreadsheetDocumentPrinter;
import DesignPatterns.AbstractFactory.processor.DocumentProcessor;
import DesignPatterns.AbstractFactory.parser.DocumentParser;
import DesignPatterns.AbstractFactory.printer.DocumentPrinter;
import DesignPatterns.AbstractFactory.processor.SpreadsheetDocumentProcessor;

public class AbstractFactoryDemo {
    
    public static void main(String[] args) {
        System.out.println("=== Abstract Factory Pattern Demo ===\n");
        
        // Test using DocumentFactoryProvider
        System.out.println("1. Testing DocumentFactoryProvider:");
        DocumentFactory textFactory = DocumentFactoryProvider.getFactory(DocumentType.TEXT);
        DocumentFactory spreadsheetFactory = DocumentFactoryProvider.getFactory(DocumentType.SPREAD_SHEET);
        
        System.out.println("Text Factory supports: " + textFactory.supportsType());
        System.out.println("Spreadsheet Factory supports: " + spreadsheetFactory.supportsType());
        
        // Test TextDocumentFactory
        System.out.println("\n2. Testing TextDocumentFactory:");
        DocumentProcessor textProc = textFactory.createProcessor("document.txt");
        DocumentParser textParser = textFactory.createParser("document.txt");
        DocumentPrinter textPrinter = textFactory.createPrinter("document.txt");
        
        textProc.processDocument();
        textParser.parseDocument();
        textPrinter.printDocument();
        
        // Test SpreadsheetDocumentFactory
        System.out.println("\n3. Testing SpreadsheetDocumentFactory:");
        DocumentProcessor sheetProc = spreadsheetFactory.createProcessor("budget.xlsx");
        DocumentParser sheetParser = spreadsheetFactory.createParser("budget.xlsx");
        DocumentPrinter sheetPrinter = spreadsheetFactory.createPrinter("budget.xlsx");
        
        sheetProc.processDocument();
        sheetParser.parseDocument();
        sheetPrinter.printDocument();
        
        // Test type-specific methods
        System.out.println("\n4. Testing Type-specific Methods:");
        if (sheetProc instanceof SpreadsheetDocumentProcessor) {
            ((SpreadsheetDocumentProcessor) sheetProc).performDataAnalysis();
        }
        
        if (sheetParser instanceof SpreadsheetDocumentParser) {
            ((SpreadsheetDocumentParser) sheetParser).extractData();
        }
        
        if (sheetPrinter instanceof SpreadsheetDocumentPrinter) {
            ((SpreadsheetDocumentPrinter) sheetPrinter).printCharts();
        }
        
        // Test Abstract Factory Pattern Benefits
        System.out.println("\n5. Testing Abstract Factory Pattern Benefits:");
        System.out.println("✓ Compatible families: All objects from same factory work together");
        System.out.println("✓ Type safety: Each factory creates objects of the same document type");
        System.out.println("✓ Extensibility: Easy to add new document types");
        System.out.println("✓ Encapsulation: Client doesn't need to know concrete classes");
        
        System.out.println("\n=== Abstract Factory Demo Complete ===");
    }
}