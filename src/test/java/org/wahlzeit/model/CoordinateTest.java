/*
 * Autor: Daniel Mulzer
 *
 * Date: 29.10.2017
 *
 * Version: adap-cw03
 *
 * Class: CoordinateTest
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CoordinateTest {

	private static final double DELTA = 0.00001;
	private static final double TESTVALUE = 13.37;
	
	private static Coordinate firstTestCoordinate = new Coordinate(0,0,0);
	private static Coordinate secondTestCoordinate = new Coordinate(0,0,1);
	private static Coordinate thirdTestCoordinate = new Coordinate(0,1,0);
	private static Coordinate fourthTestCoordinate = new Coordinate(1,0,0);
	private static Coordinate fifthTestCoordinate = new Coordinate(0,1,1);
	private static Coordinate sixthTestCoordinate = new Coordinate(1,1,1);
	
	@Test
	public void testGetDistance() {
		//Position of Points are equal
		assertEquals(0, firstTestCoordinate.getDistance(firstTestCoordinate), DELTA);
		//Position of Points are 1 point away
		assertEquals(1, firstTestCoordinate.getDistance(secondTestCoordinate), DELTA);
		assertEquals(1, secondTestCoordinate.getDistance(firstTestCoordinate), DELTA);
		assertEquals(1, firstTestCoordinate.getDistance(thirdTestCoordinate), DELTA);
		assertEquals(1, thirdTestCoordinate.getDistance(firstTestCoordinate), DELTA);
		assertEquals(1, firstTestCoordinate.getDistance(fourthTestCoordinate), DELTA);
		assertEquals(1, fourthTestCoordinate.getDistance(firstTestCoordinate), DELTA);
		//Position of Points are sqrt(2) point away
		assertEquals(Math.sqrt(2), thirdTestCoordinate.getDistance(secondTestCoordinate), DELTA);
		assertEquals(Math.sqrt(2), secondTestCoordinate.getDistance(thirdTestCoordinate), DELTA);
		assertEquals(Math.sqrt(2), thirdTestCoordinate.getDistance(fourthTestCoordinate), DELTA);
		assertEquals(Math.sqrt(2), fourthTestCoordinate.getDistance(thirdTestCoordinate), DELTA);
		assertEquals(Math.sqrt(2), firstTestCoordinate.getDistance(fifthTestCoordinate), DELTA);
		assertEquals(Math.sqrt(2), fifthTestCoordinate.getDistance(firstTestCoordinate), DELTA);
		//Position of Points are sqrt(3) point away
		assertEquals(Math.sqrt(3), firstTestCoordinate.getDistance(sixthTestCoordinate), DELTA);
		assertEquals(Math.sqrt(3), sixthTestCoordinate.getDistance(firstTestCoordinate), DELTA);
		assertEquals(Math.sqrt(3), fourthTestCoordinate.getDistance(fifthTestCoordinate), DELTA);
		assertEquals(Math.sqrt(3), fifthTestCoordinate.getDistance(fourthTestCoordinate), DELTA);
	}
	
	@Test
	public void testIsEqual() {
		assertTrue(firstTestCoordinate.isEqual(firstTestCoordinate));
		assertTrue(firstTestCoordinate.isEqual(new Coordinate(0,0,0)));
		assertFalse(secondTestCoordinate.isEqual(firstTestCoordinate));
		assertFalse(secondTestCoordinate.isEqual(new Coordinate(1,1,1)));
		assertFalse(firstTestCoordinate.isEqual(null));
	}
	
	@Test
	public void testEquals() {
		Coordinate firstTempTestCoordinate = new Coordinate(0,0,0);
		Coordinate secondTempTestCoordinate = new Coordinate(-10,1,-1337);
		Coordinate thirdTempTestCoordinate = new Coordinate(1.1,0,1957);
		Coordinate fourthTempTestCoordinate = new Coordinate(1.1,0,1957);
		assertTrue(firstTestCoordinate.equals(firstTestCoordinate));
		assertTrue(firstTempTestCoordinate.equals(firstTestCoordinate));
		assertTrue(thirdTempTestCoordinate.equals(fourthTempTestCoordinate));
		assertFalse(firstTestCoordinate.equals(secondTempTestCoordinate));
		assertFalse(firstTestCoordinate.equals(null));
		assertFalse(firstTestCoordinate.equals(new Object()));
	}
	
	@Test
	public void testXCoordinate() {
	    Coordinate firstTempTestCoordinate = new Coordinate(0,0,0);
	    assertEquals(0, firstTempTestCoordinate.getX(),DELTA);
	    firstTempTestCoordinate = new Coordinate(TESTVALUE, 0, 0);
	    assertEquals(TESTVALUE, firstTempTestCoordinate.getX(), DELTA);
	}
	
	@Test
	public void testYCoordinate() {
	    Coordinate firstTempTestCoordinate = new Coordinate(0,0,0);
	    assertEquals(0, firstTempTestCoordinate.getY(),DELTA);
	    firstTempTestCoordinate = new Coordinate(0, TESTVALUE, 0);
	    assertEquals(TESTVALUE, firstTempTestCoordinate.getY(), DELTA);
	}
	
	@Test
	public void testZCoordinate() {
	    Coordinate firstTempTestCoordinate = new Coordinate(0,0,0);
	    assertEquals(0, firstTempTestCoordinate.getZ(),DELTA);
	    firstTempTestCoordinate = new Coordinate(0, 0, TESTVALUE);
	    assertEquals(TESTVALUE, firstTempTestCoordinate.getZ(), DELTA);
	}

}
