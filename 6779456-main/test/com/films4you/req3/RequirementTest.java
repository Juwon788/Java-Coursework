package com.films4you.req3;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

public class RequirementTest {
  
  /**
   * A sample test. You'll need to test your solution properly.
   */
  @Test
  public void testRequirementGetActual() {
    Requirement r = new Requirement();
    assertEquals("101", r.getValueAsString());
  }
  @Test
  public void testRequirementGetHumanReadable() {
	    Requirement r2 = new Requirement();
	    assertEquals(r2.getHumanReadable(), "The number of films that contain the word 'Documentary' is 101");
  }
 
}
