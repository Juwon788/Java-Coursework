package com.films4you.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * A class to connect to, query, and close the testing database in one go.
 * 

 * @author Nick Frymann
 *
 */
public class Database {
  /* URI specifying the JBDC connection method and testing database location. */
  private static final String URI_TESTING_DB = "jdbc:sqlite:testing.db";
  private @Nullable Connection conn = null;

  /**
   * Execute query on database and handle opening and closing the connection. 

   * @param sql String containing SQL statement to execute.
   * @return ResultSet for query or null on error which is read-only and 
   *     scroll-forward only (as SQLite supports only forward cursors).
   */
  public @Nullable ResultSet query(String sql) {
    try {
      if (conn == null) {
        conn = DriverManager.getConnection(URI_TESTING_DB);
      }
      Statement st = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, 
          ResultSet.CONCUR_READ_ONLY);
      return st.executeQuery(sql);
    } catch (SQLException e) {
      System.err.println(e);
      return null;
    }
  }
  
  public boolean emailQuery(String sql) {
	  try {
	      if (conn == null) {
	    	  conn = DriverManager.getConnection(URI_TESTING_DB);
	      }
	      Statement st = conn.createStatement();
	      String sql1 = "SELECT * FROM customer WHERE email = '" + sql + "'";
	      ResultSet rs = st.executeQuery(sql1);
	      String name = rs.getString("email");
	      if (name == null) {
	    	  return false;
	      }
	      else {
	    	  return true;
	      }
	  } catch (SQLException e) {
	      System.err.println(e);
	  }
	return false;
  }
  
  public String nameQuery(String sql) {
	  try {
	      if (conn == null) {
	    	  conn = DriverManager.getConnection(URI_TESTING_DB);
	      }
	      Statement st = conn.createStatement();
	      String sql1 = "SELECT * FROM customer WHERE email = '" + sql + "'";
	      ResultSet rs = st.executeQuery(sql1);
	      String fname = rs.getString("first_name");
	      String lname = rs.getString("last_name");
	      String fullname = fname + " " + lname;
	      return fullname;
	  } catch (SQLException e) {
	      System.err.println(e);
	  }
	return null;
  }
  
  public String genreQuery(String sql) {
	  try {
	      if (conn == null) {
	    	  conn = DriverManager.getConnection(URI_TESTING_DB);
	      }
	      String filmTitle = "";
	      Statement st = conn.createStatement();
	      String sql1 = "SELECT * FROM category WHERE name = '" + sql + "'";
	      ResultSet rs = st.executeQuery(sql1);
	      
	      String sql2 = "SELECT * FROM film_category WHERE category_id = '" + rs.getString("category_id") + "'";
	      ResultSet rs2 = st.executeQuery(sql2);
	      
	      List<Integer> filmIds = new ArrayList<>();
	      while (rs2.next()) {
	    	  int filmId = rs2.getInt("film_id");
	    	  filmIds.add(filmId);
	      }
	      
	      String filmTitles = "";
	      for (int filmId : filmIds) {
		      String sql3 = "SELECT * FROM film WHERE film_id = '" + filmId + "'";
	    	  ResultSet rs3 = st.executeQuery(sql3);
	    	  while (rs3.next()) {
	    		  filmTitles += rs3.getString("title") + "\n";
	    	  }
    	  }
	      return filmTitles;
	  } catch (SQLException e) {
	      System.err.println(e);
	  }
	return null;
  }
  /**
   * Close the database connection once query finished.
   */
  public void close() {
    if (conn != null) {
      try {
        conn.close();
      } catch (SQLException e) {
        System.err.println(e);
      }
    }
  }
}
