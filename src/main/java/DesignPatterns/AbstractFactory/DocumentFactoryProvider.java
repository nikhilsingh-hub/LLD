package DesignPatterns.AbstractFactory;

public class DocumentFactoryProvider {
    
    public static DocumentFactory getFactory(DocumentType documentType) {
        switch (documentType) {
            case TEXT:
                return new TextDocumentFactory();
            case SPREAD_SHEET:
                return new SpreadsheetDocumentFactory();
            default:
                throw new IllegalArgumentException("Unsupported document type: " + documentType);
        }
    }
}