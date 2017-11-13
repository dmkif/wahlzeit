/*
 * Autor: Daniel Mulzer
 *
 * Date: 12.11.2017
 *
 * Version: adap-cwXX
 *
 * Class: MainframePhotoTest
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;

/**
 * @author dmkif
 *
 */
public class MainframePhotoTest extends LocalDatastoreServiceTestConfigProvider{

    private static MainframePhoto defaultValuePhoto;
    private static MainframePhoto notInitalisedPhoto;
    private static MainframePhoto valuesSetPhoto;
    
    @Before
    public void setUp() throws Throwable {
	super.before();
	defaultValuePhoto = new MainframePhoto();
	notInitalisedPhoto = null;
	valuesSetPhoto = new MainframePhoto(10, "WahlZeit");
    }
    /**
     * Test method for {@link org.wahlzeit.model.MainframePhoto#MainframePhoto()}.
     */
    @Test
    public void testMainframePhoto() {
	assertSame(MainframePhoto.class, defaultValuePhoto.getClass());
	assertNull(notInitalisedPhoto);
	assertSame(MainframePhoto.class,valuesSetPhoto.getClass());
    }

    /**
     * Test method for {@link org.wahlzeit.model.MainframePhoto#getMillionInstructionsPerSecond()}.
     */
    @Test
    public void testGetMillionInstructionsPerSecond() {
	assertEquals(0,defaultValuePhoto.getMillionInstructionsPerSecond());
	assertEquals(10,valuesSetPhoto.getMillionInstructionsPerSecond());
	
	defaultValuePhoto.setMillionInstructionsPerSecond(1337);
	valuesSetPhoto.setMillionInstructionsPerSecond(1337);
	
	assertEquals(1337,defaultValuePhoto.getMillionInstructionsPerSecond());
	assertEquals(1337,defaultValuePhoto.getMillionInstructionsPerSecond());
    }

    /**
     * Test method for {@link org.wahlzeit.model.MainframePhoto#setMillionInstructionsPerSecond(int)}.
     */
    @Test(expected=NumberFormatException.class)
    public void testSetMillionInstructionsPerSecond() {
	valuesSetPhoto.setMillionInstructionsPerSecond(1337);
	assertEquals(1337, valuesSetPhoto.getMillionInstructionsPerSecond());
	valuesSetPhoto.setMillionInstructionsPerSecond(-1);
	fail("negative values should be permitted");
    }

    /**
     * Test method for {@link org.wahlzeit.model.MainframePhoto#getManufacturer()}.
     */
    @Test
    public void testGetManufacturer() {
	assertEquals("example_manufacturer", defaultValuePhoto.getManufacturer());
	assertEquals("WahlZeit", valuesSetPhoto.getManufacturer());
	
	defaultValuePhoto.setManufacturer("IBM");
	valuesSetPhoto.setManufacturer("GREYSCALE");
	
	assertEquals("IBM",defaultValuePhoto.getManufacturer());
	assertEquals("GREYSCALE",valuesSetPhoto.getManufacturer());
    }

    /**
     * Test method for {@link org.wahlzeit.model.MainframePhoto#setManufacturer(java.lang.String)}.
     */
    @Test(expected=IllegalArgumentException.class)
    public void testSetManufacturer() {
	defaultValuePhoto.setManufacturer("Google Inc.");
	valuesSetPhoto.setManufacturer("");
	fail("empty string should throw an exception");
    }
}
