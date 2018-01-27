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
package org.wahlzeit.model.mainframe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.model.mainframe.MainframeManager;
import org.wahlzeit.model.mainframe.MainframePhoto;
import org.wahlzeit.model.mainframe.MainframeType;
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
	MainframeManager.setUseDatastore(false);
	defaultValuePhoto = new MainframePhoto();
	notInitalisedPhoto = null;
	valuesSetPhoto = new MainframePhoto(10, "WahlZeit", "WahlZeitModel");
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
	assertEquals(1,defaultValuePhoto.getMainframe().getMillionInstructionsPerSecond());
	assertEquals(10,valuesSetPhoto.getMainframe().getMillionInstructionsPerSecond());
	
	defaultValuePhoto.getMainframe().setMillionInstructionsPerSecond(1337);
	valuesSetPhoto.getMainframe().setMillionInstructionsPerSecond(1337);
	
	assertEquals(1337,defaultValuePhoto.getMainframe().getMillionInstructionsPerSecond());
	assertEquals(1337,defaultValuePhoto.getMainframe().getMillionInstructionsPerSecond());
    }

    /**
     * Test method for {@link org.wahlzeit.model.MainframePhoto#setMillionInstructionsPerSecond(int)}.
     */
    @Test(expected=NumberFormatException.class)
    public void testSetMillionInstructionsPerSecond() {
	valuesSetPhoto.getMainframe().setMillionInstructionsPerSecond(1337);
	assertEquals(1337, valuesSetPhoto.getMainframe().getMillionInstructionsPerSecond());
	valuesSetPhoto.getMainframe().setMillionInstructionsPerSecond(-1);
	fail("negative values should be permitted");
    }

    /**
     * Test method for {@link org.wahlzeit.model.MainframePhoto#getManufacturer()}.
     */
    @Test
    public void testGetManufacturer() {
	assertEquals("default_manufacturer", defaultValuePhoto.getMainframe().getMainframeType().getManufacturer());
	assertEquals("WahlZeit", valuesSetPhoto.getMainframe().getMainframeType().getManufacturer());
    }

    /**
     * Test method for {@link org.wahlzeit.model.MainframePhoto#setManufacturer(java.lang.String)}.
     */
    @Test(expected=IllegalArgumentException.class)
    public void testSetManufacturer() {
	MainframeType mt = MainframeManager.getInstance().createMainframeType("Google Inc.");
	defaultValuePhoto.getMainframe().getModel();
	
	
	mt = MainframeManager.getInstance().createMainframeType("");
	fail("empty string should throw an exception");
    }
}
