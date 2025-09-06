package DesignPatterns.Factory.DocumentProcess;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DocumentProcessorTest {

    @Test
    public void testTextDocumentProcessorCreation() {
        DocumentProcessor processor = DocumentProcessorFactory.createDocumentProcessor(DocumentType.TEXT, "test.txt");
        
        assertNotNull(processor);
        assertTrue(processor instanceof TextDocumentProcessor);
        assertEquals("test.txt", processor.getDocumentName());
        assertEquals(DocumentType.TEXT, processor.supportsType());
    }

    @Test
    public void testSpreadsheetDocumentProcessorCreation() {
        DocumentProcessor processor = DocumentProcessorFactory.createDocumentProcessor(DocumentType.SPREAD_SHEET, "data.xlsx");
        
        assertNotNull(processor);
        assertTrue(processor instanceof SpreadsheetDocumentProcessor);
        assertEquals("data.xlsx", processor.getDocumentName());
        assertEquals(DocumentType.SPREAD_SHEET, processor.supportsType());
    }

    @Test
    public void testPresentationDocumentProcessorCreation() {
        DocumentProcessor processor = DocumentProcessorFactory.createDocumentProcessor(DocumentType.PRESENTATION, "slides.pptx");
        
        assertNotNull(processor);
        assertTrue(processor instanceof PresentationDocumentProcessor);
        assertEquals("slides.pptx", processor.getDocumentName());
        assertEquals(DocumentType.PRESENTATION, processor.supportsType());
    }

    @Test
    public void testDocumentProcessorInheritance() {
        DocumentProcessor textProcessor = DocumentProcessorFactory.createDocumentProcessor(DocumentType.TEXT, "doc.txt");
        DocumentProcessor spreadsheetProcessor = DocumentProcessorFactory.createDocumentProcessor(DocumentType.SPREAD_SHEET, "sheet.xlsx");
        DocumentProcessor presentationProcessor = DocumentProcessorFactory.createDocumentProcessor(DocumentType.PRESENTATION, "presentation.pptx");
        
        // Test that all processors extend DocumentProcessor
        assertTrue(textProcessor instanceof DocumentProcessor);
        assertTrue(spreadsheetProcessor instanceof DocumentProcessor);
        assertTrue(presentationProcessor instanceof DocumentProcessor);
        
        // Test common methods
        assertNotNull(textProcessor.getDocumentName());
        assertNotNull(spreadsheetProcessor.getDocumentName());
        assertNotNull(presentationProcessor.getDocumentName());
        
        assertNotNull(textProcessor.supportsType());
        assertNotNull(spreadsheetProcessor.supportsType());
        assertNotNull(presentationProcessor.supportsType());
    }

    @Test
    public void testProcessDocumentMethod() {
        DocumentProcessor textProcessor = DocumentProcessorFactory.createDocumentProcessor(DocumentType.TEXT, "test.txt");
        DocumentProcessor spreadsheetProcessor = DocumentProcessorFactory.createDocumentProcessor(DocumentType.SPREAD_SHEET, "data.xlsx");
        DocumentProcessor presentationProcessor = DocumentProcessorFactory.createDocumentProcessor(DocumentType.PRESENTATION, "slides.pptx");
        
        // These should not throw exceptions
        assertDoesNotThrow(() -> textProcessor.processDocument());
        assertDoesNotThrow(() -> spreadsheetProcessor.processDocument());
        assertDoesNotThrow(() -> presentationProcessor.processDocument());
    }
}