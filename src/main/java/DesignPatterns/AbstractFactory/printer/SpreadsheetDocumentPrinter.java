package DesignPatterns.AbstractFactory.printer;

import DesignPatterns.AbstractFactory.DocumentType;

public class SpreadsheetDocumentPrinter extends DocumentPrinter {

    public SpreadsheetDocumentPrinter(String documentName) {
        super(documentName);
    }

    @Override
    public DocumentType supportsType() {
        return DocumentType.SPREAD_SHEET;
    }

    @Override
    public void printDocument() {
        // Implement spreadsheet document printing logic
        System.out.println("Printing a spreadsheet document: " + getDocumentName());
        // Additional logic for spreadsheet document printing
    }

    public void printCharts() {
        System.out.println("Printing charts from the spreadsheet.");
    }
}