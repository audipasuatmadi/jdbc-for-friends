package com.tugas.ppbo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConfig {
  private  Connection connection;
  private Statement statement;

  public void setConfig() {
    try {
      connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_login", "root", "");
      statement = connection.createStatement();
    } catch (Exception e) {
      System.out.println("error occured");
      System.out.println(e);
    }
  }

  public Connection getConnection() {
    return connection;
  }

  public Statement getStatement() {
    return statement;
  }
}
