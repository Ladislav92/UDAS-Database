package ba.rs.udas.database.ui;

import static ba.rs.udas.database.Utils.Preconditions.checkNotNull;

import ba.rs.udas.database.ui.controllers.Controller;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Loader {

  private static Map<Class<? extends Controller>, Loader> loaders = new LinkedHashMap<>();
  private Stage stage;
  private FXMLLoader loader;
  private Scene scene;
  private Node mainNode;

  private Loader(Stage stage, Class<? extends Controller> controller,
                 ResourceBundle resourceBundle) {
    this.stage = stage;

    loader = new FXMLLoader(getFXMLLocation(controller));
    loader.setResources(resourceBundle);

    try {
      mainNode = loader.load();
      scene = new Scene((Parent) mainNode);
      scene.getStylesheets().add("/assets/style.css");
    } catch (IOException e) {
      System.out.println("Error: " + e); //TODO: logging
    }
  }

  public static Loader setInstance(Stage stage, Class<? extends Controller> clazz) {
    return setInstance(stage, clazz, null);
  }

  public static Loader setInstance(Stage stage, Class<? extends Controller> clazz,
                                   ResourceBundle resourceBundle) {
    Loader tmp = new Loader(stage, clazz, resourceBundle);
    ((Controller) tmp.loader.getController()).postInit(tmp);
    loaders.put(clazz, tmp);
    return tmp;
  }

  public static Loader getInstance(Class<? extends Controller> clazz) {
    return loaders.get(clazz);
  }

  public static ResourceBundle getResourceBundle(Class<? extends Controller> type) {
    return getResourceBundle(type, Locale.getDefault());
  }

  public static ResourceBundle getResourceBundle(Class<? extends Controller> type, Locale locale) {
    checkNotNull(type, "type");
    checkNotNull(locale, "locale");

    final String baseName = type.getPackage().getName() + "." + type.getSimpleName();

    ResourceBundle resourceBundle = null;
    try {
      resourceBundle = ResourceBundle
          .getBundle(baseName, locale, type.getClassLoader(), new UTF8Control());
    } catch (MissingResourceException e) {
      System.out.println("Debug: " + e.getMessage()); //TODO: logging
    }
    return resourceBundle;
  }

  private static URL getFXMLLocation(Class<? extends Controller> type) {
    return type.getResource(getFxmlFileName(type));
  }

  private static String getFxmlFileName(Class<? extends Controller> type) {
    return type.getSimpleName() + ".fxml";
  }

  public <T extends Controller> T getController(Class<T> clazz) {
    return clazz.cast(loader.getController());
  }

  public Stage getStage() {
    return stage;
  }

  public void setStage(Stage stage) {
    this.stage = stage;
  }

  public Scene getScene() {
    return scene;
  }

  public Node getNode() {
    return mainNode;
  }

  public Object getRawController() {
    return loader.getController();
  }

  private static class UTF8Control extends ResourceBundle.Control {

    public ResourceBundle newBundle(String baseName, Locale locale, String format,
                                    ClassLoader loader, boolean reload) throws IOException {

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


}
