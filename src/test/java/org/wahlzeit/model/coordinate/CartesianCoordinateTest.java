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
package org.wahlzeit.model.coordinate;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.model.coordinate.CartesianCoordinate;
import org.wahlzeit.utils.DoubleUtil;

public class CartesianCoordinateTest {

    private static final double TESTVALUE = 13.37;

    private static CartesianCoordinate firstTestCoordinate = new CartesianCoordinate(0, 0, 0);
    private static CartesianCoordinate secondTestCoordinate = new CartesianCoordinate(0, 0, 1);
    private static CartesianCoordinate thirdTestCoordinate = new CartesianCoordinate(0, 1, 0);
    private static CartesianCoordinate fourthTestCoordinate = new CartesianCoordinate(1, 0, 0);
    private static CartesianCoordinate fifthTestCoordinate = new CartesianCoordinate(0, 1, 1);
    private static CartesianCoordinate sixthTestCoordinate = new CartesianCoordinate(1, 1, 1);

    @Test
    public void testGetDistance() {
	// Position of Points are equal
	assertEquals(0, firstTestCoordinate.getDistance(firstTestCoordinate), DoubleUtil.DELTA);
	// Position of Points are 1 point away
	assertEquals(1, firstTestCoordinate.getDistance(secondTestCoordinate), DoubleUtil.DELTA);
	assertEquals(1, secondTestCoordinate.getDistance(firstTestCoordinate), DoubleUtil.DELTA);
	assertEquals(1, firstTestCoordinate.getDistance(thirdTestCoordinate), DoubleUtil.DELTA);
	assertEquals(1, thirdTestCoordinate.getDistance(firstTestCoordinate), DoubleUtil.DELTA);
	assertEquals(1, firstTestCoordinate.getDistance(fourthTestCoordinate), DoubleUtil.DELTA);
	assertEquals(1, fourthTestCoordinate.getDistance(firstTestCoordinate), DoubleUtil.DELTA);
	// Position of Points are sqrt(2) point away
	assertEquals(Math.sqrt(2), thirdTestCoordinate.getDistance(secondTestCoordinate), DoubleUtil.DELTA);
	assertEquals(Math.sqrt(2), secondTestCoordinate.getDistance(thirdTestCoordinate), DoubleUtil.DELTA);
	assertEquals(Math.sqrt(2), thirdTestCoordinate.getDistance(fourthTestCoordinate), DoubleUtil.DELTA);
	assertEquals(Math.sqrt(2), fourthTestCoordinate.getDistance(thirdTestCoordinate), DoubleUtil.DELTA);
	assertEquals(Math.sqrt(2), firstTestCoordinate.getDistance(fifthTestCoordinate), DoubleUtil.DELTA);
	assertEquals(Math.sqrt(2), fifthTestCoordinate.getDistance(firstTestCoordinate), DoubleUtil.DELTA);
	// Position of Points are sqrt(3) point away
	assertEquals(Math.sqrt(3), firstTestCoordinate.getDistance(sixthTestCoordinate), DoubleUtil.DELTA);
	assertEquals(Math.sqrt(3), sixthTestCoordinate.getDistance(firstTestCoordinate), DoubleUtil.DELTA);
	assertEquals(Math.sqrt(3), fourthTestCoordinate.getDistance(fifthTestCoordinate), DoubleUtil.DELTA);
	assertEquals(Math.sqrt(3), fifthTestCoordinate.getDistance(fourthTestCoordinate), DoubleUtil.DELTA);
    }

    @Test
    public void testIsEqual() {
	assertTrue(firstTestCoordinate.isEqual(firstTestCoordinate));
	assertTrue(firstTestCoordinate.isEqual(new CartesianCoordinate(0, 0, 0)));
	assertTrue(firstTestCoordinate
		.isEqual(new CartesianCoordinate(DoubleUtil.DELTA / 10, DoubleUtil.DELTA / 10, DoubleUtil.DELTA / 10)));
	assertFalse(secondTestCoordinate.isEqual(firstTestCoordinate));
	assertFalse(secondTestCoordinate.isEqual(new CartesianCoordinate(1, 1, 1)));
	assertFalse(firstTestCoordinate.isEqual(null));
	assertFalse(firstTestCoordinate
		.isEqual(new CartesianCoordinate(DoubleUtil.DELTA, DoubleUtil.DELTA, DoubleUtil.DELTA)));

    }

    @Test
    public void testEquals() {
	CartesianCoordinate firstTempTestCoordinate = new CartesianCoordinate(0, 0, 0);
	CartesianCoordinate secondTempTestCoordinate = new CartesianCoordinate(-10, 1, -1337);
	CartesianCoordinate thirdTempTestCoordinate = new CartesianCoordinate(1.1, 0, 1957);
	CartesianCoordinate fourthTempTestCoordinate = new CartesianCoordinate(1.1, 0, 1957);
	assertTrue(firstTestCoordinate.equals(firstTestCoordinate));
	assertTrue(firstTempTestCoordinate.equals(firstTestCoordinate));
	assertTrue(thirdTempTestCoordinate.equals(fourthTempTestCoordinate));
	assertFalse(firstTestCoordinate.equals(secondTempTestCoordinate));
	try {
	    assertFalse(firstTestCoordinate.equals(null));
	    fail("invalid assertion passed!");
	} catch (IllegalArgumentException er) {
	}
	assertFalse(firstTestCoordinate.equals(new Object()));
    }

    @Test
    public void testXCoordinate() {
	CartesianCoordinate firstTempTestCoordinate = new CartesianCoordinate(0, 0, 0);
	assertEquals(0, firstTempTestCoordinate.getX(), DoubleUtil.DELTA);
	firstTempTestCoordinate = new CartesianCoordinate(TESTVALUE, 0, 0);
	assertEquals(TESTVALUE, firstTempTestCoordinate.getX(), DoubleUtil.DELTA);
    }

    @Test
    public void testYCoordinate() {
	CartesianCoordinate firstTempTestCoordinate = new CartesianCoordinate(0, 0, 0);
	assertEquals(0, firstTempTestCoordinate.getY(), DoubleUtil.DELTA);
	firstTempTestCoordinate = new CartesianCoordinate(0, TESTVALUE, 0);
	assertEquals(TESTVALUE, firstTempTestCoordinate.getY(), DoubleUtil.DELTA);
    }

    @Test
    public void testZCoordinate() {
	CartesianCoordinate firstTempTestCoordinate = new CartesianCoordinate(0, 0, 0);
	assertEquals(0, firstTempTestCoordinate.getZ(), DoubleUtil.DELTA);
	firstTempTestCoordinate = new CartesianCoordinate(0, 0, TESTVALUE);
	assertEquals(TESTVALUE, firstTempTestCoordinate.getZ(), DoubleUtil.DELTA);
    }

    @Test
    public void testHashCode() {
	assertEquals(firstTestCoordinate.hashCode(), (new CartesianCoordinate(firstTestCoordinate.getX(),
		firstTestCoordinate.getY(), firstTestCoordinate.getZ()).hashCode()));
	assertNotEquals(firstTestCoordinate.hashCode(), secondTestCoordinate.hashCode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptiongetCartesianDistance() {
	firstTestCoordinate.getCartesianDistance(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptiongetDistance() {
	firstTestCoordinate.getDistance(null);
    }

}
