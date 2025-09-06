package DesignPatterns.Adapter;

import DesignPatterns.Adapter.file.*;

import java.util.List;

public class TranslationManager {

    private final GoogleTranslateAdapter googleAdapter = new GoogleTranslateAdapter();
    private final MicrosoftTranslateAdapter microsoftAdapter = new MicrosoftTranslateAdapter();
    private final YandexTranslateAdapter yandexAdapter = new YandexTranslateAdapter();

    public String translate(String text, String sourceLanguage, String targetLanguage, String provider) {
        TranslationProviderAdapter adapter = getAdapter(provider);
        return adapter.translate(text, sourceLanguage, targetLanguage);
    }
    
    public List<String> getSupportedLanguages(String provider) {
        TranslationProviderAdapter adapter = getAdapter(provider);
        return adapter.getSupportedLanguages();
    }
    
    private TranslationProviderAdapter getAdapter(String provider) {
        switch (provider.toLowerCase()) {
            case "google":
                return googleAdapter;
            case "microsoft":
                return microsoftAdapter;
            case "yandex":
                return yandexAdapter;
            default:
                throw new RuntimeException("Invalid provider: " + provider);
        }
    }
}

