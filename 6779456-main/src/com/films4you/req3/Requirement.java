package com.films4you.req3;

import com.films4you.main.RequirementInterface;
import com.films4you.main.TaskNotAttemptedException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Your class description here.

 * @author Juwon Atiku
 *
 */
public class Requirement implements RequirementInterface {
	
	 private static final String URI_TESTING_DB = "jdbc:sqlite:testing.db";
	 private @Nullable Connection conn = null;
  
  @Override
  public @Nullable String getValueAsString() {
    //throw new TaskNotAttemptedException();
	  try {
	      if (conn == null) {
	    	  conn = DriverManager.getConnection(URI_TESTING_DB);
	      }
	 
	Statement st = conn.createStatement();
	String sql = "SELECT * FROM film"; //Selects all of the films
	ResultSet rs = st.executeQuery(sql);
	int noOfFilmWithDocumentary = 0;
	
	while (rs.next()) {
		
		String description = rs.getString("description"); //Collects all of the films descriptions
		DescriptionCheck descCheck = new DescriptionCheck(description);
		if (descCheck.docCheck() == true){ //Check if true or false is returned
			noOfFilmWithDocumentary += 1;
		}
	
	}
	return Integer.toString(noOfFilmWithDocumentary); //Returns the number of films
	
	  } catch (SQLException e) {
	      System.err.println(e);
	  }
	return null;
  }

  @Override
  public @NonNull String getHumanReadable() { //Same as getValueAsString()
    //throw new TaskNotAttemptedException();
	  try {
	      if (conn == null) {
	    	  conn = DriverManager.getConnection(URI_TESTING_DB);
	      }
	Statement st = conn.createStatement();
	String sql = "SELECT * FROM film";
	ResultSet rs = st.executeQuery(sql);
	int noOfFilmWithDocumentary = 0;
	
	while (rs.next()) {
		
		String description = rs.getString("description");
		DescriptionCheck descCheck = new DescriptionCheck(description);
		if (descCheck.docCheck() == true){
			noOfFilmWithDocumentary += 1;
		}
	}
	return "The number of films that contain the word 'Documentary' is "+ Integer.toString(noOfFilmWithDocumentary); //Returns this sentence
	
	  } catch (SQLException e) {
	      System.err.println(e);
	  }
	return null;
  }
 }


