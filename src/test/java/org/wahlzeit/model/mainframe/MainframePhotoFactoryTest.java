/*
 * Autor: Daniel Mulzer
 *
 * Date: 13.11.2017
 *
 * Version: adap-cwXX
 *
 * Class: MainframePhotoFactoryTest
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

import static org.junit.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoFactory;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;

/**
 * @author dmkif
 *
 */
public class MainframePhotoFactoryTest extends LocalDatastoreServiceTestConfigProvider {

    private static MainframePhotoFactory photoFactory = null;

    @Before
    public void SetUp() throws Throwable {
	this.before();
	MainframeManager.setUseDatastore(false);
    }

    /**
     * Test method for
     * {@link org.wahlzeit.model.MainframePhotoFactory#createPhoto()}.
     */
    @Test
    public void testCreatePhoto() {
	assertThat(MainframePhotoFactory.getInstance().createPhoto(), instanceOf(Photo.class));
    }

    /**
     * Test method for
     * {@link org.wahlzeit.model.MainframePhotoFactory#getInstance()}.
     */
    @Test
    public void testGetInstance() {
	assertNotNull(MainframePhotoFactory.getInstance());
	assertNotNull(MainframePhotoFactory.getInstance());
	assertNotNull(MainframePhotoFactory.getInstance());
    }

    /**
     * Test method for
     * {@link org.wahlzeit.model.MainframePhotoFactory#setInstance(org.wahlzeit.model.MainframePhotoFactory)}.
     */
    @Test(expected = IllegalStateException.class)
    public void testSetInstanceMainframePhotoFactory() {
	assertNotNull(PhotoFactory.getInstance());
	PhotoFactory.setInstance(PhotoFactory.getInstance());
	fail("only one instace should instancable!");
    }

}
