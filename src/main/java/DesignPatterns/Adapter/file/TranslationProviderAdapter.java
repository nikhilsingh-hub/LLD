package DesignPatterns.Adapter.file;

import java.util.List;

public interface TranslationProviderAdapter {
    
    /**
     * Translates text from source language to target language
     * @param text the text to translate
     * @param sourceLanguage the source language code
     * @param targetLanguage the target language code
     * @return the translated text
     */
    String translate(String text, String sourceLanguage, String targetLanguage);
    
    /**
     * Gets the list of supported languages for this translation provider
     * @return list of supported language codes
     */
    List<String> getSupportedLanguages();
}
