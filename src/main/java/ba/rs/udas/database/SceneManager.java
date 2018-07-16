package ba.rs.udas.database;

import static ba.rs.udas.database.Utils.Preconditions.checkNotNull;

import ba.rs.udas.database.ui.controllers.Controller;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public final class SceneManager {

  private static final SceneManager instance = new SceneManager();

  private static Stage stage;

  public static Stage getStage() {
    checkStageThrowIfNull();
    return stage;
  }

  public static SceneManager setStage(Stage stage) {
    checkNotNull(stage, "stage");
    SceneManager.stage = stage;
    return instance;
  }

  public static SceneManager setupStage(Consumer<Stage> stageSetupConsumer) {
    checkStageThrowIfNull();

    if (stageSetupConsumer != null) {
      stageSetupConsumer.accept(stage);
    }

    return instance;
  }

  public static SceneManager hide() {
    checkStageThrowIfNull();
    stage.hide();
    return instance;
  }

  public static SceneManager show() {
    checkStageThrowIfNull();
    stage.show();
    return instance;
  }

  public static SceneManager changeScene(Scene scene) {
    checkStageThrowIfNull();
    stage.setScene(scene);
    return instance;
  }

  public static SceneManager changeScene(Class<? extends Controller> clazz) {
    return changeScene(clazz, getResourceBundle(clazz, Locale.getDefault()));
  }

  public static SceneManager changeScene(Class<? extends Controller> clazz, Locale locale) {
    return changeScene(clazz, getResourceBundle(clazz, locale));
  }

  public static SceneManager changeScene(Class<? extends Controller> clazz, ResourceBundle bundle) {
    checkNotNull(clazz, "clazz");
    checkStageThrowIfNull();

    FXMLLoader loader = new FXMLLoader(getFXMLLocation(clazz));
    loader.setResources(bundle);

    try {
      Scene scene = new Scene(loader.load());
      scene.getStylesheets().add("/assets/style.css");

      if (bundle != null) {
        stage.setTitle(bundle.getString("stage_title"));
      }

      stage.setScene(scene);

    } catch (IOException e) {
      //TODO: logging
      System.out.println("Error loading scene graph for controller: " + clazz.getSimpleName() + "\n" + e);
    }

    return instance;
  }

  public static ResourceBundle getResourceBundle(Class<? extends Controller> clazz, Locale locale) {
    final String baseName = clazz.getPackage().getName() + "." + clazz.getSimpleName();

    ResourceBundle bundle = null;
    try {
      bundle = ResourceBundle.getBundle(baseName, locale, clazz.getClassLoader(), new UTF8Control());
    } catch (MissingResourceException e) {
      //TODO: proper logging.
      System.out.printf("Can't load resource bundle for baseName=%s, locale=%s, classLoader=%s\n%s\n",
          baseName, locale, clazz.getClassLoader(), e);
    }

    return bundle;
  }

  private static URL getFXMLLocation(Class<? extends Controller> type) {
    return type.getResource(getFxmlFileName(type));
  }

  private static String getFxmlFileName(Class<? extends Controller> type) {
    return type.getSimpleName() + ".fxml";
  }

  private static void checkStageThrowIfNull() {
    if (SceneManager.stage == null) {
      throw new IllegalStateException("Should set stage before using SceneManager");
    }
  }

  private static class UTF8Control extends ResourceBundle.Control {

    public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader,
                                    boolean reload) throws IOException {

      String bundleName = toBundleName(baseName, locale);
      String resourceName = toResourceName(bundleName, "properties");
      ResourceBundle bundle = null;
      InputStream stream = null;
      if (reload) {
        URL url = loader.getResource(resourceName);
        if (url != null) {
          URLConnection connection = url.openConnection();
          if (connection != null) {
            connection.setUseCaches(false);
            stream = connection.getInputStream();
          }
        }
      } else {
        stream = loader.getResourceAsStream(resourceName);
      }
      if (stream != null) {
        try {
          // Only this line is changed to make it to read properties files as UTF-8.
          bundle = new PropertyResourceBundle(new InputStreamReader(stream, "UTF-8"));
        } finally {
          stream.close();
        }
      }
      return bundle;
    }
  }

  public static final class StaticResources {

    public static final Image APP_ICON =
        new Image(Main.class.getResourceAsStream("/assets/img/appicon_256px.png"));

    private StaticResources() {
    }
  }
}