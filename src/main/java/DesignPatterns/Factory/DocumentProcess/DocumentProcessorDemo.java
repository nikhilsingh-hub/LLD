package DesignPatterns.Factory.DocumentProcess;

public class DocumentProcessorDemo {
    
    public static void main(String[] args) {
        System.out.println("=== Simple Factory Pattern Demo for Document Processing ===\n");
        
        // Create different document processors using the factory
        DocumentProcessor textProcessor = DocumentProcessorFactory.createDocumentProcessor(
            DocumentType.TEXT, "report.txt");
        DocumentProcessor spreadsheetProcessor = DocumentProcessorFactory.createDocumentProcessor(
            DocumentType.SPREAD_SHEET, "financial_data.xlsx");
        DocumentProcessor presentationProcessor = DocumentProcessorFactory.createDocumentProcessor(
            DocumentType.PRESENTATION, "quarterly_review.pptx");
        
        // Process documents
        System.out.println("Processing documents:");
        textProcessor.processDocument();
        spreadsheetProcessor.processDocument();
        presentationProcessor.processDocument();
        
        System.out.println("\n=== Document Information ===");
        System.out.println("Text Document: " + textProcessor.getDocumentName() + 
                          " (Type: " + textProcessor.supportsType() + ")");
        System.out.println("Spreadsheet Document: " + spreadsheetProcessor.getDocumentName() + 
                          " (Type: " + spreadsheetProcessor.supportsType() + ")");
        System.out.println("Presentation Document: " + presentationProcessor.getDocumentName() + 
                          " (Type: " + presentationProcessor.supportsType() + ")");
        
        // Demonstrate type-specific methods
        System.out.println("\n=== Type-specific Operations ===");
        if (spreadsheetProcessor instanceof SpreadsheetDocumentProcessor) {
            ((SpreadsheetDocumentProcessor) spreadsheetProcessor).performDataAnalysis();
        }
        
        if (presentationProcessor instanceof PresentationDocumentProcessor) {
            ((PresentationDocumentProcessor) presentationProcessor).addSlide();
        }
        
        System.out.println("\n=== Factory Pattern Benefits ===");
        System.out.println("✓ Single point of object creation");
        System.out.println("✓ Easy to add new document types");
        System.out.println("✓ Client code doesn't need to know concrete classes");
        System.out.println("✓ Consistent interface for all document processors");
    }
}