/*
 * Autor: Daniel Mulzer
 *
 * Date: 29.10.2017
 *
 * Version: adap-cw03
 *
 * Class: LocationTest
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

import org.junit.Test;
import org.wahlzeit.model.coordinate.CartesianCoordinate;

/**
 * @author dmkif
 *
 */
public class LocationTest {

    /**
     * Test method for {@link org.wahlzeit.model.Location#Location(org.wahlzeit.model.CartesianCoordinate)}.
     */
    @Test
    public void testLocation() {
	assertNotNull(new Location(new CartesianCoordinate(0,0,0)));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testLocationException() {
	new Location(null);
    }

    /**
     * Test method for {@link org.wahlzeit.model.Location#getCoordinate()}.
     */
    @Test
    public void testGetCoordinate() {
	CartesianCoordinate testCoordinate = new CartesianCoordinate(0,0,0);
	Location withZeroPointCoordinateLocation = new Location(testCoordinate);
	Location withNullCoordinateLocation = new Location(new CartesianCoordinate(0.0001,0.0,0.000));
	
	assertSame(testCoordinate, withZeroPointCoordinateLocation.getCoordinate());
	assertNotNull(withNullCoordinateLocation.getCoordinate());
	assertNotSame(testCoordinate, withNullCoordinateLocation.getCoordinate());
    }

    /**
     * Test method for {@link org.wahlzeit.model.Location#setCoordinate(org.wahlzeit.model.CartesianCoordinate)}.
     */
    @Test
    public void testSetCoordinate() {
	CartesianCoordinate testCoordinate = new CartesianCoordinate(0,0,0);
	Location testLocation = new Location(testCoordinate);
	
	assertSame(testCoordinate, testLocation.getCoordinate());
	assertTrue(testLocation.getCoordinate().equals(testCoordinate));
	
	testLocation.setCoordinate(null);
	assertNull(testLocation.getCoordinate());
	assertNotSame(testCoordinate, testLocation.getCoordinate());
	
	testLocation.setCoordinate(testCoordinate);
	assertSame(testCoordinate, testLocation.getCoordinate());
	assertTrue(testLocation.getCoordinate().equals(testCoordinate));
	
    }

    /**
     * Test method for {@link org.wahlzeit.model.Location#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObject() {
	CartesianCoordinate testCoordinate = new CartesianCoordinate(0,0,0);
	Location firstTestLocation = new Location(testCoordinate);
	Location secondTestLocation = new Location(testCoordinate);
	
	assertTrue(firstTestLocation.equals(secondTestLocation));
	
	secondTestLocation.setCoordinate(new CartesianCoordinate(1,1,1));
	assertFalse(firstTestLocation.equals(secondTestLocation));
	
	firstTestLocation.setCoordinate(new CartesianCoordinate(1,1,1));
	assertTrue(firstTestLocation.equals(secondTestLocation));
	
    }
    
    public void testHashCode() {
	CartesianCoordinate testCoordinate = new CartesianCoordinate(0,0,0);
	Location firstTestLocation = new Location(testCoordinate);
	Location secondTestLocation = new Location(testCoordinate);
	
	    assertEquals(firstTestLocation.hashCode(), (new Location(testCoordinate).hashCode()));
	    assertNotEquals(firstTestLocation.hashCode(), secondTestLocation.hashCode());
	}

}
