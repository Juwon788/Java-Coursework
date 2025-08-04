package com.films4you.req4;

import java.util.ArrayList;
import java.util.List;

public class Languages {
	public List<String> languages = null;
	
	public Languages() {
		this.languages = new ArrayList<String>(); //Initialises the array
	}

	public String getLanguages(int languageNumber) { //Getter for language
		return this.languages.get(languageNumber);
	}

	public void addLanguages(String language) { //Setter for language
		this.languages.add(language) ;
	}
	
	public int getSize() { //Gets the size of the array
		return this.languages.size();
	}
	
}
