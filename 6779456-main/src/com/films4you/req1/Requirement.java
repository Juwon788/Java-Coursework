package com.films4you.req1;

import com.films4you.main.RequirementInterface;
import com.films4you.main.TaskNotAttemptedException;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.checkerframework.checker.nullness.qual.Nullable;
/**
 * Your class description here.

 * @author Your name Juwon Atiku
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
		Statement st = conn.createStatement();
		String sql = "SELECT * FROM film"; //Selects all the elements from the table film
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
	    	  String film = rs.getString("title");
	    	  films.addFilms(film);
		}
		String allFilmsBeginningWithD = "";
		for (int i = 0; i < films.getSize(); i ++) {
			if (films.getFilm(i).charAt(0) == 'D') {
				allFilmsBeginningWithD += films.getFilm(i) + "\n"; //Adds only the films that begin with the letter "D"
			}
		}
		return allFilmsBeginningWithD; //Returns those films
	    	  
		
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
		Films films = new Films();
		FilmID filmIDs = new FilmID();
		Statement st = conn.createStatement();
		String sql = "SELECT * FROM film";
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
	    	  String filmId = rs.getString("film_id");
	    	  filmIDs.addFilmIDs(filmId);
	    	  String film = rs.getString("title");
	    	  films.addFilms(film);
		}
		String allFilmsBeginningWithD = "Film ID          Film Name\n"; //Adds the film ID and the film name
		for (int i = 0; i < films.getSize(); i ++) {
			if (films.getFilm(i).charAt(0) == 'D') {
				allFilmsBeginningWithD += filmIDs.getFilmIDs(i) + "              " + films.getFilm(i) + "\n";
			}
		}
		return allFilmsBeginningWithD;
	    	  
		
		  } catch (SQLException e) {
		      System.err.println(e);
		  }
		return null;
	  }
}
	  
