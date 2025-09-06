package DesignPatterns.AbstractFactory;

import DesignPatterns.AbstractFactory.processor.DocumentProcessor;
import DesignPatterns.AbstractFactory.parser.DocumentParser;
import DesignPatterns.AbstractFactory.printer.DocumentPrinter;
import DesignPatterns.Factory.DocumentProcess.DocumentProcessorFactory;

public class FactoryPatternComparison {
    
    public static void main(String[] args) {
        System.out.println("=== Factory Pattern Comparison ===\n");
        
        // SIMPLE FACTORY PATTERN
        System.out.println("1. SIMPLE FACTORY PATTERN:");
        System.out.println("   - Single factory class with static methods");
        System.out.println("   - Creates ONE type of object (only processors)");
        System.out.println("   - Uses switch statement to determine object type");
        System.out.println("   - Client directly calls factory method");
        
        // Example of Simple Factory
        DocumentProcessor textProcessor = DocumentProcessorFactory.createDocumentProcessor(
            DesignPatterns.Factory.DocumentProcess.DocumentType.TEXT, "simple.txt");
        DocumentProcessor sheetProcessor = DocumentProcessorFactory.createDocumentProcessor(
            DesignPatterns.Factory.DocumentProcess.DocumentType.SPREAD_SHEET, "simple.xlsx");
        
        System.out.println("\n   Simple Factory Usage:");
        textProcessor.processDocument();
        sheetProcessor.processDocument();
        
        System.out.println("\n   Simple Factory Limitations:");
        System.out.println("   ❌ Only creates processors (not parsers/printers)");
        System.out.println("   ❌ No family compatibility guarantee");
        System.out.println("   ❌ Hard to extend with new object types");
        System.out.println("   ❌ Violates Open/Closed Principle");
        
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        // ABSTRACT FACTORY PATTERN
        System.out.println("2. ABSTRACT FACTORY PATTERN:");
        System.out.println("   - Abstract factory interface/class");
        System.out.println("   - Multiple concrete factories (one per family)");
        System.out.println("   - Creates FAMILIES of related objects");
        System.out.println("   - Each factory ensures compatibility within family");
        
        // Example of Abstract Factory
        DocumentFactory textFactory = new TextDocumentFactory();
        DocumentFactory sheetFactory = new SpreadsheetDocumentFactory();
        
        System.out.println("\n   Abstract Factory Usage:");
        System.out.println("   Text Factory creates compatible family:");
        DocumentProcessor textProc = textFactory.createProcessor("abstract.txt");
        DocumentParser textParser = textFactory.createParser("abstract.txt");
        DocumentPrinter textPrinter = textFactory.createPrinter("abstract.txt");
        
        textProc.processDocument();
        textParser.parseDocument();
        textPrinter.printDocument();
        
        System.out.println("\n   Spreadsheet Factory creates compatible family:");
        DocumentProcessor sheetProc = sheetFactory.createProcessor("abstract.xlsx");
        DocumentParser sheetParser = sheetFactory.createParser("abstract.xlsx");
        DocumentPrinter sheetPrinter = sheetFactory.createPrinter("abstract.xlsx");
        
        sheetProc.processDocument();
        sheetParser.parseDocument();
        sheetPrinter.printDocument();
        
        System.out.println("\n   Abstract Factory Benefits:");
        System.out.println("   ✅ Creates complete families of objects");
        System.out.println("   ✅ Guarantees compatibility within families");
        System.out.println("   ✅ Easy to extend with new families");
        System.out.println("   ✅ Follows Open/Closed Principle");
        System.out.println("   ✅ Better separation of concerns");
        
        System.out.println("\n" + "=".repeat(60) + "\n");
        
        // KEY DIFFERENCES SUMMARY
        System.out.println("3. KEY DIFFERENCES SUMMARY:");
        System.out.println();
        
        System.out.println("   📊 OBJECT CREATION:");
        System.out.println("   Simple Factory:  Creates ONE type of object");
        System.out.println("   Abstract Factory: Creates FAMILIES of related objects");
        System.out.println();
        
        System.out.println("   🏗️ ARCHITECTURE:");
        System.out.println("   Simple Factory:  Single concrete factory class");
        System.out.println("   Abstract Factory: Abstract factory + multiple concrete factories");
        System.out.println();
        
        System.out.println("   🔧 EXTENSIBILITY:");
        System.out.println("   Simple Factory:  Modify existing factory (violates OCP)");
        System.out.println("   Abstract Factory: Add new concrete factories (follows OCP)");
        System.out.println();
        
        System.out.println("   🤝 COMPATIBILITY:");
        System.out.println("   Simple Factory:  No compatibility guarantee");
        System.out.println("   Abstract Factory: Guaranteed compatibility within families");
        System.out.println();
        
        System.out.println("   📝 USAGE PATTERN:");
        System.out.println("   Simple Factory:  Factory.createObject(type, params)");
        System.out.println("   Abstract Factory: factory.createObject(params)");
        System.out.println();
        
        System.out.println("   🎯 WHEN TO USE:");
        System.out.println("   Simple Factory:  When you need ONE type of object");
        System.out.println("   Abstract Factory: When you need FAMILIES of related objects");
        
        System.out.println("\n=== Comparison Complete ===");
    }
}