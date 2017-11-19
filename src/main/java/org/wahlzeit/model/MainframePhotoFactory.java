/*
 * Autor: Daniel Mulzer
 *
 * Date: 15.11.2017
 *
 * Version: adap-cw05
 *
 * Class: MainframePhotoFactory
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

import java.util.logging.Logger;

import org.wahlzeit.services.LogBuilder;

/**
 * factory for creating mainframe photo instances
 *
 */
public class MainframePhotoFactory extends PhotoFactory {
    
    private static final Logger log = Logger.getLogger(MainframePhotoFactory.class.getName());
    	
	/**
	 *
	 */
	protected MainframePhotoFactory() {
		// do nothing
	}

	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	public static void initialize() {
		getInstance(); // drops result due to getInstance() side-effects
	}

	/**
	 * Public singleton access method.
	 */
	public static synchronized MainframePhotoFactory getInstance() {
		if (instance == null) {
			log.config(LogBuilder.createSystemMessage().addAction("setting mainframe PhotoFactory").toString());
			setInstance(new MainframePhotoFactory());
		}
		return (MainframePhotoFactory)instance;
	}

	/**
	 * Method to set the singleton instance of MainframePhotoFactory.
	 */
	protected static synchronized void setInstance(MainframePhotoFactory photoFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initalize PhotoFactory twice");
		}

		instance = photoFactory;
	}

	/**
	 * @methodtype factory
	 */
	@Override
	public Photo createPhoto() {
		return new MainframePhoto();
	}

	/**
	 * Creates a new photo with the specified id
	 */
	@Override
	public Photo createPhoto(PhotoId id) {
		return new MainframePhoto(id);
	}
}
