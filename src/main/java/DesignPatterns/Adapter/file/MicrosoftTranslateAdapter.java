package DesignPatterns.Adapter.file;

import DesignPatterns.Adapter.file.external.MicrosoftTranslateApi;

import java.util.List;

public class MicrosoftTranslateAdapter implements TranslationProviderAdapter {
    
    private final MicrosoftTranslateApi microsoftTranslateApi;
    
    public MicrosoftTranslateAdapter() {
        this.microsoftTranslateApi = new MicrosoftTranslateApi();
    }
    
    @Override
    public String translate(String text, String sourceLanguage, String targetLanguage) {
        return microsoftTranslateApi.translate(text, sourceLanguage, targetLanguage);
    }
    
    @Override
    public List<String> getSupportedLanguages() {
        return microsoftTranslateApi.getSupportedLanguages();
    }
}