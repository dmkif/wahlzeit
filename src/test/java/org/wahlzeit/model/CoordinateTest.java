package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CoordinateTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDistance() {
		// Distance: math
		//double distance = Math.sqrt(Math.pow((coord1.x - coord2.x, 2)+(coord1.y - coord2.y, 2)+(coord1.y - coord2.y, 2));
		Coordinate coord1 = new Coordinate(0,0,0);
		Coordinate coord2 = new Coordinate(0,0,1);
		Coordinate coord3 = new Coordinate(0,1,0);
		Coordinate coord4 = new Coordinate(1,0,0);
		Coordinate coord5 = new Coordinate(0,1,1);
		Coordinate coord6 = new Coordinate(1,1,1);
		
		//Position of Points are equal
		assertEquals(0, coord1.getDistance(coord1), 0.001);
		//Position of Points are 1 point away
		assertEquals(1, coord1.getDistance(coord2), 0.001);
		assertEquals(1, coord2.getDistance(coord1), 0.001);
		assertEquals(1, coord1.getDistance(coord3), 0.001);
		assertEquals(1, coord3.getDistance(coord1), 0.001);
		assertEquals(1, coord1.getDistance(coord4), 0.001);
		assertEquals(1, coord4.getDistance(coord1), 0.001);
		//Position of Points are sqrt(2) point away
		assertEquals(Math.sqrt(2), coord3.getDistance(coord2), 0.001);
		assertEquals(Math.sqrt(2), coord2.getDistance(coord3), 0.001);
		assertEquals(Math.sqrt(2), coord3.getDistance(coord4), 0.001);
		assertEquals(Math.sqrt(2), coord4.getDistance(coord3), 0.001);
		assertEquals(Math.sqrt(2), coord1.getDistance(coord5), 0.001);
		assertEquals(Math.sqrt(2), coord5.getDistance(coord1), 0.001);
		//Position of Points are sqrt(3) point away
		assertEquals(Math.sqrt(3), coord1.getDistance(coord6), 0.001);
		assertEquals(Math.sqrt(3), coord6.getDistance(coord1), 0.001);
		assertEquals(Math.sqrt(3), coord4.getDistance(coord5), 0.001);
		assertEquals(Math.sqrt(3), coord5.getDistance(coord4), 0.001);
	}
	
	public void testIsEqual() {
		Coordinate coord1 = new Coordinate(0,0,0);
		Coordinate coord2 = new Coordinate(0,0,1);
		
		assertTrue(coord1.isEqual(coord1));
		assertTrue(coord1.isEqual(new Coordinate(0,0,0)));
		assertFalse(coord2.isEqual(coord1));
		assertFalse(coord2.isEqual(new Coordinate(1,1,1)));
		assertFalse(coord1.isEqual(null));
	}

}
