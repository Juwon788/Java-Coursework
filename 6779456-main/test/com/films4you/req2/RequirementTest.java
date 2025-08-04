package com.films4you.req2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RequirementTest {
  
  /**
   * A sample test. You'll need to test your solution properly.
   */
  @Test
  public void testRequirementGetActual() {
    Requirement r = new Requirement();
    assertEquals("LAMBS CINCINATTI", r.getValueAsString());
  }
 
  @Test 
  public void testRequirementGetHumanReadable() {
	    Requirement r = new Requirement();
	    assertEquals("Film with most actors is LAMBS CINCINATTI with 15 actors", r.getHumanReadable());
  }
  
}
