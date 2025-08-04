package com.films4you.req4;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RequirementTest {
  
  /**
   * A sample test. You'll need to test your solution properly.
   */
  @Test
  public void testRequirementGetActual() {
    Requirement r = new Requirement();
    assertEquals("200\n"
    		+ "0\n"
    		+ "0\n"
    		+ "0\n"
    		+ "0\n"
    		+ "0\n", r.getValueAsString());
  }
  @Test
  public void testRequirementGetHumanReadable() {
	    Requirement r2 = new Requirement();
	    assertEquals(r2.getHumanReadable(),"Language             Number of Actors\n"
	    		+ "English                     200\n"
	    		+ "Italian                     0\n"
	    		+ "Japanese                    0\n"
	    		+ "Mandarin                    0\n"
	    		+ "French                      0\n"
	    		+ "German                      0\n");
  }
}
