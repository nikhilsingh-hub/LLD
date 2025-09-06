package DesignPatterns.AbstractFactory.parser;

import DesignPatterns.AbstractFactory.DocumentType;

public class SpreadsheetDocumentParser extends DocumentParser {

    public SpreadsheetDocumentParser(String documentName) {
        super(documentName);
    }

    @Override
    public DocumentType supportsType() {
        return DocumentType.SPREAD_SHEET;
    }

    @Override
    public void parseDocument() {
        // Implement spreadsheet document parsing logic
        System.out.println("Parsing a spreadsheet document: " + getDocumentName());
        // Additional logic for spreadsheet document parsing
    }

    public void extractData() {
        System.out.println("Extracting data from the spreadsheet.");
    }
}