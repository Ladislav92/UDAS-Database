package ba.rs.udas.database.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public final class ConnectionManager {

  private static final String JDBC_URL = "jdbc:mysql://localhost/udas?user=%s&password=%s"
      + "&useSSL=false&allowPublicKeyRetrieval=true";

  private static Connection connection = null;
  private static DataAdapter dataAdapter = new MySqlAdapter();

  public static Optional<Connection> getConnection() {
    return Optional.ofNullable(connection);
  }

  public static DataAdapter getDataAdapter() {
    return dataAdapter;
  }

  public static Connection connect(String user, String password) throws SQLException {
    if (!isConnected()) {
      connection = DriverManager.getConnection(String.format(JDBC_URL, user, password));
    }

    return connection;
  }

  public static void disconnect() {
    if (connection == null) {
      return;
    }

    try {
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
      //TODO: logging
    } finally {
      connection = null;
    }
  }


  public static boolean isConnected() throws SQLException {
    return connection != null && connection.isValid(3);
  }
}
