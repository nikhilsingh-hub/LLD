package DesignPatterns.Factory.DocumentProcess;

public class DocumentProcessorFactory {
    
    public static DocumentProcessor createDocumentProcessor(DocumentType documentType, String documentName) {
        switch (documentType) {
            case TEXT:
                return new TextDocumentProcessor(documentName);
            case SPREAD_SHEET:
                return new SpreadsheetDocumentProcessor(documentName);
            case PRESENTATION:
                return new PresentationDocumentProcessor(documentName);
            default:
                throw new IllegalArgumentException("Unsupported document type: " + documentType);
        }
    }
}
