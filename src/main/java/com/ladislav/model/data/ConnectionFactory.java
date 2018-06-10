package com.ladislav.model.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

  private static final String SQL_SERVER = "jdbc:mysql://localhost/udas?" +
      "user=root&password=qwerty&useSSL=false";

  /**
   * Get a connection to database, user must provide valid credentials - SQL Authentification is
   * used
   *
   * @return Connection object
   */
  public static Connection getConnection() throws SQLException {

//    String connectionUrl = SQL_SERVER + SQL_USER + username + SQL_PASSWORD + password + ";";
    return DriverManager.getConnection(SQL_SERVER);
  }

  /**
   * Test Connection
   */
  public static void main(String[] args) throws SQLException {
    Connection connection = ConnectionFactory.getConnection();

  }
}


