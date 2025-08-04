package com.films4you.req2;

import java.util.ArrayList;
import java.util.List;

public class Films {
	public List<String> films = null;
		
	public Films() {
		this.films = new ArrayList<String>();
	}
		
	public void addFilms(String filmName) { //adds films to the array
		this.films.add(filmName);
	}
		
	public int getSize() { //Gets the size of the array
		return this.films.size();
	}
		
	public String getFilm(int filmNumber) { //Gets the film from the array by it's film number
		return this.films.get(filmNumber);
	}

}