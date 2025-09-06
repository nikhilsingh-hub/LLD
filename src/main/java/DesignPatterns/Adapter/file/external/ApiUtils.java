package DesignPatterns.Adapter.file.external;

public class ApiUtils {
    public static void logGoogleGetSupportedLanguages() {
        System.out.println("Getting supported languages from Google Translate API");
    }

    public static void logGoogleTranslate() {
        System.out.println("Translating text using Google Translate API");
    }

    public static void logMicrosoftGetSupportedLanguages() {
        System.out.println("Getting supported languages from Microsoft Translator API");
    }

    public static void logMicrosoftTranslate() {
        System.out.println("Translating text using Microsoft Translator API");
    }
    
    public static void logYandexGetSupportedLanguages() {
        System.out.println("Getting supported languages from Yandex Translate API");
    }

    public static void logYandexTranslate() {
        System.out.println("Translating text using Yandex Translate API");
    }
}
