package com.films4you.req3;

public class DescriptionCheck {
	
	public String description = "";
	
	public DescriptionCheck(String description) {
		this.description = description;    //Sets the description string
	}
	
	public boolean docCheck() {
		if (description.indexOf("Documentary") != -1) { //Checks if the word "Documentary" is in each sentence 
			return true;
		}
		else {
			return false;
		}
	}

}
