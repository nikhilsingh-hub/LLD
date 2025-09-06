package DesignPatterns.Adapter.file.external;

import java.util.List;

import static DesignPatterns.Adapter.file.external.ApiUtils.logYandexGetSupportedLanguages;
import static DesignPatterns.Adapter.file.external.ApiUtils.logYandexTranslate;

public class YandexTranslateApi {
    
    public String translateText(String text, String sourceLanguage, String targetLanguage) {
        // Implementation for translating text using Yandex Translate API
        logYandexTranslate();
        return "Translated text";
    }
    
    public List<String> getAvailableLanguages() {
        // Implementation for fetching supported languages from Yandex Translate
        logYandexGetSupportedLanguages();
        return List.of("hindi", "marathi", "kannada");
    }
}