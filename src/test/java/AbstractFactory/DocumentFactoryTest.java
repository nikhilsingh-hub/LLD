package AbstractFactory;

import DesignPatterns.AbstractFactory.*;
import DesignPatterns.AbstractFactory.parser.SpreadsheetDocumentParser;
import DesignPatterns.AbstractFactory.parser.TextDocumentParser;
import DesignPatterns.AbstractFactory.printer.SpreadsheetDocumentPrinter;
import DesignPatterns.AbstractFactory.printer.TextDocumentPrinter;
import DesignPatterns.AbstractFactory.processor.DocumentProcessor;
import DesignPatterns.AbstractFactory.parser.DocumentParser;
import DesignPatterns.AbstractFactory.printer.DocumentPrinter;
import DesignPatterns.AbstractFactory.processor.SpreadsheetDocumentProcessor;
import DesignPatterns.AbstractFactory.processor.TextDocumentProcessor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DocumentFactoryTest {

    @Test
    public void testTextDocumentFactoryCreation() {
        DocumentFactory factory = new TextDocumentFactory();
        
        assertNotNull(factory);
        assertTrue(factory instanceof TextDocumentFactory);
        assertEquals(DocumentType.TEXT, factory.supportsType());
    }

    @Test
    public void testSpreadsheetDocumentFactoryCreation() {
        DocumentFactory factory = new SpreadsheetDocumentFactory();
        
        assertNotNull(factory);
        assertTrue(factory instanceof SpreadsheetDocumentFactory);
        assertEquals(DocumentType.SPREAD_SHEET, factory.supportsType());
    }

    @Test
    public void testTextDocumentFactoryCreatesCompatibleObjects() {
        TextDocumentFactory factory = new TextDocumentFactory();
        String documentName = "test.txt";
        
        DocumentProcessor processor = factory.createProcessor(documentName);
        DocumentParser parser = factory.createParser(documentName);
        DocumentPrinter printer = factory.createPrinter(documentName);
        
        // Test that all objects are created successfully
        assertNotNull(processor);
        assertNotNull(parser);
        assertNotNull(printer);
        
        // Test that all objects support the same document type
        assertEquals(DocumentType.TEXT, processor.supportsType());
        assertEquals(DocumentType.TEXT, parser.supportsType());
        assertEquals(DocumentType.TEXT, printer.supportsType());
        
        // Test that all objects have the same document name
        assertEquals(documentName, processor.getDocumentName());
        assertEquals(documentName, parser.getDocumentName());
        assertEquals(documentName, printer.getDocumentName());
        
        // Test that objects are of correct concrete types
        assertTrue(processor instanceof TextDocumentProcessor);
        assertTrue(parser instanceof TextDocumentParser);
        assertTrue(printer instanceof TextDocumentPrinter);
    }

    @Test
    public void testSpreadsheetDocumentFactoryCreatesCompatibleObjects() {
        SpreadsheetDocumentFactory factory = new SpreadsheetDocumentFactory();
        String documentName = "data.xlsx";
        
        DocumentProcessor processor = factory.createProcessor(documentName);
        DocumentParser parser = factory.createParser(documentName);
        DocumentPrinter printer = factory.createPrinter(documentName);
        
        // Test that all objects are created successfully
        assertNotNull(processor);
        assertNotNull(parser);
        assertNotNull(printer);
        
        // Test that all objects support the same document type
        assertEquals(DocumentType.SPREAD_SHEET, processor.supportsType());
        assertEquals(DocumentType.SPREAD_SHEET, parser.supportsType());
        assertEquals(DocumentType.SPREAD_SHEET, printer.supportsType());
        
        // Test that all objects have the same document name
        assertEquals(documentName, processor.getDocumentName());
        assertEquals(documentName, parser.getDocumentName());
        assertEquals(documentName, printer.getDocumentName());
        
        // Test that objects are of correct concrete types
        assertTrue(processor instanceof SpreadsheetDocumentProcessor);
        assertTrue(parser instanceof SpreadsheetDocumentParser);
        assertTrue(printer instanceof SpreadsheetDocumentPrinter);
    }

    @Test
    public void testDocumentFactoryProvider() {
        DocumentFactory textFactory = DocumentFactoryProvider.getFactory(DocumentType.TEXT);
        DocumentFactory spreadsheetFactory = DocumentFactoryProvider.getFactory(DocumentType.SPREAD_SHEET);
        
        assertNotNull(textFactory);
        assertNotNull(spreadsheetFactory);
        
        assertTrue(textFactory instanceof TextDocumentFactory);
        assertTrue(spreadsheetFactory instanceof SpreadsheetDocumentFactory);
        
        assertEquals(DocumentType.TEXT, textFactory.supportsType());
        assertEquals(DocumentType.SPREAD_SHEET, spreadsheetFactory.supportsType());
    }

    @Test
    public void testDocumentFactoryProviderThrowsExceptionForUnsupportedType() {
        assertThrows(IllegalArgumentException.class, () -> {
            DocumentFactoryProvider.getFactory(DocumentType.PRESENTATION);
        });
    }

    @Test
    public void testAbstractFactoryPatternCompatibility() {
        // Test that factories create compatible families of objects
        DocumentFactory textFactory = new TextDocumentFactory();
        DocumentFactory spreadsheetFactory = new SpreadsheetDocumentFactory();
        
        // Create objects from text factory
        DocumentProcessor textProcessor = textFactory.createProcessor("doc.txt");
        DocumentParser textParser = textFactory.createParser("doc.txt");
        DocumentPrinter textPrinter = textFactory.createPrinter("doc.txt");
        
        // Create objects from spreadsheet factory
        DocumentProcessor sheetProcessor = spreadsheetFactory.createProcessor("sheet.xlsx");
        DocumentParser sheetParser = spreadsheetFactory.createParser("sheet.xlsx");
        DocumentPrinter sheetPrinter = spreadsheetFactory.createPrinter("sheet.xlsx");
        
        // Verify that objects from the same factory are compatible
        assertEquals(textProcessor.supportsType(), textParser.supportsType());
        assertEquals(textProcessor.supportsType(), textPrinter.supportsType());
        
        assertEquals(sheetProcessor.supportsType(), sheetParser.supportsType());
        assertEquals(sheetProcessor.supportsType(), sheetPrinter.supportsType());
        
        // Verify that objects from different factories are not compatible
        assertNotEquals(textProcessor.supportsType(), sheetProcessor.supportsType());
    }

    @Test
    public void testDocumentProcessorInheritance() {
        DocumentFactory textFactory = new TextDocumentFactory();
        DocumentFactory spreadsheetFactory = new SpreadsheetDocumentFactory();
        
        DocumentProcessor textProcessor = textFactory.createProcessor("test.txt");
        DocumentProcessor sheetProcessor = spreadsheetFactory.createProcessor("test.xlsx");
        
        // Test that all processors extend DocumentProcessor
        assertTrue(textProcessor instanceof DocumentProcessor);
        assertTrue(sheetProcessor instanceof DocumentProcessor);
        
        // Test common methods
        assertNotNull(textProcessor.getDocumentName());
        assertNotNull(sheetProcessor.getDocumentName());
        
        assertNotNull(textProcessor.supportsType());
        assertNotNull(sheetProcessor.supportsType());
        
        // Test that processDocument method works
        assertDoesNotThrow(() -> textProcessor.processDocument());
        assertDoesNotThrow(() -> sheetProcessor.processDocument());
    }
}