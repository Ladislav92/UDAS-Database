package ba.rs.udas.database.ui;

import java.util.Locale;
import javafx.util.StringConverter;

public final class LanguageManager {

  private static Language activeLanguage = Language.SERBIAN_LATIN;

  public static Language getActiveLanguage() {
    return activeLanguage;
  }

  public static void changeLanguage(Language language) {
    activeLanguage = language;
  }

  public static void changeLanguage(Language language, StageManager manager) {
    activeLanguage = language;
    manager.reloadScene();
  }

  public enum Language {
    SERBIAN_LATIN(Locale.forLanguageTag("sr-Latin"), "Srpski"),
    SERBIAN_CYRILLIC(Locale.forLanguageTag("sr-Cyrillic"), "Српски"),
    ENGLISH(Locale.forLanguageTag("en"), "English");

    private final Locale locale;
    private final String literal;

    Language(Locale locale, String literal) {
      this.locale = locale;
      this.literal = literal;
    }

    public static Language fromLiteral(String literal) {
      for (Language e : values()) {
        if (e.getLiteral().equals(literal)) {
          return e;
        }
      }

      return null;
    }

    public Locale getLocale() {
      return locale;
    }

    public String getLiteral() {
      return literal;
    }

    public static class LanguageToStringConverter extends StringConverter<Language> {

      @Override
      public String toString(Language object) {
        return object.getLiteral();
      }

      @Override
      public Language fromString(String string) {
        return Language.fromLiteral(string);
      }
    }
  }
}