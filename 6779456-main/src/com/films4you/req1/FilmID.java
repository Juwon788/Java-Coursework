package com.films4you.req1;

import java.util.ArrayList;
import java.util.List;

public class FilmID {
	public List<String>filmIDs = null;
	
	public FilmID() {
		this.filmIDs = new ArrayList<String>(); //Initialises the array
	}

	public String getFilmIDs(int idNumber) { //Gets the film at the index of the idNumber
		return this.filmIDs.get(idNumber);
	}

	public void addFilmIDs(String filmIDs) { //Adds the film to the array
		this.filmIDs.add(filmIDs);
	}
	
}
