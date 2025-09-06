package DesignPatterns.Adapter.file;

import DesignPatterns.Adapter.file.external.GoogleTranslateApi;
import DesignPatterns.Adapter.file.external.GoogleTranslationRequest;

import java.util.List;

public class GoogleTranslateAdapter implements TranslationProviderAdapter {
    
    private final GoogleTranslateApi googleTranslateApi;
    
    public GoogleTranslateAdapter() {
        this.googleTranslateApi = new GoogleTranslateApi();
    }
    
    @Override
    public String translate(String text, String sourceLanguage, String targetLanguage) {
        // Create Google-specific request object with confidence score
        GoogleTranslationRequest request = new GoogleTranslationRequest(text, sourceLanguage, targetLanguage, 0.8);
        return googleTranslateApi.convert(request);
    }
    
    @Override
    public List<String> getSupportedLanguages() {
        return googleTranslateApi.getLanguages();
    }
}