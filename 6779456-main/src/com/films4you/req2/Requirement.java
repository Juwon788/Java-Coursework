package com.films4you.req2;

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

 * @author Your name
 * Juwon Atiku
 *
 */
public class Requirement implements RequirementInterface {
  
	private static final String URI_TESTING_DB = "jdbc:sqlite:testing.db";
	private @Nullable Connection conn = null; //Connects to the test database
	
	
  @Override
  public @Nullable String getValueAsString() {
    //throw new TaskNotAttemptedException();
	  try {
	      if (conn == null) {
	    	  conn = DriverManager.getConnection(URI_TESTING_DB);
	      }
	Films films = new Films();
	NoOfActors noOfActors = new NoOfActors();
	Statement st = conn.createStatement();
	String sql = "SELECT * FROM film_list"; //Selects all the elements from the film list
	ResultSet rs = st.executeQuery(sql);
	String temp = "";
	int noOfActor = 1;
	while (rs.next()) {
    	  String film1 = rs.getString("title");
    	  if (temp.equals("")) { //Uses the temp value check if the value has already been counted
    		  films.addFilms(film1);
    		  temp = film1;
    	  }
    	  else {
	    	  if (!temp.equals(film1)) {
	    		  films.addFilms(film1); //Adds another film to the list
	    		  temp = film1;
	    		  noOfActors.addNoOfActors(noOfActor);
	    		  noOfActor = 1;
	    	  }
	    	  else { //Actors is incremented
	    		  noOfActor += 1;
	    	  }
    	  }
	}
    	  
	if (!temp.equals("")) {
		noOfActors.addNoOfActors(noOfActor);
	}
	
	int largestNoOfActors = 0;
	String filmWithLargestActors = "";
	for (int i = 0; i < films.getSize(); i++) { //Compares all films to their numbers of actors
		if (largestNoOfActors < noOfActors.getNoOfActors(i)) {
			largestNoOfActors = noOfActors.getNoOfActors(i);
			filmWithLargestActors = films.getFilm(i);
		}
	}
	return filmWithLargestActors;
    	  
	
	 } catch (SQLException e) {
	      System.err.println(e);
	  }
	return null;
  }

  @Override
  public @NonNull String getHumanReadable() { //Same as getValueToSTring()
    //throw new TaskNotAttemptedException();
	  try {
	      if (conn == null) {
	    	  conn = DriverManager.getConnection(URI_TESTING_DB);
	      }
	Films films = new Films();
	NoOfActors noOfActors = new NoOfActors();
	Statement st = conn.createStatement();
	String sql = "SELECT * FROM film_list";
	ResultSet rs = st.executeQuery(sql);
	String temp = "";
	int noOfActor = 1;
	while (rs.next()) {
    	  String film1 = rs.getString("title");
    	  if (temp.equals("")) {
    		  films.addFilms(film1);
    		  temp = film1;
    	  }
    	  else {
	    	  if (!temp.equals(film1)) {
	    		  films.addFilms(film1);
	    		  temp = film1;
	    		  noOfActors.addNoOfActors(noOfActor);
	    		  noOfActor = 1;
	    	  }
	    	  else {
	    		  noOfActor += 1;
	    	  }
    	  }
	}
    	  
	if (!temp.equals("")) {
		noOfActors.addNoOfActors(noOfActor);
	}
	
	int largestNoOfActors = 0;
	String filmWithLargestActors = "";
	for (int i = 0; i < films.getSize(); i++) {
		if (largestNoOfActors < noOfActors.getNoOfActors(i)) {
			largestNoOfActors = noOfActors.getNoOfActors(i);
			filmWithLargestActors = films.getFilm(i);
		}
	}
	return "Film with most actors is " + filmWithLargestActors + " with " + largestNoOfActors + " actors"; //Adds this sentence
  	
    } catch (SQLException e) {
	      System.err.println(e);
	  }
	return null;
  }
}
