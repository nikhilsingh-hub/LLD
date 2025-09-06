package DesignPatterns.Adapter.file;

import DesignPatterns.Adapter.file.external.YandexTranslateApi;

import java.util.List;

public class YandexTranslateAdapter implements TranslationProviderAdapter {
    
    private final YandexTranslateApi yandexTranslateApi;
    
    public YandexTranslateAdapter() {
        this.yandexTranslateApi = new YandexTranslateApi();
    }
    
    @Override
    public String translate(String text, String sourceLanguage, String targetLanguage) {
        return yandexTranslateApi.translateText(text, sourceLanguage, targetLanguage);
    }
    
    @Override
    public List<String> getSupportedLanguages() {
        return yandexTranslateApi.getAvailableLanguages();
    }
}