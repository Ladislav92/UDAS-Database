package ba.rs.udas.database.ui.controllers;

import ba.rs.udas.database.ui.Loader;

public interface Controller {

  default void postInit(Loader loader) {
  }
}
