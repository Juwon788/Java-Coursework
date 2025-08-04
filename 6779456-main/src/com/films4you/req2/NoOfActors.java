package com.films4you.req2;

import java.util.ArrayList;
import java.util.List;

public class NoOfActors {
	public List<Integer> noOfActors = null;
	
	public NoOfActors() {
		this.noOfActors= new ArrayList<Integer>(); 
	}
	
	public void addNoOfActors(Integer noOfActor) { //adds number of actors to the array
		this.noOfActors.add(noOfActor);
	}
	
	public int getSize() { //Gets the size of the array
		return this.noOfActors.size();
	}
	
	public int getNoOfActors(int actorNumber) { //Gets the number of actors from the array by it's actor Number
		return this.noOfActors.get(actorNumber);
	}

}
