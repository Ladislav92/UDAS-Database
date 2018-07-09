package ba.rs.udas.database.ui;

import static ba.rs.udas.database.Utils.Preconditions.checkNotNull;

import ba.rs.udas.database.Main;
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
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Loader {

  private static Map<Class<? extends Controller>, Loader> loaders = new LinkedHashMap<>();
  private static Stage activeStage;

  private FXMLLoader loader;

  private Stage stage;
  private Scene scene;
  private Node rootNode;

  private Loader(Class<? extends Controller> controller, ResourceBundle resourceBundle, boolean keepStage) {
    loader = new FXMLLoader(getFXMLLocation(controller));
    loader.setResources(resourceBundle);

    try {
      rootNode = loader.load();
      scene = new Scene((Parent) rootNode);
      scene.getStylesheets().add("/assets/style.css");

      Controller controllerInstance = loader.getController();
      if (!keepStage || activeStage == null) {
        stage = controllerInstance.prepareStage(resourceBundle);
        activeStage = stage;
      } else {
        stage = activeStage;
        controllerInstance.updateStage(activeStage, resourceBundle);
      }

      stage.setScene(scene);

    } catch (IOException e) {
      System.out.println("Error: " + e); //TODO: logging
    }
  }

  public static Loader getInstance(Class<? extends Controller> clazz) {
    Loader loader = loaders.get(clazz);
    if (loader == null) {
      throw new IllegalStateException("No such loader.");
    }

    return loader;
  }

  public static Loader setInstance(Class<? extends Controller> clazz) {
    return setInstance(clazz, getResourceBundle(clazz, Locale.getDefault()), false);
  }

  public static Loader setInstance(Class<? extends Controller> clazz, boolean keepStage) {
    return setInstance(clazz, getResourceBundle(clazz, Locale.getDefault()), keepStage);
  }

  public static Loader setInstance(Class<? extends Controller> clazz, Locale locale, boolean keepStage) {
    return setInstance(clazz, getResourceBundle(clazz, locale), keepStage);
  }

  public static Loader setInstance(Class<? extends Controller> clazz, ResourceBundle resourceBundle,
                                   boolean keepStage) {
    Loader tmp = new Loader(clazz, resourceBundle, keepStage);
    loaders.put(clazz, tmp);
    return tmp;
  }

  public static ResourceBundle getResourceBundle(Class<? extends Controller> clazz, Locale locale) {
    checkNotNull(clazz, "clazz");
    checkNotNull(locale, "locale");

    final String baseName = clazz.getPackage().getName() + "." + clazz.getSimpleName();

    ResourceBundle resourceBundle = null;
    try {
      resourceBundle =
          ResourceBundle.getBundle(baseName, locale, clazz.getClassLoader(), new UTF8Control());
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

  public Object getRawController() {
    return loader.getController();
  }

  public Node getRootNode() {
    return rootNode;
  }

  public Scene getScene() {
    return scene;
  }

  public Stage getStage() {
    return stage;
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

  public static class StaticResources {

    public static final Image APP_ICON = new Image(
        Main.class.getResourceAsStream("/assets/img/appicon_256px.png"));
  }
}
