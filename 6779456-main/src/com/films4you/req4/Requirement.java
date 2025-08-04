package com.films4you.req4;

import com.films4you.main.RequirementInterface;
import com.films4you.main.TaskNotAttemptedException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Your class description here.

 * @author Juwon Atiku
 *
 */
public class Requirement implements RequirementInterface {
	
	private static final String URI_TESTING_DB = "jdbc:sqlite:testing.db"; //Using the sql database
	private @Nullable Connection conn = null; 
	
  
  @Override
  public @Nullable String getValueAsString() {
    //throw new TaskNotAttemptedException();
	  try {
		  if (conn == null) {
    	  conn = DriverManager.getConnection(URI_TESTING_DB);
      }
    Languages languages = new Languages(); //Initialises the language class
	Statement st = conn.createStatement(); 
	Statement st1 = conn.createStatement();
	Statement st2 = conn.createStatement();
	String sql = "SELECT * FROM language"; //Selects all the element for the table language
	ResultSet rs = st.executeQuery(sql);
	
	int rowCount = 0;
	List<Integer> languageIDs = new ArrayList<>();
	while (rs.next()) { //This while loop puts all of the languages in a list
		String language = rs.getString("name");
		languages.addLanguages(language);
		rowCount++;
		languageIDs.add(rs.getInt("language_id"));
	}
	int[] langNum = new int[rowCount];
    Arrays.fill(langNum, 0); //Sets all the values in that array to 0
    
    
    String sql1 = "SELECT * FROM film"; //Selects all the elements from the film table
	ResultSet rs1 = st1.executeQuery(sql1);
	
	String sql2 = "SELECT * FROM film_actor"; //Selects all the elements from the film_actor table
	ResultSet rs2 = st2.executeQuery(sql2);
    
	String temp = "";
	for (int i = 0; i < langNum.length; i++) { //This is what check the language and increment the langNum array depending on the actor and the language of the film they act in.
		int languageID = languageIDs.get(i);
	    while (rs1.next()) {
	    	int languageID1 = rs1.getInt("language_id");
	    	int film_filmID = rs1.getInt("film_id");
	    	if (languageID == languageID1) {
	    		while (rs2.next()) {
	    			int actor_filmID = rs1.getInt("film_id");
	    			if (film_filmID == actor_filmID) {
		    			if (!temp.equals(rs2.getString("actor_id"))) {
		    				temp = rs2.getString("actor_id");
		    				langNum[i] += 1;
		    			}
		    			else {
		    				langNum[i] += 0;
		    			}
	    			}
	    		}
	    		
	    	}
	    	else {
	    		continue;
	    	}
	    	
	    }
	}
    
	String filmLanguages = "";
    for (int j = 0; j < langNum.length; j++) {
    	filmLanguages += langNum[j] + "\n";
    }
		
		
	return filmLanguages;
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
    Languages languages = new Languages();
	Statement st = conn.createStatement();
	Statement st1 = conn.createStatement();
	Statement st2 = conn.createStatement();
	String sql = "SELECT * FROM language";
	ResultSet rs = st.executeQuery(sql);
	
	int rowCount = 0;
	List<Integer> languageIDs = new ArrayList<>();
	while (rs.next()) {
		String language = rs.getString("name");
		languages.addLanguages(language);
		rowCount++;
		languageIDs.add(rs.getInt("language_id"));
	}
	int[] langNum = new int[rowCount];
    Arrays.fill(langNum, 0);
    
    
    String sql1 = "SELECT * FROM film";
	ResultSet rs1 = st1.executeQuery(sql1);
	
	String sql2 = "SELECT * FROM film_actor";
	ResultSet rs2 = st2.executeQuery(sql2);
    
	String temp = "";
	for (int i = 0; i < langNum.length; i++) {
		int languageID = languageIDs.get(i);
	    while (rs1.next()) {
	    	int languageID1 = rs1.getInt("language_id");
	    	int film_filmID = rs1.getInt("film_id");
	    	if (languageID == languageID1) {
	    		while (rs2.next()) {
	    			int actor_filmID = rs1.getInt("film_id");
	    			if (film_filmID == actor_filmID) {
		    			if (!temp.equals(rs2.getString("actor_id"))) {
		    				temp = rs2.getString("actor_id");
		    				langNum[i] += 1;
		    			}
		    			else {
		    				langNum[i] += 0;
		    			}
	    			}
	    		}
	    		
	    	}
	    	else {
	    		languageID = languageID1;
	    		continue;
	    	}
	    	
	    }
	}
    
	String filmLanguages = "Language             Number of Actors\n"; //Gets the language and the number of actors
    for (int j = 0; j < langNum.length; j++) { //Changes depending on the length of the language
    	if (languages.getLanguages(j).length() == 6) {
    		filmLanguages += languages.getLanguages(j) + "                      "+ langNum[j] + "\n";
    	}
    	if (languages.getLanguages(j).length() == 7) {
    		filmLanguages += languages.getLanguages(j) + "                     "+ langNum[j] + "\n";
    	}
    	if (languages.getLanguages(j).length() == 8) {
    		filmLanguages += languages.getLanguages(j) + "                    "+ langNum[j] + "\n";
    	}
    }
		
		
	return filmLanguages;
	} catch (SQLException e) {
	      System.err.println(e);
	  }
	return null;
	}
}
