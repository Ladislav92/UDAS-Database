package ba.rs.udas.database.view;

import static ba.rs.udas.database.util.Utils.Preconditions.checkNotNull;

import ba.rs.udas.database.Main;
import ba.rs.udas.database.util.Utils.Reflections;
import ba.rs.udas.database.controllers.Controller;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public final class StageManager {

  private final Stage stage;

  public StageManager(Stage stage) {
    checkNotNull(stage, "stage");
    this.stage = stage;
    stage.sceneProperty().addListener(this::setStageMinSizeListener);
  }

  /**
   *  By default if stage minimal dimension; let's say minWidth was bound to a scene width, and scene root
   *  prefWidth is set, initial stage width calculation have unintended behaviour where window decorations
   *  width added to a stage width, which allows stage to be resized by the width of such decoration.
   *  This code calculates proper Stage size if scene prefWidth was set.
   *
   */
  private void setStageMinSizeListener(ObservableValue<? extends Scene> _1, Scene _2, Scene newScene) {
    Platform.runLater(() -> {
      Parent root = newScene.getRoot();

      Optional<Double> sceneMinWidth = getMinWidth(root);
      Optional<Double> sceneMinHeight = getMinHeight(root);
      Optional<Double> scenePrefWidth = getPrefWidth(root);
      Optional<Double> scenePrefHeight = getPrefHeight(root);

      double stageWidth = stage.getWidth();
      double stageHeight = stage.getHeight();
      double stageMinWidth = stageWidth;
      double stageMinHeight = stageHeight;

      if (sceneMinWidth.isPresent() && scenePrefWidth.isPresent()
          && scenePrefWidth.get() > sceneMinWidth.get()) {
        stageMinWidth = sceneMinWidth.get() + (stageWidth - sceneMinWidth.get());
      }

      if (sceneMinHeight.isPresent() && scenePrefHeight.isPresent()
          && scenePrefHeight.get() > sceneMinHeight.get()) {
        stageMinHeight = sceneMinHeight.get() + (stageHeight - sceneMinHeight.get());
      }

      stage.setMinWidth(stageMinWidth);
      stage.setMinHeight(stageMinHeight);
    });
  }

  private Optional<Double> getMinWidth(Object object) {
    return Reflections.getField(Region.class, Double.class, "_minWidth", object);
  }

  private Optional<Double> getMinHeight(Object object) {
    return Reflections.getField(Region.class, Double.class, "_minHeight", object);
  }

  private Optional<Double> getPrefWidth(Object object) {
    return Reflections.getField(Region.class, Double.class, "_prefWidth", object);
  }

  private Optional<Double> getPrefHeight(Object object) {
    return Reflections.getField(Region.class, Double.class, "_prefHeight", object);
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

  public Stage getStage() {
    return stage;
  }

  public StageManager setupStage(Consumer<Stage> setupConsumer) {
    if (setupConsumer != null) {
      setupConsumer.accept(stage);
    }

    return this;
  }

  public StageManager hide() {
    stage.hide();
    return this;
  }

  public StageManager show() {
    stage.show();
    return this;
  }

  public StageManager changeScene(Class<? extends Controller> clazz) {
    return changeScene(clazz, getResourceBundle(clazz, LanguageManager.getActiveLanguage().getLocale()));
  }

  public StageManager changeScene(Class<? extends Controller> clazz, Locale locale) {
    return changeScene(clazz, getResourceBundle(clazz, locale));
  }

  public StageManager changeScene(Class<? extends Controller> clazz, ResourceBundle bundle) {
    checkNotNull(clazz, "clazz");

    FXMLLoader loader = new FXMLLoader(getFXMLLocation(clazz));
    loader.setResources(bundle);

    try {
      Scene scene = new Scene(loader.load());
      scene.setUserData(loader);
      scene.getStylesheets().add("/assets/style.css");

      if (bundle != null) {
        stage.setTitle(bundle.getString("stage_title"));
      }

      stage.setScene(scene);
    } catch (IOException e) {
      //TODO: logging
      System.out.println("Error loading scene graph for controller: " + clazz.getSimpleName() + "\n" + e);
    }

    return this;
  }

  public void reloadScene() {
    FXMLLoader loader = (FXMLLoader) this.getStage().getScene().getUserData();
    Class<? extends Controller> clazz = (Class<? extends Controller>) loader.getController().getClass();
    this.changeScene(clazz, LanguageManager.getActiveLanguage().getLocale());
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
          bundle = new PropertyResourceBundle(new InputStreamReader(stream, StandardCharsets.UTF_8));
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